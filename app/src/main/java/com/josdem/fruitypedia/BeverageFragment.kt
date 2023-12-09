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
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.josdem.fruitypedia.databinding.FragmentBeverageBinding
import com.josdem.fruitypedia.model.Beverage
import com.josdem.fruitypedia.service.FruityService
import com.josdem.fruitypedia.service.RetrofitHelper
import com.josdem.fruitypedia.state.ApplicationState
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class BeverageFragment : Fragment() {
    private var _binding: FragmentBeverageBinding? = null
    private val binding get() = _binding!!
    private val fruityService: FruityService = RetrofitHelper.getInstance().create(FruityService::class.java)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentBeverageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        val currentCategory: Int = ApplicationState.getValue("currentCategory") as Int
        Log.d("currentCategory: $currentCategory", "is active")

        if(currentCategory > 0) {
            MainScope().launch {
                val result =
                    fruityService.getBeverages(ApplicationState.getValue("currentCategory") as Int)
                Log.d("beverages: ", result.body().toString())
                displayResults(result.body())
            }
        }
    }

    private fun displayResults(beverages: List<Beverage>?) {
        val listView = view?.findViewById(R.id.listViewBeverages) as ListView
        val arrayAdapter: ArrayAdapter<Beverage> =
            ArrayAdapter<Beverage>(
                view!!.context,
                R.layout.list_beverage,
                R.id.beverageTextView,
                beverages as MutableList<Beverage>,
            )
        listView.adapter = arrayAdapter

        listView.setOnItemClickListener { parent, view, position, id ->
            val beverage = arrayAdapter.getItem(position)
            Log.d("element: $beverage", "was selected")
            beverage?.id?.let { ApplicationState.storeValue("currentBeverage", it) }
            findNavController().navigate(R.id.action_SecondFragment_to_ThirdFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
