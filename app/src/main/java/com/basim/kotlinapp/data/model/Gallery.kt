package com.basim.kotlinapp.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Class which provides a model for gallery
 * @constructor Sets all properties of the gallery
 * @property id id of the gallery
 * @property title title of the gallery
 * @property description description of the gallery
 * @property link URL of the gallery
 */
@Parcelize
data class Gallery (
    var id : String?,
    val title : String?,
    val description : String?,
    val images : List<Image>?,
    val link : String?) : Parcelable