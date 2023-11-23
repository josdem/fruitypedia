package com.josdem.fruitypedia.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.josdem.fruitypedia.R
import com.josdem.fruitypedia.model.Category

class CategoryAdapter(context: Context, resource: Int) : ArrayAdapter<Category>(context, resource) {
    private val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup,
    ): View {
        val view = inflater.inflate(R.layout.list_category, parent, false)
        val textView = view.findViewById(R.id.categoryTextView) as TextView
        textView.text = this.getItem(position)?.name
        return view
    }
}
