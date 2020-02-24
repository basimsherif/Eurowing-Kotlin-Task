package com.basim.kotlinapp.base

import androidx.lifecycle.ViewModel
import com.basim.kotlinapp.injection.NetworkModule
import com.basim.kotlinapp.injection.component.DaggerViewModelInjector
import com.basim.kotlinapp.injection.component.ViewModelInjector
import com.basim.kotlinapp.ui.GalleryViewModel
/**
 * Base Viewmodel class used for dependency injection
 */
abstract class BaseViewModel: ViewModel(){
    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is GalleryViewModel -> injector.inject(this)
        }
    }
}