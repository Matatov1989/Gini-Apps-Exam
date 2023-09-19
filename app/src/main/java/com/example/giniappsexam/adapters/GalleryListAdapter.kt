package com.example.giniappsexam.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.request.ImageRequest
import com.example.giniappsexam.R
import com.example.giniappsexam.model.PixabayImage

class GalleryListAdapter(private val list: List<PixabayImage>, private val context: Context) :
    RecyclerView.Adapter<GalleryListAdapter.GalleryViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GalleryListAdapter.GalleryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_image, parent, false)
        return GalleryViewHolder(view)
    }

    override fun onBindViewHolder(holder: GalleryListAdapter.GalleryViewHolder, position: Int) {
        val imageLoader = ImageLoader.Builder(context).build()
        val request = ImageRequest.Builder(context)
            .data(list[position].largeImageURL)
            .target(holder.imageUrl)
            .build()
        imageLoader.enqueue(request)

        holder.textViewLikes.text = context.getString(R.string.likes, list[position].likes)
        holder.textViewComments.text = context.getString(R.string.comments, list[position].comments)
    }

    override fun getItemCount(): Int = list.size

    class GalleryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageUrl: ImageView = itemView.findViewById(R.id.imageViewUrl)
        val textViewLikes: TextView = itemView.findViewById(R.id.textViewLikes)
        val textViewComments: TextView = itemView.findViewById(R.id.textViewComments)
    }
}