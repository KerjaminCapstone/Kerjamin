package com.capstone.project.kerjamin.data.ui.detail.client

import android.util.Log
import androidx.lifecycle.*
import com.capstone.project.kerjamin.data.api.ApiConfiguration
import com.capstone.project.kerjamin.data.ui.auth.ClientPreferences
import com.capstone.project.kerjamin.data.ui.auth.response.ResponseLogin
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ClientViewModel (private val preferences: ClientPreferences) : ViewModel() {

    val dataClient = MutableLiveData<ArrayList<ClientModel>?>()

    fun getClient(): LiveData<ResponseLogin> {
        return preferences.getToken().asLiveData()
    }

    fun getDetailClient(): MutableLiveData<ArrayList<ClientModel>?>{
        return dataClient
    }

    fun setToken(tokenAuthentication : String){
        val hardcodeToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1aWQiOiJDTC1nYXVwU1IzbSIsInJvbGVfaWRfMSI6IkNMIiwiZXhwIjoxNjg2MjI2NjUxfQ.SfC4ejGzGyU5--ttSAfGGPZq3-5lbvdG8JhS2KRqO5c"
        Log.d(this@ClientViewModel::class.java.simpleName, tokenAuthentication)
        ApiConfiguration().getApiClient().getClient(header = "application/json", token = "Bearer $tokenAuthentication")
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

    fun tokenClear() {
        viewModelScope.launch {
            preferences.logOut()
        }
    }
}