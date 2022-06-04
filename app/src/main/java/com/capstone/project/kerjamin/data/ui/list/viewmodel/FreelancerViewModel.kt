package com.capstone.project.kerjamin.data.ui.list.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.capstone.project.kerjamin.data.api.ApiConfiguration
import com.capstone.project.kerjamin.data.ui.list.model.FreelancerModel
import com.capstone.project.kerjamin.data.ui.auth.ClientPreferences
import com.capstone.project.kerjamin.data.ui.list.response.FreelancerResponse
import com.capstone.project.kerjamin.data.ui.auth.response.ResponseLogin
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FreelancerViewModel (private val  preferences : ClientPreferences) : ViewModel() {

    val freelancerList = MutableLiveData<ArrayList<FreelancerModel>?>()

    fun setFreelancerList (tokenAuthentication : String){
        Log.d(this@FreelancerViewModel::class.java.simpleName, tokenAuthentication)
        ApiConfiguration().getApiClient().getFreelancerBuilder("application/json", token = "Bearer $tokenAuthentication")
            .enqueue(object : Callback<FreelancerResponse>{
                override fun onResponse(
                    call: Call<FreelancerResponse>,
                    response: Response<FreelancerResponse>
                ) {
                    if (response.isSuccessful){
                        freelancerList.postValue(response.body()?.data)
                    } else {
                        freelancerList.postValue(null)
                    }
                }

                override fun onFailure(call: Call<FreelancerResponse>, t: Throwable) {
                    freelancerList.postValue(null)
                }

            })
    }

    fun getFreelancer() : MutableLiveData<ArrayList<FreelancerModel>?>{
        return freelancerList
    }

    fun getClient(): LiveData<ResponseLogin> {
        return preferences.getToken().asLiveData()
    }
}