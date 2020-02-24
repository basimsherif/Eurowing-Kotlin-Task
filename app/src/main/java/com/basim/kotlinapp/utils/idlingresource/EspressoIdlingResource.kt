package com.basim.kotlinapp.utils.idlingresource

import androidx.test.espresso.IdlingResource

object EspressoIdlingResource {

    private const val resource = "GLOBAL"
    private val countingIdlingResource =
        CountingIdlingResource(resource)

    fun increment() = countingIdlingResource.increment()

    fun decrement() = countingIdlingResource.decrement()

    fun getIdlingResource(): IdlingResource =
        countingIdlingResource


}