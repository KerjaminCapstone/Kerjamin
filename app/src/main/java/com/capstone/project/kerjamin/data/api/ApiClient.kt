package com.capstone.project.kerjamin.data.api

import com.capstone.project.kerjamin.data.ui.auth.login.LoginModel
import com.capstone.project.kerjamin.data.ui.auth.register.RegisterModel
import com.capstone.project.kerjamin.data.ui.detail.client.ClientResponse
import com.capstone.project.kerjamin.data.ui.list.response.FreelancerResponse
import com.capstone.project.kerjamin.data.ui.auth.response.ResponseLogin
import com.capstone.project.kerjamin.data.ui.auth.response.ResponseRegister
import com.capstone.project.kerjamin.data.ui.detail.freelancer.DetailFreelancerResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiClient {

    @POST("auth/sign-up/client")
    fun registerClient(@Header("Content-type") header: String, @Body register : RegisterModel): Call<ResponseRegister>

    @POST("auth/login")
    fun loginClient(@Header("Content-type") header: String, @Body login : LoginModel): Call<ResponseLogin>

    @GET("client/me")
    fun getClient(
        @Header("Content-type") header: String,
        @Header("Authorization") token: String
    ): Call<ClientResponse>

    @GET("client/search/freelance/SE/1")
    fun getFreelancerService(
        @Header("Content-type") header: String,
        @Header("Authorization") token: String,
    ): Call<FreelancerResponse>

    @GET("client/search/freelance/JK/1")
    fun getFreelancerCleaner(
        @Header("Content-type") header: String,
        @Header("Authorization") token: String,
    ): Call<FreelancerResponse>

    @GET("client/search/freelance/TB/1")
    fun getFreelancerBuilder(
        @Header("Content-type") header: String,
        @Header("Authorization") token: String,
    ): Call<FreelancerResponse>

    @GET("client/freelance/1")
    fun getDetailFreelancer(
        @Header("Content-type") header: String,
        @Header("Authorization") token: String,
    ): Call<DetailFreelancerResponse>
}