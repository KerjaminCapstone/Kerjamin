package com.capstone.project.kerjamin.data.api

import com.capstone.project.kerjamin.data.database.model.LoginModel
import com.capstone.project.kerjamin.data.database.model.RegisterModel
import com.capstone.project.kerjamin.data.database.response.ResponseLogin
import com.capstone.project.kerjamin.data.database.response.ResponseRegister
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiClient {

    @POST("/auth/sign-up")
    fun registerClient(@Header("Content-type") header: String, @Body register : RegisterModel): Call<ResponseRegister>


    @POST("/auth/login")
    fun loginClient(@Header("Content-type") header: String, @Body login : LoginModel): Call<ResponseLogin>

}