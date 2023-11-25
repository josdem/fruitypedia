/*
Copyright 2023 Jose Morales joseluis.delacruz@gmail.com

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
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.josdem.fruitypedia.databinding.FragmentRecipeBinding
import com.josdem.fruitypedia.model.Beverage
import com.josdem.fruitypedia.service.FruityService
import com.josdem.fruitypedia.service.RetrofitHelper
import com.josdem.fruitypedia.state.ApplicationState
import com.josdem.fruitypedia.util.ImageResolver
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class RecipeFragment : Fragment() {
    private var _binding: FragmentRecipeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentRecipeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
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

        val image = view?.findViewById<ImageView>(R.id.image)
        if (image != null && beverage != null) {
            ImageResolver.setImage(image, beverage)
        }

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
