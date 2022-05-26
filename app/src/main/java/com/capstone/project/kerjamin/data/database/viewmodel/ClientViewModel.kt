package com.capstone.project.kerjamin.data.database.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.capstone.project.kerjamin.data.api.ApiConfiguration
import com.capstone.project.kerjamin.data.database.model.ClientModel
import com.capstone.project.kerjamin.data.database.model.LoginModel
import com.capstone.project.kerjamin.data.database.model.RegisterModel
import com.capstone.project.kerjamin.data.database.preference.ClientPreferences
import com.capstone.project.kerjamin.data.database.response.ClientResponse
import com.capstone.project.kerjamin.data.database.response.ResponseLogin
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


class ClientViewModel (private val preferences: ClientPreferences) : ViewModel(){

    val dataClient = MutableLiveData<ArrayList<ClientModel>?>()

    fun tokenSave(login: ResponseLogin){
        viewModelScope.launch {
            preferences.saveToken(login)
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

    fun getClient(): MutableLiveData<ArrayList<ClientModel>?>{
        return dataClient
    }

    fun setToken(token : String){
        Log.d(this@ClientViewModel::class.java.simpleName, token)
        ApiConfiguration().getApiClient().getClient("application/json", token = "Bearer $token")
            .enqueue(object : Callback<ClientResponse> {
                override fun onResponse(
                    call: Call<ClientResponse>,
                    response: Response<ClientResponse>
                ) {
                    if (response.isSuccessful) {
                        dataClient.postValue(response.body()?.data)
                    } else {
                        dataClient.postValue(null)
                    }
                }
                override fun onFailure(call: Call<ClientResponse>, t: Throwable) {
                    dataClient.postValue(null)
                }

            })
    }
}