package com.android.example.wallpaper.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.example.wallpaper.databinding.ItemPhotoBinding
import com.android.example.wallpaper.models.pixabay.Hit
import com.squareup.picasso.Picasso

class PhotoAdapter(
    private val list: List<Hit>,
    private val onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<PhotoAdapter.Vh>() {

    inner class Vh(private val itemPhotoBinding: ItemPhotoBinding) :
        RecyclerView.ViewHolder(itemPhotoBinding.root) {

        fun onBind(url: Hit, position: Int) {
            Picasso.get().load(url.webformatURL).into(itemPhotoBinding.image)
            itemPhotoBinding.image.setOnClickListener {
                onItemClickListener.onItemListener(url, position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh = Vh(
        ItemPhotoBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position], position)
    }

    interface OnItemClickListener {
        fun onItemListener(hit: Hit, position: Int)
    }
}