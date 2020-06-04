package com.huishan.trafficimages.di

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.huishan.trafficimages.di.rx.ApplicationSchedulerProvider
import com.huishan.trafficimages.repository.ApiService
import com.huishan.trafficimages.repository.TransportRepo
import com.huishan.trafficimages.viewmodel.TransportViewModel
import okhttp3.OkHttpClient
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

fun createOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .addNetworkInterceptor(StethoInterceptor())
        .build()
}

fun getApiService(okHttpClient: OkHttpClient, baseUrl: String): ApiService {
    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    return retrofit.create(ApiService::class.java)
}
val networkModule = module {
    single { createOkHttpClient() }
    single { getApiService(get(), "https://api.data.gov.sg/v1/") }
    single { ApplicationSchedulerProvider() }
}

val viewmodelModule = module {
    viewModel { TransportViewModel(get(), get()) }
}

val dataModule = module {
    single { TransportRepo(get()) }
}