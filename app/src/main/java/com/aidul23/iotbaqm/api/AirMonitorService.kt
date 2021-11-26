package com.aidul23.iotbaqm.api

import com.aidul23.iotbaqm.models.AirMonitor
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AirMonitorService {
    @GET("feeds.json?api_key=NG3T7X6EA1F3OR8W")
    suspend fun getData() : Response<AirMonitor>
}