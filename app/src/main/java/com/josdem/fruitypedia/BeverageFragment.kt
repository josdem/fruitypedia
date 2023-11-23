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

        Log.d("currentCategory: ${ApplicationState.getValue("currentCategory")}", "is active")
        val fruityService = RetrofitHelper.getInstance().create(FruityService::class.java)

        MainScope().launch {
            val result =
                fruityService.getBeverages(ApplicationState.getValue("currentCategory") as Int)
            Log.d("beverages: ", result.body().toString())
            displayResults(result.body())
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
