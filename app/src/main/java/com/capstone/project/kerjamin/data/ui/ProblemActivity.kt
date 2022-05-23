package com.capstone.project.kerjamin.data.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.capstone.project.kerjamin.data.ui.order.DetailOrderActivity
import com.capstone.project.kerjamin.databinding.ActivityProblemBinding

class ProblemActivity : AppCompatActivity() {

    private lateinit var binding : ActivityProblemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProblemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Form"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.btnStart.setOnClickListener {
            val view = Intent(this@ProblemActivity, DetailOrderActivity::class.java)
            startActivity(view)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}