package com.capstone.project.kerjamin.data.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.capstone.project.kerjamin.databinding.ActivityDetailClientBinding

class DetailClientActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailClientBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailClientBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Informasi Akun"
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }
}