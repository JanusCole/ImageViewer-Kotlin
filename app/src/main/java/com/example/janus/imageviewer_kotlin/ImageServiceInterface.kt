package com.example.janus.imageviewer_kotlin

interface ImageServiceInterface {

    interface ImagesSearchCallback {
        fun onImagesFound (returnedImages : List<ImageDescription>, imageCount : Int) : Unit
        fun endOfDataReached() : Unit
        fun onNetworkError() : Unit
    }
}