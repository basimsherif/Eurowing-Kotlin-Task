package com.basim.kotlinapp.ui

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.basim.kotlinapp.R
import com.basim.kotlinapp.base.BaseViewModel
import com.basim.kotlinapp.data.model.Gallery
import com.basim.kotlinapp.data.model.GalleryRoot
import com.basim.kotlinapp.utils.SingleLiveEvent
import com.basim.kotlinapp.utils.idlingresource.EspressoIdlingResource
import com.basim.mercari.data.model.ApiInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * ViewModel class for Gallery listing page
 */
class GalleryViewModel(val category: String) : BaseViewModel(){
    @Inject
    lateinit var apiInterface: ApiInterface

    private lateinit var subscription: Disposable
    val galleryList: MutableLiveData<GalleryRoot> = MutableLiveData()
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val flameVisibility: MutableLiveData<Int> = MutableLiveData()
    val recyclerVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage:MutableLiveData<Int> = MutableLiveData()
    internal val selectItemEvent = SingleLiveEvent<Pair<out Gallery, out List<View>>>()
    val viralStatus: MutableLiveData<Boolean> = MutableLiveData()

    init{
        loadGalleries()
    }

    fun loadGalleries(){
        subscription = apiInterface.getImages(category)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrieveGalleryStart() }
            .doOnTerminate { onRetrieveGalleryFinish() }
            .subscribe( { result -> onRetrieveGallerySuccess(result)},
                { result ->onRetrieveGalleryError(result)}
            )
    }

    fun loadViralGalleries(){
        subscription = apiInterface.getViralImages(category)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrieveViralGalleryStart() }
            .doOnTerminate { onRetrieveViralGalleryFinish() }
            .subscribe( { result -> onRetrieveViralGallerySuccess(result)},
                { result ->onRetrieveViralGalleryError(result)}
            )
    }

    private fun onRetrieveGalleryStart(){
        EspressoIdlingResource.increment()
        loadingVisibility.value = View.VISIBLE
        recyclerVisibility.value = View.GONE
    }

    private fun onRetrieveGalleryFinish(){
        EspressoIdlingResource.decrement()
        loadingVisibility.value = View.GONE
        recyclerVisibility.value = View.VISIBLE
    }

    private fun onRetrieveGallerySuccess(imageList:GalleryRoot){
        this.galleryList.value = imageList
        this.viralStatus.value = false
    }

    private fun onRetrieveGalleryError(result: Throwable){
        errorMessage.value = R.string.gallery_error
    }

    private fun onRetrieveViralGalleryStart(){
        EspressoIdlingResource.increment()
        flameVisibility.value = View.VISIBLE
        recyclerVisibility.value = View.GONE
    }

    private fun onRetrieveViralGalleryFinish(){
        EspressoIdlingResource.decrement()
        flameVisibility.value = View.GONE
        recyclerVisibility.value = View.VISIBLE
    }

    private fun onRetrieveViralGallerySuccess(imageList:GalleryRoot){
        this.galleryList.value = imageList
        this.viralStatus.value = true
    }

    private fun onRetrieveViralGalleryError(result: Throwable){
        errorMessage.value = R.string.gallery_error
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }
}
