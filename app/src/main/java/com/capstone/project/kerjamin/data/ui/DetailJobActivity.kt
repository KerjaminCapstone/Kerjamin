package com.capstone.project.kerjamin.data.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.capstone.project.kerjamin.databinding.ActivityDetailJobBinding

class DetailJobActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailJobBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailJobBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnTransfer.setOnClickListener {
            val view = Intent(this@DetailJobActivity, ReviewActivity::class.java)
            startActivity(view)
        }
    }
}