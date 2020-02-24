package com.basim.kotlinapp.utils

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.basim.kotlinapp.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

/**
 * Binding Adapter class
 */
@BindingAdapter("mutableVisibility")
fun setMutableVisibility(view: View, visibility: MutableLiveData<Int>?) {
    val parentActivity: AppCompatActivity? = view.context as AppCompatActivity?
    if(parentActivity != null && visibility != null) {
        visibility.observe(parentActivity, Observer { value -> view.visibility = value?:View.VISIBLE})
    }
}
@BindingAdapter("mutableText")
fun setMutableText(view: TextView, text: MutableLiveData<String>?) {
    val parentActivity:AppCompatActivity? = view.context as AppCompatActivity?
    if(parentActivity != null && text != null) {
        text.observe(parentActivity, Observer { value -> view.text = value?:""})
    }
}
@BindingAdapter("mutableImageURL")
fun setMutableImageURL(view: ImageView, imageUrl: MutableLiveData<String>?) {
    val parentActivity:AppCompatActivity? = view.context as AppCompatActivity?
    if(parentActivity != null && imageUrl != null) {
        imageUrl.observe(parentActivity, Observer { value -> Glide.with(view.context)
            .load(value?.toString()).placeholder(R.drawable.placeholder).apply(RequestOptions().centerCrop()).dontTransform().diskCacheStrategy( DiskCacheStrategy.ALL )
            .into(view)})
    }
}