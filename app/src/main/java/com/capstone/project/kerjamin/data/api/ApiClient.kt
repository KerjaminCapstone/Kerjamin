package com.capstone.project.kerjamin.data.api

import com.capstone.project.kerjamin.data.ui.auth.login.LoginModel
import com.capstone.project.kerjamin.data.ui.auth.register.RegisterModel
import com.capstone.project.kerjamin.data.database.response.ClientResponse
import com.capstone.project.kerjamin.data.database.response.ResponseLogin
import com.capstone.project.kerjamin.data.database.response.ResponseRegister
import retrofit2.Call
import retrofit2.http.*

interface ApiClient {

    @POST("auth/sign-up")
    fun registerClient(@Header("Content-type") header: String, @Body register : RegisterModel): Call<ResponseRegister>


    @POST("auth/login")
    fun loginClient(@Header("Content-type") header: String, @Body login : LoginModel): Call<ResponseLogin>

    @GET("client/me")
    fun getClient(@Header("Content-type") header: String, @Header("Authorization") token: String): Call<ClientResponse>
}