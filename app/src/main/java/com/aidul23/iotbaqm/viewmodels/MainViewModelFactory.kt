package com.aidul23.iotbaqm.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aidul23.iotbaqm.repository.AirMonitorRepository

class MainViewModelFactory(private val repository: AirMonitorRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}