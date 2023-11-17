package com.josdem.fruitypedia

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.josdem.fruitypedia.databinding.ActivityMainBinding
import com.josdem.fruitypedia.service.FruityService
import com.josdem.fruitypedia.service.RetrofitHelper
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.File

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dexOutputDir: File = codeCacheDir
        dexOutputDir.setReadOnly()

        val fruityService = RetrofitHelper.getInstance().create(FruityService::class.java)

        GlobalScope.launch {
            val result = fruityService.getCategories()
            Log.d("categories: ", result.body().toString())
        }

        displayResults()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    private fun displayResults() {
        val categories =
            arrayOf("Healing", "Energy", "Healthy", "Boost")
        val arrayAdapter: ArrayAdapter<String> =
            ArrayAdapter<String>(this, R.layout.list_category, R.id.categoryTextView, categories)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}