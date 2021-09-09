package com.android.example.wallpaper.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.example.wallpaper.R
import com.android.example.wallpaper.databinding.ItemImageBinding
import com.android.example.wallpaper.models.pixabay.Hit
import com.squareup.picasso.Picasso

class ImageAdapter(private val list: List<Hit>) : RecyclerView.Adapter<ImageAdapter.Vh>() {

    inner class Vh(private val itemImageBinding: ItemImageBinding) :
        RecyclerView.ViewHolder(itemImageBinding.root) {

        fun onBind(hit: Hit, position: Int) {
            if (hit.userImageURL != "") {
                Picasso.get().load(hit.userImageURL).placeholder(R.drawable.user_bg)
                    .into(itemImageBinding.userImage)
            } else {
                itemImageBinding.userImage.setImageResource(R.drawable.ic_user)
            }
            itemImageBinding.username.text = hit.user
            Picasso.get().load(hit.largeImageURL).into(itemImageBinding.image)
            Picasso.Listener { picasso, uri, exception ->
                if (picasso.areIndicatorsEnabled()) {
                    itemImageBinding.progressBar.visibility = View.INVISIBLE
                }
            }
            itemImageBinding.likesCount.text = "${hit.likes} likes"
            if (hit.comments > 99) {
                itemImageBinding.commentCount.text = "99+"
            } else {
                itemImageBinding.commentCount.text = hit.comments.toString()
            }
            if (hit.views > 999) {
                itemImageBinding.viewsCount.text = "999+"
            } else {
                itemImageBinding.viewsCount.text = hit.views.toString()
            }
            if (hit.downloads > 999) {
                itemImageBinding.downloadCount.text = "999+"
            } else {
                itemImageBinding.downloadCount.text = hit.downloads.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh = Vh(
        ItemImageBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position], position)
    }
}