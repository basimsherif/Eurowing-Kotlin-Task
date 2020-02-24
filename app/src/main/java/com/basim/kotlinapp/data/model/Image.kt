package com.basim.kotlinapp.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Class which provides a model for Image
 * @constructor Sets all properties of the Image
 * @property id id of the media
 * @property title title of the media
 * @property description description of the media
 * @property type type of the media
 * @property link URL of the media
 */
@Parcelize
data class Image(
    var id : String?,
    val title : String?,
    val description : String?,
    val type : String?,
    val link : String?) : Parcelable