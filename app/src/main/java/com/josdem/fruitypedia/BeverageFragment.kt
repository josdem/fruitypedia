package com.josdem.fruitypedia

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBeverageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("currentCategory: ${ApplicationState.getValue("currentCategory")}", "is active")
        val fruityService = RetrofitHelper.getInstance().create(FruityService::class.java)

        MainScope().launch {
            val result = fruityService.getBeverages(ApplicationState.getValue("currentCategory") as Int)
            Log.d("beverages: ", result.body().toString())
            displayResults(result.body())
        }

        binding.buttonBeverage.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_ThirdFragment)
        }
    }

    private fun displayResults(beverages: List<Beverage>?) {
        val listView = view?.findViewById(R.id.listViewBeverages) as ListView

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}