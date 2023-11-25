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
            val bitmap : Bitmap = loadImage(url)
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