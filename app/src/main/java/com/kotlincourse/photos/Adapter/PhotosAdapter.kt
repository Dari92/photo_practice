package com.kotlincourse.photos.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kotlincourse.photos.Models.Photo
import com.kotlincourse.photos.R
import com.squareup.picasso.Picasso

class PhotosAdapter(private val photos: ArrayList<Photo>) : RecyclerView.Adapter<PhotosAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        //setting the parameters that we want to display
        val tvIdPhoto: TextView? = itemView.findViewById(R.id.tvIdPhoto)
        val tvNamePhoto: TextView? = itemView.findViewById(R.id.tvNamePhoto)
        val tvDescriptionPhoto: TextView? = itemView.findViewById(R.id.tvDescriptionPhoto)
        val ivPhoto:  ImageView? = itemView.findViewById(R.id.ivPhoto)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.layout_item_photos, parent, false) //create a new view
        return ViewHolder(v) // Return the viewholder 'v'

    }

    override fun getItemCount(): Int {
        return photos.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvIdPhoto?.text = photos[position].id.toString()
        holder.tvNamePhoto?.text = photos[position].name
        holder.tvDescriptionPhoto?.text = photos[position].description

        Picasso.get().load(photos[position].photo).into(holder.ivPhoto)

    }
}