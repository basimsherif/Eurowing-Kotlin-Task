package com.basim.mercari.data.model

import com.basim.kotlinapp.data.model.GalleryRoot
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

/**
 * An Interface to communicate with API using Retrofit
 * */
interface ApiInterface {
    /**
     * Get the list of the images from the API
     */
    @GET("gallery/{category}")
    fun getImages(@Path("category")section: String): Observable<GalleryRoot>

    /**
     * Get the list of the viral images from the API
     */
    @GET("gallery/{category}/viral")
    fun getViralImages(@Path("category")section: String): Observable<GalleryRoot>
}