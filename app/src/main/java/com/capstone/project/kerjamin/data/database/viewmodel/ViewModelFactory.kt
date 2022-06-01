package com.capstone.project.kerjamin.data.database.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.capstone.project.kerjamin.data.database.preference.ClientPreferences

class ViewModelFactory (private val preferences: ClientPreferences) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(preferences) as T
            }
            modelClass.isAssignableFrom(ClientViewModel::class.java) -> {
                ClientViewModel(preferences) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
}