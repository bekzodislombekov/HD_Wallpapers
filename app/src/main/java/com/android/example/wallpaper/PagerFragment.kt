package com.android.example.wallpaper

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.android.example.wallpaper.adapters.PhotoAdapter
import com.android.example.wallpaper.databinding.FragmentPagerBinding
import com.android.example.wallpaper.models.pixabay.Hit
import com.android.example.wallpaper.models.pixabay.Photo
import com.android.example.wallpaper.retrofit.ApiClient
import com.android.example.wallpaper.retrofit.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val ARG_PARAM1 = "category_list"

class PagerFragment : Fragment() {
    private lateinit var category: String
    private lateinit var binding: FragmentPagerBinding
    private lateinit var adapter: PhotoAdapter
    private lateinit var apiService: ApiService
    private lateinit var list: ArrayList<Hit>
    private lateinit var photo: Photo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            category = it.getString(ARG_PARAM1)!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPagerBinding.inflate(inflater, container, false)
        apiService = ApiClient.getRetrofit(requireContext()).create(ApiService::class.java)
        list = ArrayList()
        apiService.getPhotos(API_KEY, category, "photo", true).enqueue(object : Callback<Photo> {
            override fun onFailure(call: Call<Photo>, t: Throwable) {

            }

            override fun onResponse(call: Call<Photo>, response: Response<Photo>) {
                list.addAll(ArrayList(response.body()?.hits!!))
                photo = response.body()!!
                adapter.notifyDataSetChanged()
            }
        })

        adapter = PhotoAdapter(list, object : PhotoAdapter.OnItemClickListener {
            override fun onItemListener(hit: Hit, position: Int) {
                val bundle = bundleOf("photo" to photo, "position" to position)
                findNavController().navigate(R.id.imageFragment, bundle)
            }
        })
        binding.rv.adapter = adapter
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String) =
            PagerFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1, param1)
                }
            }
    }
}