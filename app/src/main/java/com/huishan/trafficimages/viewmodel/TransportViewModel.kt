package com.huishan.trafficimages.viewmodel

import android.util.Log
import androidx.annotation.CallSuper
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.huishan.trafficimages.di.rx.ApplicationSchedulerProvider
import com.huishan.trafficimages.di.rx.with
import com.huishan.trafficimages.model.Camera
import com.huishan.trafficimages.repository.TransportRepo
import io.reactivex.disposables.CompositeDisposable
import java.text.SimpleDateFormat
import java.util.Date

class TransportViewModel(
    private val repo: TransportRepo,
    private val scheduler: ApplicationSchedulerProvider
) : ViewModel() {

    val cameras = MutableLiveData<List<Camera>>()
    private val disposables: CompositeDisposable = CompositeDisposable()

    fun getTrafficImages() {
        val dateTime: String = SimpleDateFormat("YYYY-MM-dd'T'HH:mm:ss").format(Date())
        disposables.add(
            repo.getTrafficImages(dateTime).with(scheduler).subscribe({ trafficImagesResponse ->
                cameras.value = trafficImagesResponse.items[0].cameras
            }, {
                Log.e("TransportViewModel", it.localizedMessage)
            })
        )
    }

    @CallSuper
    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}