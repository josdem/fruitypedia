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
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SimpleAdapter
import android.widget.TextView
import com.josdem.fruitypedia.R

class BeverageAdapter(
    context: Context,
    data: MutableList<MutableMap<String, Any>>,
    resource: Int,
    from: Array<String>,
    to: IntArray,
) : SimpleAdapter(context, data, resource, from, to) {
    private val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup,
    ): View {
        if (convertView == null) {
            val view = inflater.inflate(R.layout.list_beverage, parent, false)
            val textView: TextView = view.findViewById(R.id.beverageIdTextView)
            val nameView: TextView = view.findViewById(R.id.beverageTextView)
            val entry = this.getItem(position).toString().replace("{", "").replace("}", "").split("=")
            textView.text = entry[0]
            nameView.text = entry[1]
            return view
        }
        return convertView
    }
}
