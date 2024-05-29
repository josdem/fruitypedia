/*
Copyright 2024 Jose Morales contact@josdem.io

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing,
software distributed under the License is distributed on an
"AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
KIND, either express or implied.  See the License for the
specific language governing permissions and limitations
under the License.
 */

package com.josdem.fruitypedia

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.josdem.fruitypedia.databinding.ActivityMainBinding
import com.josdem.fruitypedia.model.Category
import com.josdem.fruitypedia.service.FruityService
import com.josdem.fruitypedia.service.RetrofitHelper
import com.josdem.fruitypedia.state.ApplicationState
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.io.File
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private val language: String = Locale.getDefault().language
    private var clickCounter: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dexOutputDir: File = codeCacheDir
        dexOutputDir.setReadOnly()

        val fruityService = RetrofitHelper.getInstance().create(FruityService::class.java)

        MainScope().launch {
            val result = fruityService.getCategories(language)
            Log.d("categories: ", result.body().toString())
            storeResponse(result.body())
        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setEasterEgg(binding.toolbar)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    private fun setEasterEgg(toolbar: Toolbar) {
        Log.d("Easter Egg:", "On Toolbar")
        toolbar.setOnClickListener {
            clickCounter += 1
            Log.d("clickCounter:", clickCounter.toString())
            if (clickCounter >= 5) {
                AlertDialog.Builder(this)
                    .setMessage(R.string.dialogMessage)
                    .setPositiveButton(R.string.dialogButton, null)
                    .show()
                clickCounter = 0
            }
        }
    }

    private fun storeResponse(categories: List<Category>?) {
        (ApplicationState.getValue("categoryFragment") as CategoryFragment).displayResults(
            categories,
        )
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) ||
            super.onSupportNavigateUp()
    }
}
