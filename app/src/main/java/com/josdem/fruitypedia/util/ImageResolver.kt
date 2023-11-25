package com.josdem.fruitypedia.util

import android.widget.ImageView
import com.josdem.fruitypedia.R
import com.josdem.fruitypedia.model.Beverage

class ImageResolver {

    companion object {

        fun setImage(image: ImageView, beverage: Beverage) {
            if (beverage.image == "") {
                image.setImageResource(R.drawable.no_image);
            } else {
                DownloadImageHelper(image).doInBackground(beverage.image)
            }
        }

    }
}