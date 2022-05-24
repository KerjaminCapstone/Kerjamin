package com.capstone.project.kerjamin.data.database.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.capstone.project.kerjamin.data.database.model.LoginModel
import com.capstone.project.kerjamin.data.database.model.RegisterModel
import com.capstone.project.kerjamin.data.database.preference.ClientPreferences
import com.capstone.project.kerjamin.data.database.response.ResponseLogin
import com.capstone.project.kerjamin.data.ui.auth.ClientAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClientViewModel @Inject constructor(private val clientAuth: ClientAuth, private val preferences: ClientPreferences) : ViewModel() {

    val responAccount: LiveData<Boolean> = clientAuth.responAccount
    val showLoading: LiveData<Boolean> = clientAuth.showLoading
    val clientLogin: LiveData<ResponseLogin> = clientAuth.login

    fun clientRegister(registerModel: RegisterModel) {
        clientAuth.registerAccount(registerModel)
    }

    fun clientLogin(loginModel: LoginModel) {
        clientAuth.loginAccount(loginModel)
    }

    fun tokenGet(): LiveData<ResponseLogin> {
        return preferences.getToken().asLiveData()
    }

    fun tokenSave(login: ResponseLogin){
        viewModelScope.launch {
            preferences.saveToken(ResponseLogin(login.message, login.token))
        }
    }

    fun tokenClear() {
        viewModelScope.launch {
            preferences.logOut()
        }
    }
}