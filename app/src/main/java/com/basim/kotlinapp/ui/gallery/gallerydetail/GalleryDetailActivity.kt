package com.basim.kotlinapp.ui.gallery.gallerydetail

import android.os.Bundle
import android.transition.TransitionInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.basim.kotlinapp.R
import com.basim.kotlinapp.data.model.Gallery
import com.basim.kotlinapp.databinding.ActivityGalleryDetailsBinding
import com.basim.kotlinapp.utils.Constants.Companion.BUNDLE_EXTRA_GALLERY_PARAM
import com.basim.kotlinapp.utils.Constants.Companion.BUNDLE_EXTRA_PARAM
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

/** An Activity for showing gallery detail page
 **/
class GalleryDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGalleryDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.sharedElementEnterTransition = TransitionInflater.from(this).inflateTransition(R.transition.image_transition)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_gallery_details)
        val bundle = intent.getBundleExtra(BUNDLE_EXTRA_PARAM)
        val gallery = bundle.getParcelable<Gallery>(BUNDLE_EXTRA_GALLERY_PARAM)
        binding.title.text = gallery?.title
        binding.descriptionTextView.text = gallery?.images?.get(0)?.description
        Glide.with(this)
            .load(gallery?.images?.get(0)?.link).placeholder(R.drawable.placeholder).apply(RequestOptions().centerCrop()).dontTransform()
            .into(binding.header)
    }
}
