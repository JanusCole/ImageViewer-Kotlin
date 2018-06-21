package com.example.janus.imageviewer_kotlin

object ImageService {

    val remoteImageDataSource : RemoteImageDataSource = RemoteImageDataSource()

    // This is the image cache. It saves ImageDescription objects in a HashMap keyed on the image ID
    lateinit var imageCache : List<ImageDescription>

// These fields are used to determine if the cache can satisfy the current image request
    var lastSearchCriteria : String = ""

    fun getImages (searchCriteria : String, pageNumber : Int, imagesSearchCallback : ImageServiceInterface.ImagesSearchCallback) : Unit {

        if (searchCriteria.equals(lastSearchCriteria)) {
            imagesSearchCallback.onImagesFound(imageCache, imageCache.size)
        } else {

            remoteImageDataSource.getImages(searchCriteria, pageNumber, object : ImageServiceInterface.ImagesSearchCallback {
                override fun onImagesFound(returnedImages: List<ImageDescription>, imageCount: Int) {
                    imageCache = returnedImages
                    lastSearchCriteria = searchCriteria
                    imagesSearchCallback.onImagesFound(returnedImages, imageCount)
                }

                override fun endOfDataReached() {
                    imagesSearchCallback.endOfDataReached()
                }

                override fun onNetworkError() {
                    imagesSearchCallback.onNetworkError()
                }
            })
        }

    }

}