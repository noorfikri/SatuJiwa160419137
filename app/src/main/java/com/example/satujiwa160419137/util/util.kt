package com.example.satujiwa160419137.util

import android.content.SharedPreferences
import android.view.View
import android.widget.ImageView
import com.example.satujiwa160419137.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

fun ImageView.loadImage(url: String?){
    Picasso.get()
        .load(url)
        .resize(400, 400)
        .centerCrop()
        .error(R.drawable.baseline_error_24)
        .into(this, object: Callback {
            override fun onSuccess() {
            }
            override fun onError(e: Exception?) {
            }
        })
}