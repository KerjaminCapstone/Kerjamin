package com.capstone.project.kerjamin.data.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.capstone.project.kerjamin.databinding.ActivityJobdeskBinding

class JobdeskActivity : AppCompatActivity() {

    private lateinit var binding : ActivityJobdeskBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJobdeskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Form"
        actionBar?.setDisplayHomeAsUpEnabled(true)

        binding.btnStart.setOnClickListener {
            val view = Intent(this@JobdeskActivity, DetailJobActivity::class.java)
            startActivity(view)
        }
    }
}