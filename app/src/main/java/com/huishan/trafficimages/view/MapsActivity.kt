package com.huishan.trafficimages.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.huishan.trafficimages.R
import com.huishan.trafficimages.model.Camera
import com.huishan.trafficimages.viewmodel.TransportViewModel
import kotlinx.android.synthetic.main.activity_maps.*
import org.koin.android.viewmodel.ext.android.viewModel

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private val viewModel: TransportViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val sgp = LatLng(1.3619722, 103.8431686)
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sgp))
        mMap.setMinZoomPreference(10f)
        viewModel.getTrafficImages()

        viewModel.cameras.observe(this, Observer { cameras ->
            for (camera in cameras){
                val marker = mMap.addMarker(
                    MarkerOptions()
                        .position(LatLng(camera.location.latitude, camera.location.longitude))
//                        .title(camera.camera_id)
                )
                marker.tag = camera
            }
            mMap.setOnMarkerClickListener { marker ->
                val modalBottomSheet = CameraImageFragment(marker.tag as Camera)
                modalBottomSheet.show(supportFragmentManager, CameraImageFragment.TAG)
                return@setOnMarkerClickListener false
            }
        })

        refresh.setOnClickListener {
            viewModel.getTrafficImages()
        }
    }
}
