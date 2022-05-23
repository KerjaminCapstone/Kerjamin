package com.capstone.project.kerjamin.data.ui.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.capstone.project.kerjamin.data.ui.ProblemActivity
import com.capstone.project.kerjamin.databinding.ActivityDetailFreelancerBinding

class DetailFreelancerActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailFreelancerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailFreelancerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Detail Freelancer"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.btnOrder.setOnClickListener {
            val view = Intent(this@DetailFreelancerActivity, ProblemActivity::class.java)
            startActivity(view)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}