package com.capstone.project.kerjamin.data.ui.auth

import androidx.lifecycle.*
import com.capstone.project.kerjamin.data.ui.auth.response.ResponseLogin
import kotlinx.coroutines.launch


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