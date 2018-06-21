package com.example.janus.imageviewer_kotlin.MultipleImageDisplay

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import com.example.janus.imageviewer_kotlin.*
import com.example.janus.imageviewer_kotlin.util.DisplayFormattedMessages

class MultipleImageDisplayActivity : AppCompatActivity(), MultipleImageDisplayContract.View, ImagesAdapter.OnItemClickedListener {

    public val IMAGE_SEARCH_CRITERIA : String = "image_search_criteria"
    lateinit var multipleImageDisplayPresenter : MultipleImageDisplayContract.Presenter

    lateinit var imagesRecyclerView : RecyclerView
    lateinit var  gridLayoutManager : GridLayoutManager
    lateinit var  imagesRecyclerViewAdapter : ImagesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.multiple_images_display_activity)

        // Get the display fields from the layout
        imagesRecyclerView = findViewById(R.id.multipleImagesDisplay_RecyclerView) as RecyclerView
        gridLayoutManager = GridLayoutManager(this, getResources().getInteger(R.integer.image_grid_columns));
        imagesRecyclerView.setLayoutManager(gridLayoutManager);
        imagesRecyclerViewAdapter = ImagesAdapter(ArrayList<ImageDescription>(), this, this);
        imagesRecyclerView.setAdapter(imagesRecyclerViewAdapter);

        // Get the image search criteria
        var searchCriteria : String = getIntent().getStringExtra(IMAGE_SEARCH_CRITERIA);

        // Create the presenter and pass it a repository

        multipleImageDisplayPresenter = MultipleImageDisplayPresenter(this);
        multipleImageDisplayPresenter.loadImages(searchCriteria)

        var returnButton : Button = findViewById<Button>(R.id.multipleImagesDisplayReturn_Button)

        returnButton.setOnClickListener(View.OnClickListener {
            onReturnButtonClicked()
        })

    }

    fun onReturnButtonClicked() :Unit {
        multipleImageDisplayPresenter.exitView()
    }

    override fun displayImages(returnedImages: List<ImageDescription>) {
        imagesRecyclerViewAdapter.addImageURLs(returnedImages)
    }

    override fun displaySingleImageUI(imageID: Int) {
    }

    override fun DisplayNetworkErrorMessage() {
        DisplayFormattedMessages.displayErrorMessageAlertDialog(getString(R.string.network_error_message), layoutInflater, this);
    }

    override fun finishActivity() {
        finish()
    }

    override fun onItemClicked(view: View, imageIndex: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
