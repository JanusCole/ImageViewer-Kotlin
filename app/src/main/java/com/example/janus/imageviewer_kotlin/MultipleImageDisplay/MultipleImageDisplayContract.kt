package com.example.janus.imageviewer_kotlin.MultipleImageDisplay

import com.example.janus.imageviewer_kotlin.ImageDescription

interface MultipleImageDisplayContract {

    interface View {
        fun displayImages(returnedImages: List<ImageDescription>) : Unit
        fun displaySingleImageUI(imageID : Int) : Unit
        fun DisplayNetworkErrorMessage() : Unit
        fun finishActivity() : Unit
    }

    interface Presenter {
        fun loadImages(searchCriteria: String) : Unit
        fun exitView() : Unit
    }
}