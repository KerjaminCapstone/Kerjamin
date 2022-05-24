package com.capstone.project.kerjamin.data.ui.order

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.capstone.project.kerjamin.R
import com.capstone.project.kerjamin.data.ui.review.ReviewActivity
import com.capstone.project.kerjamin.data.ui.detail.freelancer.DetailFreelancerActivity
import com.capstone.project.kerjamin.data.ui.detail.job.DetailJobActivity
import com.capstone.project.kerjamin.databinding.ActivityDetailOrder2Binding

class DetailOrder2Activity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailOrder2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailOrder2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Detail Pesanan"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.btnConfirm.setOnClickListener {
            val view = Intent(this@DetailOrder2Activity, ReviewActivity::class.java)
            startActivity(view)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.detail_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.whatsapp -> {
                val intent = Intent(this, DetailFreelancerActivity::class.java)
                startActivity(intent)
            }
            R.id.list -> {
                val intent = Intent(this, DetailJobActivity::class.java)
                startActivity(intent)
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
}