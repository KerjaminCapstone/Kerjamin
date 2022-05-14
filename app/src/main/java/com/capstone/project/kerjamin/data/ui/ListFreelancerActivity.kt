package com.capstone.project.kerjamin.data.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import com.capstone.project.kerjamin.R
import com.capstone.project.kerjamin.databinding.ActivityListFreelancerBinding
import com.capstone.project.kerjamin.databinding.ActivityLoginBinding

class ListFreelancerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListFreelancerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListFreelancerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Daftar Freelancer"
        actionBar?.setDisplayHomeAsUpEnabled(true)
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
        return true
    }
}