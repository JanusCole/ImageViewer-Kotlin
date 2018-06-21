package com.example.janus.imageviewer_kotlin.MultipleImageDisplay

import com.example.janus.imageviewer_kotlin.ImageDescription
import com.example.janus.imageviewer_kotlin.ImageService
import com.example.janus.imageviewer_kotlin.ImageServiceInterface

class MultipleImageDisplayPresenter (multipleImageDisplayView : MultipleImageDisplayContract.View) : MultipleImageDisplayContract.Presenter  {

    val multipleImageDisplayView : MultipleImageDisplayContract.View = multipleImageDisplayView

    override fun loadImages(searchCriteria: String) {

        ImageService.getImages(searchCriteria, 1, object : ImageServiceInterface.ImagesSearchCallback {
            override fun onImagesFound(returnedImages: List<ImageDescription>, imageCount: Int) {
                if (returnedImages.isEmpty()) {
                    multipleImageDisplayView.DisplayNetworkErrorMessage()
                } else {
                    multipleImageDisplayView.displayImages(returnedImages)
                }
            }

            override fun endOfDataReached() {
            }

            override fun onNetworkError() {
                multipleImageDisplayView.DisplayNetworkErrorMessage()
            }
        })
    }

    override fun exitView() {
        multipleImageDisplayView.finishActivity()
    }
}