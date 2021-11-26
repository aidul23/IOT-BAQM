package com.aidul23.iotbaqm.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aidul23.iotbaqm.models.AirMonitor
import com.aidul23.iotbaqm.repository.AirMonitorRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository : AirMonitorRepository) :ViewModel() {
    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getQuotes()
        }
    }

    val airMonitor : LiveData<AirMonitor>
    get() = repository.quotes
}