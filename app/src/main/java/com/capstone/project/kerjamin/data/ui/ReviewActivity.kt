package com.capstone.project.kerjamin.data.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.capstone.project.kerjamin.databinding.ActivityReviewBinding

class ReviewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Review"
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }
}