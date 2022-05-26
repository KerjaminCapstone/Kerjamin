package com.capstone.project.kerjamin.data.ui.ui.home

import androidx.lifecycle.*
import com.capstone.project.kerjamin.data.database.model.ClientModel
import com.capstone.project.kerjamin.data.database.preference.ClientPreferences
import com.capstone.project.kerjamin.data.database.response.ResponseLogin
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