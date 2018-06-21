package com.example.janus.imageviewer_kotlin.MultipleImageDisplay

import android.content.Context
import com.example.janus.imageviewer_kotlin.ImageDescription

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.janus.imageviewer_kotlin.R
import com.example.janus.imageviewer_kotlin.util.SquareImageView
import com.squareup.picasso.Picasso

class ImagesAdapter (val images : MutableList<ImageDescription>, val context : Context, val onItemClickedListener : OnItemClickedListener) : RecyclerView.Adapter<ImagesAdapter.ViewHolder>() {

    class ViewHolder (itemView : View) : RecyclerView.ViewHolder (itemView){
        public val imageForDisplay = itemView.findViewById<SquareImageView>(R.id.imagesRecyclerView_ImageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.images_recyclerview_item,parent, false) as View
        return ImagesAdapter.ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return images.size
    }

    override fun onBindViewHolder(holder: ImagesAdapter.ViewHolder, position: Int) {
        Picasso.with(context).load(images.get(position).webformatURL).into(holder.imageForDisplay)
    }

    // Allows for adding new item to the List o fimage URLs. This is to support pagination
    fun addImageURLs(newImages : List<ImageDescription>) {
        images.addAll(newImages);
        notifyDataSetChanged();
    }

    // Passes back the index of the image in the List.
    interface OnItemClickedListener {
        fun onItemClicked(view : View, imageIndex : Int) : Unit
    }
}