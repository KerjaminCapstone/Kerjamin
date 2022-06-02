package com.capstone.project.kerjamin.data.ui.list

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.capstone.project.kerjamin.R
import com.capstone.project.kerjamin.data.database.adapter.FreelancerAdapter
import com.capstone.project.kerjamin.data.ui.auth.login.LoginActivity
import com.capstone.project.kerjamin.data.ui.detail.freelancer.DetailFreelancerActivity
import com.capstone.project.kerjamin.data.ui.maps.MapsActivity
import com.capstone.project.kerjamin.databinding.ActivityFreelancerArsitecBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import kotlin.random.Random

class FreelancerArsitecActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFreelancerArsitecBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var freelancerList : ArrayList<Freelancer>
    private lateinit var adapter : FreelancerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFreelancerArsitecBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()

        supportActionBar?.title = "Arsitek Bangunan"
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
        recyclerView = findViewById(R.id.rv_freelancer_arsitec)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)

        freelancerList = ArrayList()

        freelancerList.add(Freelancer(R.drawable.name, "Dadang Supriyatna", "Tukang cat", "1 Km", "5"))
        freelancerList.add(Freelancer(R.drawable.name, "Maman", "Tukang alumunium", "1.2 Km", "2"))
        freelancerList.add(Freelancer(R.drawable.name, "Aldi Rahmadi", "Tukang cor", "1,5 Km", "3"))
        freelancerList.add(Freelancer(R.drawable.name, "Daniel Celo", "Tukang besi", "1.6 Km", "2"))
        freelancerList.add(Freelancer(R.drawable.name, "Nur Ikhsan", "Tukang granit", "1.7 Km", "5"))
        freelancerList.add(Freelancer(R.drawable.name, "Fahrurrozi", "Tukang listrik", "2 Km", "5"))

        adapter = FreelancerAdapter(freelancerList)
        recyclerView.adapter = adapter

    }
}