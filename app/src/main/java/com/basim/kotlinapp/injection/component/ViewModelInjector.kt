package com.basim.kotlinapp.injection.component

import com.basim.kotlinapp.injection.NetworkModule
import com.basim.kotlinapp.ui.GalleryViewModel
import dagger.Component
import javax.inject.Singleton

/**
 * Component providing inject() methods for presenters.
 */
@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {
    /**
     * Injects required dependencies into the specified GalleryViewModel.
     * @param galleryViewModel GalleryViewModel in which to inject the dependencies
     */
    fun inject(galleryViewModel: GalleryViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector
        fun networkModule(networkModule: NetworkModule): Builder
    }
}