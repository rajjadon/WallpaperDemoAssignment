package org.school.demoapp;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.squareup.picasso.Picasso;

public class CustomBindAdapter
{
    @BindingAdapter("loadImage")
    public static void loadImage(ImageView imageView, String imageUrl)
    {
        Picasso.get().load(imageUrl).into(imageView);
    }
}
