package com.basim.kotlinapp.data.model

/**
 * Class which provides a model for galleryroot
 * @constructor Sets all properties of the galleryroot
 * @property data list of gallery items
 */
data class GalleryRoot(
    var data : List<Gallery>)