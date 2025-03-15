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
import com.josdem.fruitypedia.adapter.CategoryAdapter
import com.josdem.fruitypedia.databinding.FragmentCategoryBinding
import com.josdem.fruitypedia.model.Category
import com.josdem.fruitypedia.state.ApplicationState

class CategoryFragment : Fragment() {
    private var _binding: FragmentCategoryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("onViewCreated", "...")
        if (ApplicationState.getValue("categories") != null) {
            displayResults(ApplicationState.getValue("categories") as ArrayList<Category>)
        }

        ApplicationState.storeValue("categoryFragment", this)
    }

    fun displayResults(categories: List<Category>?) {
        val listView = view?.findViewById(R.id.listViewCategories) as ListView
        val arrayAdapter =
            CategoryAdapter(view!!.context, R.layout.list_category)
        listView.adapter = arrayAdapter

        categories?.forEach { category ->
            arrayAdapter.add(category)
        }

        ApplicationState.storeValue("categories", categories as Any)

        listView.setOnItemClickListener { parent, view, position, id ->
            val category = arrayAdapter.getItem(position)
            Log.d("element: ${category?.id}", "was selected")
            category?.id?.let { ApplicationState.storeValue("currentCategory", it) }
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
