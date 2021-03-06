package com.capstone.project.kerjamin.data.ui.list

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.project.kerjamin.R
import com.capstone.project.kerjamin.data.database.ViewModelFactory
import com.capstone.project.kerjamin.data.ui.auth.ClientPreferences
import com.capstone.project.kerjamin.data.ui.auth.login.LoginActivity
import com.capstone.project.kerjamin.data.ui.detail.freelancer.DetailFreelancerActivity
import com.capstone.project.kerjamin.data.ui.list.adapter.FreelancerAdapter
import com.capstone.project.kerjamin.data.ui.list.viewmodel.FreelancerViewModel
import com.capstone.project.kerjamin.databinding.ActivityFreelancerCleanerBinding

class FreelancerCleanerActivity : AppCompatActivity() {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "tokenClient")
    private lateinit var binding : ActivityFreelancerCleanerBinding
    private lateinit var viewModel: FreelancerViewModel
    private lateinit var adapterBuilder: FreelancerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFreelancerCleanerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Jasa Kebersihan"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.freelance_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.maps -> {
                val mapIntent = Intent(this, DetailFreelancerActivity::class.java)
                startActivity(mapIntent)
            }
        }
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed() {
        finish()
    }

    private fun initRecyclerView(){
        binding.rvFreelancerCleaner.layoutManager = LinearLayoutManager(this)
        adapterBuilder = FreelancerAdapter()
        binding.rvFreelancerCleaner.adapter = adapterBuilder
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun listFreelancer(){
        val preferences = ClientPreferences.getInstanceClient(dataStore)
        viewModel = ViewModelProvider(
            this, ViewModelFactory(preferences)
        )[FreelancerViewModel::class.java]

        viewModel.getClient().observe(this){client ->
            if(!client.isLogin){
                val intentClient = Intent(this@FreelancerCleanerActivity, LoginActivity::class.java)
                intentClient.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intentClient)
            }
            showLoading(true)
            viewModel.setFreelancerCleaner(token = client.token)
        }

        viewModel.getFreelancer().observe(this){
            if (it!=null){
                adapterBuilder.setFreelancer(it)
                adapterBuilder.notifyDataSetChanged()
                showLoading(false)
            }else{
                showLoading(false)
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}