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

package com.josdem.fruitypedia.util

import android.widget.ImageView
import com.josdem.fruitypedia.R
import com.josdem.fruitypedia.model.Beverage

class ImageResolver {
    companion object {
        fun setImage(
            image: ImageView,
            beverage: Beverage,
        ) {
            if (beverage.image == "") {
                image.setImageResource(R.drawable.no_image)
            } else {
                DownloadImageHelper(image).doInBackground(beverage.image)
            }
        }
    }
}
