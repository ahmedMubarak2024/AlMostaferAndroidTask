package com.mub.almostaferandroidtask.helpers

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.mub.almostaferandroidtask.R
import com.mub.almostaferandroidtask.model.Constants

@BindingAdapter("loadSmallImage")
fun ImageView.loadSmallImage(url: String?) {
    Glide.with(this).load(Constants.BASE_IMAGE_URL + url)
        .placeholder(R.drawable.small_place_holder)
        .error(R.drawable.small_place_holder)
        .into(this)
}

@BindingAdapter("loadLargeImage")
fun ImageView.loadLargeImage(url: String?) {
    Glide.with(this).load(Constants.BASE_IMAGE_URL + url)
        .placeholder(R.drawable.large_place_holder)
        .error(R.drawable.small_place_holder)
        .into(this)
}