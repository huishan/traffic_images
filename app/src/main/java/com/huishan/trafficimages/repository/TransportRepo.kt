package com.huishan.trafficimages.repository

import com.huishan.trafficimages.model.TrafficImagesResponse
import io.reactivex.Single

class TransportRepo(private val apiServices: ApiService) {

    fun getTrafficImages(dateTime: String): Single<TrafficImagesResponse> {
        return apiServices.getTrafficImages("application/json", "application/json", dateTime)
    }
}