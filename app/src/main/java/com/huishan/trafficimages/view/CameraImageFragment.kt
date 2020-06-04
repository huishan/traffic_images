package com.huishan.trafficimages.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.huishan.trafficimages.R
import com.huishan.trafficimages.model.Camera
import kotlinx.android.synthetic.main.fragment_image.*


class CameraImageFragment(camera: Camera) : BottomSheetDialogFragment() {
    private val imageUrl = camera.image
    private val time = camera.timestamp
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_image, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        time_stamp.text = time
        Glide.with(this).load(imageUrl).into(camera_image);
    }
    companion object {
        const val TAG = "CameraImageFragment"
    }
}