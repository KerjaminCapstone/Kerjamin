package com.capstone.project.kerjamin.data.ui.detail.client

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.capstone.project.kerjamin.data.ui.auth.ClientPreferences
import com.capstone.project.kerjamin.data.database.ViewModelFactory
import com.capstone.project.kerjamin.data.ui.auth.login.LoginActivity
import com.capstone.project.kerjamin.databinding.ActivityDetailClientBinding

class DetailClientActivity : AppCompatActivity() {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "tokenClient")

    private lateinit var binding: ActivityDetailClientBinding
    private lateinit var viewModel : ClientViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailClientBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Informasi Akun"

        setupViewModel()
        validateButton()
    }

    private fun validateButton(){
        binding.btnLogout.setOnClickListener {
            viewModel.tokenClear()
            showLoading(true)
                val intent = Intent(this@DetailClientActivity, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()
        }

    }

    private fun setupViewModel() {
        val preferences = ClientPreferences.getInstanceClient(dataStore)
        viewModel =
            ViewModelProvider(this, ViewModelFactory(preferences))[ClientViewModel::class.java]
        viewModel.getClient().observe(this){ client ->
            binding.tvNik.text = client.token
        }
    }

    private fun showLoading(state:Boolean){
        if (state){
            binding.progressBar.visibility = View.VISIBLE
        }else{
            binding.progressBar.visibility= View.GONE
        }
    }
}