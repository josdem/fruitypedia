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
import com.josdem.fruitypedia.adapter.CategoryAdapter
import com.josdem.fruitypedia.databinding.FragmentCategoryBinding
import com.josdem.fruitypedia.model.Category
import com.josdem.fruitypedia.state.ApplicationState


class CategoryFragment : Fragment() {

    private var _binding: FragmentCategoryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ApplicationState.storeValue("categoryFragment", this)
        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    fun displayResults(categories: List<Category>?) {
        val listView = view?.findViewById(R.id.listViewCategories) as ListView
        val arrayAdapter =
            CategoryAdapter(view!!.context, R.layout.list_category)
        listView.adapter = arrayAdapter

        categories?.forEach { category ->
            arrayAdapter.add(category)
        }

        listView.setOnItemClickListener { parent, view, position, id ->
            val category = arrayAdapter.getItem(position)
            Log.d("element: ${category?.id}", "was selected" )
            category?.id?.let { ApplicationState.storeValue("currentCategory", it) }
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}