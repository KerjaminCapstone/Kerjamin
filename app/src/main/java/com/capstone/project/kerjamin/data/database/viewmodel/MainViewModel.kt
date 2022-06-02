package com.capstone.project.kerjamin.data.database.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.capstone.project.kerjamin.data.api.ApiConfiguration
import com.capstone.project.kerjamin.data.database.model.ClientModel
import com.capstone.project.kerjamin.data.database.preference.ClientPreferences
import com.capstone.project.kerjamin.data.database.response.ClientResponse
import com.capstone.project.kerjamin.data.database.response.ResponseLogin
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainViewModel (private val preferences: ClientPreferences) : ViewModel(){

    fun tokenSave(login: ResponseLogin){
        viewModelScope.launch {
            preferences.saveToken(ResponseLogin(login.error, login.token,login.isLogin))
        }
    }


    fun tokenGet(): LiveData<ResponseLogin>{
        return preferences.getToken().asLiveData()

    }
}