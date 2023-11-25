/*
Copyright 2023 Jose Morales joseluis.delacruz@gmail.com

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

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class DownloadImageHelper(private val imageView: ImageView) {
    private val executor: ExecutorService = Executors.newSingleThreadExecutor()
    private val handler = Handler(Looper.getMainLooper())

    fun doInBackground(url: String) {
        executor.execute {
            val bitmap: Bitmap = loadImage(url)
            handler.post {
                imageView.setImageBitmap(bitmap)
            }
        }
    }

    private fun loadImage(url: String): Bitmap {
        var connection: HttpURLConnection? = URL(url).openConnection() as HttpURLConnection?
        connection?.connect()
        val inputStream: InputStream? = connection?.inputStream
        return BitmapFactory.decodeStream(inputStream)
    }
}
