package com.example.giniappsexam.fragments.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.giniappsexam.adapters.GalleryListAdapter
import com.example.giniappsexam.databinding.FragmentGalleryBinding
import com.example.giniappsexam.fragments.BaseFragment


class GalleryFragment : BaseFragment() {

    private lateinit var binding: FragmentGalleryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGalleryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserve()

        galleryViewModel.fetchImages()
        galleryViewModel.loadImages()
    }

    private fun initObserve() {
        galleryViewModel.imagesLiveData.observe(viewLifecycleOwner, Observer { imageList ->
            val galleryListAdapter = GalleryListAdapter(imageList, requireContext())

            binding.recyclerViewImage.adapter = galleryListAdapter
        })
    }
}