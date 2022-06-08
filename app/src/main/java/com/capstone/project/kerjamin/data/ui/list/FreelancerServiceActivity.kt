package com.capstone.project.kerjamin.data.ui.list

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
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
import com.capstone.project.kerjamin.data.ui.maps.MapsActivity
import com.capstone.project.kerjamin.databinding.ActivityFreelancerServiceBinding

class FreelancerServiceActivity : AppCompatActivity() {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "tokenClient")
    private lateinit var binding: ActivityFreelancerServiceBinding
    private lateinit var viewModel: FreelancerViewModel
    private lateinit var adapterBuilder: FreelancerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFreelancerServiceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Servis Elektronik"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initRecyclerView()
        listFreelancer()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.freelance_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.maps -> {
                val mapIntent = Intent(this, MapsActivity::class.java)
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
        binding.rvFreelancerService.layoutManager = LinearLayoutManager(this)
        adapterBuilder = FreelancerAdapter()
        binding.rvFreelancerService.adapter = adapterBuilder
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun listFreelancer(){
        val preferences = ClientPreferences.getInstanceClient(dataStore)
        viewModel = ViewModelProvider(
            this, ViewModelFactory(preferences)
        )[FreelancerViewModel::class.java]

        viewModel.getClient().observe(this){client ->
            if(!client.isLogin){
                val intentClient = Intent(this@FreelancerServiceActivity, LoginActivity::class.java)
                intentClient.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intentClient)
            }
            showLoading(true)
            viewModel.setFreelancerService(token = client.token)
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