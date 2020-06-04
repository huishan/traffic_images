package com.huishan.trafficimages

import android.app.Application
import com.huishan.trafficimages.di.dataModule
import com.huishan.trafficimages.di.networkModule
import com.huishan.trafficimages.di.viewmodelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TrafficApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@TrafficApplication)
            modules(viewmodelModule, networkModule, dataModule)
        }
    }
}