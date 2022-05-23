package com.capstone.project.kerjamin.data.ui.order

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.capstone.project.kerjamin.databinding.ActivityDetailOrder3Binding

class DetailOrder3Activity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailOrder3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailOrder3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Detail Pesanan"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}