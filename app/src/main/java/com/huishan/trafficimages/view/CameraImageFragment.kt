package com.huishan.trafficimages.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.huishan.trafficimages.R
import kotlinx.android.synthetic.main.fragment_image.*


class CameraImageFragment(url: String) : BottomSheetDialogFragment() {
    private val imageUrl = url
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_image, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(this).load(imageUrl).into(camera_image);
    }
    companion object {
        const val TAG = "CameraImageFragment"
    }
}