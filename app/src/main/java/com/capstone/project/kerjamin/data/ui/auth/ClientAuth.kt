package com.capstone.project.kerjamin.data.ui.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.capstone.project.kerjamin.data.api.ApiClient
import com.capstone.project.kerjamin.data.database.model.LoginModel
import com.capstone.project.kerjamin.data.database.model.RegisterModel
import com.capstone.project.kerjamin.data.database.response.ResponseLogin
import com.capstone.project.kerjamin.data.database.response.ResponseRegister
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class ClientAuth @Inject constructor(private val apiClient: ApiClient) {

    private val _loadData = MutableLiveData<Boolean>()
    val showLoading: LiveData<Boolean> = _loadData
    private val _responseData = MutableLiveData<Boolean>()
    val responAccount: LiveData<Boolean> = _responseData
    private val _login = MutableLiveData<ResponseLogin>()
    val login: LiveData<ResponseLogin> = _login

    fun registerAccount(registerModel: RegisterModel) {
        _loadData.value=true
        val apiService = apiClient.registerClient(registerModel)
        apiService.enqueue(object : Callback<ResponseRegister> {
            override fun onResponse(
                call: Call<ResponseRegister>,
                response: Response<ResponseRegister>
            ) {
                _loadData.value=false
                val responseBody = response.body()
                if (response.isSuccessful && responseBody != null) {
                    _responseData.value = true

                    Log.d("ResponseAccount", "onResponse: ${responseBody.message}")
                } else {
                    _responseData.value = false
                }
            }

            override fun onFailure(call: Call<ResponseRegister>, t: Throwable) {
                _loadData.value=false
                Log.d("ResponseAccount", "onFailure: ${t.message}")
            }

        })
    }

    fun loginAccount(loginModel: LoginModel) {
        _loadData.value=true
        val apiService = apiClient.loginClient("application/json",loginModel)
        apiService.enqueue(object : Callback<ResponseLogin> {
            override fun onResponse(call: Call<ResponseLogin>, response: Response<ResponseLogin>) {
                _loadData.value=false
                val responseBody = response.body()
                if (response.isSuccessful && responseBody != null) {
                    _responseData.value = true

                    Log.d("ResponseSuccess", "onResponse: ${responseBody.token}")
                } else {
                    _responseData.value = false
                }
            }

            override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
                _loadData.value=false
                Log.e("ResponseError", "onFailure: ${t.message}")
                t.printStackTrace()
            }
        })
    }
}