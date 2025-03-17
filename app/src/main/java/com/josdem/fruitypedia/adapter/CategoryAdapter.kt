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

package com.josdem.fruitypedia.adapter

import android.content.Context
import android.content.res.Configuration
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.josdem.fruitypedia.R
import com.josdem.fruitypedia.model.Category
import java.util.ArrayList

class CategoryAdapter(context: Context, resource: Int) : ArrayAdapter<Category>(context, resource) {
    private val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    private val images = ArrayList<Int>()

    init {
        val nightModeFlags = context.resources?.configuration?.uiMode?.and(Configuration.UI_MODE_NIGHT_MASK)
        if (nightModeFlags == Configuration.UI_MODE_NIGHT_YES) {
            images.add(R.drawable.healing_night)
            images.add(R.drawable.energy_night)
            images.add(R.drawable.healthy_night)
            images.add(R.drawable.boost_night)
        } else {
            images.add(R.drawable.healthy)
            images.add(R.drawable.energy)
            images.add(R.drawable.healthy)
            images.add(R.drawable.boost)
        }
    }

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup,
    ): View {
        if (convertView == null) {
            val view = inflater.inflate(R.layout.list_category, parent, false)
            val textView: TextView = view.findViewById(R.id.categoryTextView)
            textView.setCompoundDrawablesWithIntrinsicBounds(images[position], 0, 0, 0)
            textView.text = this.getItem(position)?.name
            return view
        }
        return convertView
    }
}
