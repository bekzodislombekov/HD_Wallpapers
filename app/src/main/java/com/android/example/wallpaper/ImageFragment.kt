package com.android.example.wallpaper

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import com.android.example.wallpaper.adapters.ImageAdapter
import com.android.example.wallpaper.databinding.FragmentImageBinding
import com.android.example.wallpaper.models.pixabay.Hit
import com.android.example.wallpaper.models.pixabay.Photo
import com.squareup.picasso.Picasso

private const val ARG_PARAM1 = "photo"
private const val ARG_PARAM2 = "position"

class ImageFragment : Fragment() {
    private lateinit var photo: Photo
    private var position: Int = -1
    private lateinit var binding: FragmentImageBinding
    private lateinit var adapter: ImageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            photo = it.getSerializable(ARG_PARAM1) as Photo
            position = it.getInt(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentImageBinding.inflate(inflater, container, false)
        adapter = ImageAdapter(photo.hits)
        binding.rv.adapter = adapter
        binding.rv.scrollToPosition(position)
        return binding.root
    }
}