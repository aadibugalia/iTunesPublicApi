package com.adityabugalia.itunespublicapi.databinding

import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter(value = ["setTextViewText"])
fun setTextViewText(textView: TextView, text: String) {
    Log.d("Text",""+text)
    textView.text = text
}

@BindingAdapter(value = ["loadImageFromUrl"])
fun loadImageFromUrl(imageView: ImageView, url: String) {
    Glide.with(imageView.context)
        .load(url)
        .into(imageView)
}