package com.josdem.fruitypedia.util

import android.graphics.BitmapFactory
import android.widget.ImageView
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class DownloadImageHelper(private val imageView: ImageView) {

    private val executor: ExecutorService = Executors.newSingleThreadExecutor()

    fun doInBackground(url: String) {
        var connection: HttpURLConnection?
        executor.execute {
            connection = URL(url).openConnection() as HttpURLConnection?
            connection?.connect()
            val inputStream: InputStream? = connection?.inputStream
            val bitmap = BitmapFactory.decodeStream(inputStream)
            imageView.setImageBitmap(bitmap)
        }
    }
}