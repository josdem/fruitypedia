package com.josdem.fruitypedia

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.josdem.fruitypedia.databinding.FragmentRecipeBinding
import com.josdem.fruitypedia.model.Beverage
import com.josdem.fruitypedia.service.FruityService
import com.josdem.fruitypedia.service.RetrofitHelper
import com.josdem.fruitypedia.state.ApplicationState
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch


class RecipeFragment : Fragment() {

    private var _binding: FragmentRecipeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecipeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fruityService = RetrofitHelper.getInstance().create(FruityService::class.java)

        MainScope().launch {
            val result =
                fruityService.getBeverage(ApplicationState.getValue("currentBeverage") as Int)
            Log.d("beverage: ", result.body().toString())
            displayResults(result.body())
        }
    }

    private fun displayResults(beverage: Beverage?) {
        val name = view?.findViewById<TextView>(R.id.name)
        name?.text = beverage?.name

        val ingredients = view?.findViewById<TextView>(R.id.ingredients)
        ingredients?.text = beverage?.ingredients

        val recipe = view?.findViewById<TextView>(R.id.recipe)
        recipe?.text = beverage?.recipe
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}