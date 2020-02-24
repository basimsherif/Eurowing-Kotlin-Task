package com.basim.kotlinapp.ui

import com.basim.kotlinapp.base.BaseViewModel
import com.basim.mercari.data.model.ApiInterface
import io.reactivex.disposables.Disposable
import javax.inject.Inject

/**
 * ViewModel class for Categories
 */
class MainViewModel: BaseViewModel(){
    @Inject
    lateinit var apiInterface: ApiInterface

    private lateinit var subscription: Disposable
}
