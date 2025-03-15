/*
Copyright 2025 Jose Morales contact@josdem.io

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
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.common.collect.Maps
import com.josdem.fruitypedia.adapter.BeverageAdapter
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
    private val fruityService: FruityService =
        RetrofitHelper.getInstance().create(FruityService::class.java)

    private val id: String = "ID"
    private val name: String = "NAME"
    private var data: MutableList<MutableMap<String, Any>> = mutableListOf()

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

        if (currentCategory > 0) {
            MainScope().launch {
                val result =
                    fruityService.getBeverages(ApplicationState.getValue("currentCategory") as Int)
                Log.d("beverages: ", result.body().toString())
                populateData(result.body())
                printData()
                displayResults()
            }
        }
    }

    private fun makeItem(beverage: Beverage): MutableMap<String, Any> {
        val dataRow: MutableMap<String, Any> = Maps.newHashMap()
        dataRow[beverage.id.toString()] = beverage.name
        return dataRow
    }

    private fun populateData(beverages: List<Beverage>?) {
        beverages?.forEach { beverage ->
            data.add(makeItem(beverage))
        }
    }

    private fun printData() {
        Log.d("data size: ", "size: " + data.size.toString())
        data.forEach {
            Log.d("item:", it.toString())
        }
    }

    private fun displayResults() {
        val listView: ListView = view?.findViewById(R.id.listViewBeverages)!!
        val from = arrayOf(id, name)
        val to = intArrayOf(R.id.beverageIdTextView, R.id.beverageTextView)

        val simpleAdapter =
            this.context?.let { BeverageAdapter(it, data, R.layout.list_beverage, from, to) }
        listView.adapter = simpleAdapter

        listView.setOnItemClickListener { parent, view, position, id ->
            val item = simpleAdapter?.getItem(position)
            Log.d("element:", "$item was selected")
            val entry = item.toString().replace("{", "").replace("}", "").split("=")
            ApplicationState.storeValue("currentBeverage", Integer.valueOf(entry[0]))
            findNavController().navigate(R.id.action_SecondFragment_to_ThirdFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
