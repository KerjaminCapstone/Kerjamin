package com.capstone.project.kerjamin.data.ui.detail.job

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.capstone.project.kerjamin.databinding.ActivityDetailJobBinding

class DetailJobActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailJobBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailJobBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Detail Pekerjaan"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}