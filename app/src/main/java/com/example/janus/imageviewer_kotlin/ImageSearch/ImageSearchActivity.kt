package com.example.janus.imageviewer_kotlin.ImageSearch

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.example.janus.imageviewer_kotlin.*
import com.example.janus.imageviewer_kotlin.MultipleImageDisplay.MultipleImageDisplayActivity
import com.example.janus.imageviewer_kotlin.util.DisplayFormattedMessages

class ImageSearchActivity : AppCompatActivity(), ImageSearchContract.View {

    lateinit var imageSearchPresenter : ImageSearchContract.Presenter

    val IMAGE_SEARCH_CRITERIA : String = "image_search_criteria"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.image_search_activity)

        // Create the presenter and pass it a repository

        imageSearchPresenter = ImageSearchPresenter(this);

        var imageSearchCritera : EditText = findViewById(R.id.imageSearchCriteriaEditText) as EditText
        var imageSearchButton : Button = findViewById(R.id.imageSearchButton) as Button

        imageSearchButton.setOnClickListener(View.OnClickListener {
            searchImages(imageSearchCritera.text.toString())
        })
    }

    // When the user clicks the Search button, call the Presenter to perform the search on the text in the search field
    private fun searchImages (searchCriteria : String) {
        imageSearchPresenter.loadImages(searchCriteria);
    }

    override fun displayImagesUI(searchCriteria: String) {
        var multipleImagesDisplayActivityIntent : Intent = Intent(this, MultipleImageDisplayActivity::class.java)
        multipleImagesDisplayActivityIntent.putExtra(IMAGE_SEARCH_CRITERIA, searchCriteria)
        startActivity(multipleImagesDisplayActivityIntent)
    }

    override fun displayNotFoundMessage() {
        DisplayFormattedMessages.displayErrorMessageAlertDialog(getString(R.string.not_found_message), layoutInflater, this);
    }

    override fun DisplayNetworkErrorMessage() {
        DisplayFormattedMessages.displayErrorMessageAlertDialog(getString(R.string.network_error_message), layoutInflater, this);
    }
}
