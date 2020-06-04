package com.huishan.trafficimages.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.huishan.trafficimages.R
import com.huishan.trafficimages.viewmodel.TransportViewModel
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
        viewModel.cameras.observe(this, Observer { cameras ->
            for (camera in cameras){
                val marker = mMap.addMarker(
                    MarkerOptions()
                        .position(LatLng(camera.location.latitude, camera.location.longitude))
                        .title(camera.camera_id)
                )
                marker.tag = camera.image
            }
            mMap.setOnMarkerClickListener { marker ->
                //marker.tag as String
                marker.tag
                return@setOnMarkerClickListener false
            }
        })
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val sgp = LatLng(1.3619722, 103.8431686)
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sgp))
        mMap.setMinZoomPreference(10f)
        viewModel.getTrafficImages()
    }
}
