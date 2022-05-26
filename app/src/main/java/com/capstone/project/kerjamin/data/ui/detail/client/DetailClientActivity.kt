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
import com.capstone.project.kerjamin.data.ui.auth.RegisterActivity
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
            val view = Intent(this@DetailClientActivity, LoginActivity::class.java)
            startActivity(view)
        }
    }
}