package com.capstone.project.kerjamin.data.ui.ui.home

import androidx.lifecycle.*
import com.capstone.project.kerjamin.data.ui.auth.ClientPreferences
import com.capstone.project.kerjamin.data.ui.auth.response.ResponseLogin
import kotlinx.coroutines.launch

class HomeViewModel (private val preferences: ClientPreferences) : ViewModel(){

    fun getAccount(): LiveData<ResponseLogin> {
        return preferences.getToken().asLiveData()
    }

    fun logout() {
        viewModelScope.launch {
            preferences.logOut()
        }
    }
}