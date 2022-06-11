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
import androidx.recyclerview.widget.RecyclerView
import com.capstone.project.kerjamin.R
import com.capstone.project.kerjamin.data.ui.list.adapter.FreelancerAdapter
import com.capstone.project.kerjamin.data.ui.auth.ClientPreferences
import com.capstone.project.kerjamin.data.ui.list.viewmodel.FreelancerViewModel
import com.capstone.project.kerjamin.data.database.ViewModelFactory
import com.capstone.project.kerjamin.data.ui.auth.login.LoginActivity
import com.capstone.project.kerjamin.data.ui.detail.freelancer.DetailFreelancerActivity
import com.capstone.project.kerjamin.data.ui.list.adapter.MainAdapter
import com.capstone.project.kerjamin.data.ui.list.model.Freelancer
import com.capstone.project.kerjamin.data.ui.maps.MapsActivity
import com.capstone.project.kerjamin.databinding.ActivityFreelancerBuilderBinding

class FreelancerBuilderActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFreelancerBuilderBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var freelancerList : ArrayList<Freelancer>
    private lateinit var adapter : MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFreelancerBuilderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Tukang Bangunan"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initRecyclerView()
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
        recyclerView = findViewById(R.id.rv_freelancer_builder)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)

        freelancerList = ArrayList()

        freelancerList.add(Freelancer(R.drawable.name, "Dadang Supriyatna", "Tukang cor", "1 Km", "5"))
        freelancerList.add(Freelancer(R.drawable.name, "Maman", "Tukang alumunium", "1.2 Km", "2"))
        freelancerList.add(Freelancer(R.drawable.name, "Daniel Celo", "Tukang besi", "3 Km", "2"))

        adapter = MainAdapter(freelancerList)
        recyclerView.adapter = adapter

        adapter.onItemClick = {
            val intent = Intent(this, DetailFreelancerActivity::class.java)
            intent.putExtra("freelancer", it)
            startActivity(intent)
        }
    }
}