package com.aidul23.iotbaqm.models

data class AirMonitor(
    val channel: Channel,
    val feeds: List<Feed>
)