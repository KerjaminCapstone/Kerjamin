package com.capstone.project.kerjamin.data.ui.list

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.capstone.project.kerjamin.R
import com.capstone.project.kerjamin.data.ui.detail.DetailFreelancerActivity
import com.capstone.project.kerjamin.databinding.ActivityFreelancerArsitecBinding

class FreelancerArsitecActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFreelancerArsitecBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFreelancerArsitecBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Arsitek Bangunan"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
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