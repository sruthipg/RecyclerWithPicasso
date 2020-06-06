package com.example.accubitsapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.accubitsapp.repository.HomeRepository
import com.example.accubitsapp.utils.lazyDeferred
import kotlinx.coroutines.Job

class HomeViewModel(private val repository : HomeRepository) : ViewModel() {
//    private lateinit var job: Job
//    fun getUserlist() = repository.getUserlist()
    suspend fun getUserlist() = repository.getEmployee()

    val userlist by lazyDeferred {
        repository.getEmployee()
    }
//    override fun onCleared() {
//        super.onCleared()
//        if(::job.isInitialized) job.cancel()
//    }
}