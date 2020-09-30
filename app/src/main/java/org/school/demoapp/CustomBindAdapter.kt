package org.school.demoapp

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

object CustomBindAdapter {
    @JvmStatic
    @BindingAdapter("loadImage")
    fun loadImage(imageView: ImageView?, imageUrl: String?) {
        Picasso.get().load(imageUrl).into(imageView)
    }
}