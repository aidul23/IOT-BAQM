package com.aidul23.iotbaqm.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.aidul23.iotbaqm.api.AirMonitorService
import com.aidul23.iotbaqm.models.AirMonitor

class AirMonitorRepository(private val quoteService: AirMonitorService) {

    private val airMonitorLiveData = MutableLiveData<AirMonitor>()

    val quotes : LiveData<AirMonitor>
    get() = airMonitorLiveData

    suspend fun getQuotes() {
        val result = quoteService.getData()
        if (result?.body() != null) {
            airMonitorLiveData.postValue(result.body())
        }
    }
}