package com.basim.kotlinapp.ui.gallery

import androidx.lifecycle.MutableLiveData
import com.basim.kotlinapp.base.BaseViewModel
import com.basim.kotlinapp.data.model.Gallery

/**
 * ViewModel class for Gallery Item in RecyclerView
 */
class GalleryItemViewModel: BaseViewModel(){
    private val galleryTitle = MutableLiveData<String>()
    private val galleryImageURL = MutableLiveData<String>()

    fun bind(gallery: Gallery){
        galleryTitle.value = gallery.title
        galleryImageURL.value = gallery.images?.get(0)?.link
    }

    fun getGalleryTitle():MutableLiveData<String>{
        return galleryTitle
    }

    fun getGalleryImageURL():MutableLiveData<String>{
        return galleryImageURL
    }

}