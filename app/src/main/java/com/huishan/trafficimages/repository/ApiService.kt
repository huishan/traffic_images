package com.huishan.trafficimages.repository

import com.huishan.trafficimages.model.TrafficImagesResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiService {

    @GET("transport/traffic-images")
    fun getTrafficImages(
        @Header("accept") accept: String,
        @Header("Content-Type") contentType: String,
        @Query("date_time") dateTime: String?
    ): Single<TrafficImagesResponse>
}