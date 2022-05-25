package com.capstone.project.kerjamin.data.ui.detail.client

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.capstone.project.kerjamin.data.database.model.ClientModel
import com.capstone.project.kerjamin.data.database.preference.ClientPreferences
import com.capstone.project.kerjamin.data.database.viewmodel.ClientViewModel
import com.capstone.project.kerjamin.data.database.viewmodel.ViewModelFactory
import com.capstone.project.kerjamin.data.ui.auth.LoginActivity
import com.capstone.project.kerjamin.data.ui.problem.ProblemActivity
import com.capstone.project.kerjamin.databinding.ActivityDetailClientBinding

class DetailClientActivity : AppCompatActivity() {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "token")

    private lateinit var binding: ActivityDetailClientBinding
    private lateinit var viewModel: ClientViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailClientBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Informasi Akun"

        binding.btnLogout.setOnClickListener {
            viewModel.tokenClear()
            finish()
        }

        val preferences = ClientPreferences.getInstanceClient(dataStore)
        viewModel = ViewModelProvider(
            this, ViewModelFactory(preferences)
        )[ClientViewModel::class.java]

        viewModel.tokenGet().observe(this){client ->
            if(!client.error){
                val intentStory = Intent(this@DetailClientActivity, LoginActivity::class.java)
                intentStory.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intentStory)
            }
            viewModel.setToken(token = client.token)
        }

        viewDetailClient()
    }

    private fun viewDetailClient(){
        val detailClient = intent.getParcelableExtra<ClientModel>("clientData") as ClientModel
        binding.tvNik.text = detailClient.nik
        binding.tvName.text = detailClient.name
        binding.tvGender.text = detailClient.is_male.toString()
        binding.tvAddress.text = detailClient.address
        binding.tvPhone.text = detailClient.no_wa
        binding.tvEmail.text = detailClient.email
    }
}