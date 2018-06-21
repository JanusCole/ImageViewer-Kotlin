package com.example.janus.imageviewer_kotlin.ImageSearch

import com.example.janus.imageviewer_kotlin.ImageDescription
import com.example.janus.imageviewer_kotlin.ImageService
import com.example.janus.imageviewer_kotlin.ImageServiceInterface

class ImageSearchPresenter (imageSearchView : ImageSearchContract.View) : ImageSearchContract.Presenter  {

    val imageSearchView : ImageSearchContract.View = imageSearchView

    override fun loadImages(searchCriteria: String) {

        ImageService.getImages(searchCriteria, 1, object : ImageServiceInterface.ImagesSearchCallback {
            override fun onImagesFound(returnedImages: List<ImageDescription>, imageCount: Int) {
                if (returnedImages.isEmpty()) {
                    imageSearchView.displayNotFoundMessage()
                } else {
                    imageSearchView.displayImagesUI(searchCriteria)
                }
            }

            override fun endOfDataReached() {
                imageSearchView.displayNotFoundMessage()
            }

            override fun onNetworkError() {
                imageSearchView.DisplayNetworkErrorMessage()
            }
        })
    }
}