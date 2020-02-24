package com.basim.kotlinapp.ui.gallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.basim.kotlinapp.ui.GalleryViewModel

/**
 * A custom viewModel factory which accepts category as a parameter while declaring gallery viewModel
 */
class GalleryViewModelFactory(private val category: String) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return GalleryViewModel(category) as T
    }

}