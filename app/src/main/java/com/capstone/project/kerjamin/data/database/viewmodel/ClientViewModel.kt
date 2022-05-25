package com.capstone.project.kerjamin.data.database.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.capstone.project.kerjamin.data.database.model.LoginModel
import com.capstone.project.kerjamin.data.database.model.RegisterModel
import com.capstone.project.kerjamin.data.database.preference.ClientPreferences
import com.capstone.project.kerjamin.data.database.response.ResponseLogin
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class ClientViewModel (private val preferences: ClientPreferences) : ViewModel(){

    fun tokenSave(login: ResponseLogin){
        viewModelScope.launch {
            preferences.saveToken(ResponseLogin(login.error, login.message, login.token))
        }
    }


    fun tokenGet(): LiveData<ResponseLogin>{
        return preferences.getToken().asLiveData()

    }

    fun tokenClear() {
        viewModelScope.launch {
            preferences.logOut()
        }
    }
}