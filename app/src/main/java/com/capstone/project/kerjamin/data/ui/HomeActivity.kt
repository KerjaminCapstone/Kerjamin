package com.capstone.project.kerjamin.data.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.capstone.project.kerjamin.R
import com.capstone.project.kerjamin.data.database.preference.ClientPreferences
import com.capstone.project.kerjamin.data.database.response.ResponseLogin
import com.capstone.project.kerjamin.data.database.viewmodel.ClientViewModel
import com.capstone.project.kerjamin.data.database.viewmodel.ViewModelFactory
import com.capstone.project.kerjamin.data.ui.auth.login.LoginActivity
import com.capstone.project.kerjamin.data.ui.auth.register.RegisterActivity
import com.capstone.project.kerjamin.data.ui.list.FreelancerArsitecActivity
import com.capstone.project.kerjamin.data.ui.list.FreelancerBuilderActivity
import com.capstone.project.kerjamin.data.ui.list.FreelancerCleanerActivity
import com.capstone.project.kerjamin.data.ui.list.FreelancerServiceActivity
import com.capstone.project.kerjamin.databinding.ActivityHomeBinding
import com.capstone.project.kerjamin.databinding.ActivityMenuBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding : ActivityHomeBinding
    private lateinit var viewModel : ClientViewModel
    private lateinit var login : ResponseLogin
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "token")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViewModel()

        binding.btnArsitec.setOnClickListener {
            val view = Intent(this@HomeActivity, FreelancerArsitecActivity::class.java)
            startActivity(view)
        }
        binding.btnBuilder.setOnClickListener {
            val view = Intent(this@HomeActivity, FreelancerBuilderActivity::class.java)
            startActivity(view)
        }
        binding.btnCleaner.setOnClickListener {
            val view = Intent(this@HomeActivity, FreelancerCleanerActivity::class.java)
            startActivity(view)
        }
        binding.btnService.setOnClickListener {
            val view = Intent(this@HomeActivity, FreelancerServiceActivity::class.java)
            startActivity(view)
        }
    }

    private fun setupViewModel() {
        val preferences = ClientPreferences.getInstanceClient(dataStore)
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(preferences)
        )[ClientViewModel::class.java]

        viewModel.tokenGet().observe(this) { client ->
            if (!client.isLogin) {
                val intent = Intent(this@HomeActivity, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
            viewModel.setToken(token = client.token)
        }
    }
}