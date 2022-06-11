package com.capstone.project.kerjamin.data.ui.detail.job

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.capstone.project.kerjamin.R
import com.capstone.project.kerjamin.data.ui.detail.freelancer.DetailFreelancerActivity
import com.capstone.project.kerjamin.data.ui.list.adapter.MainAdapter
import com.capstone.project.kerjamin.data.ui.list.model.Freelancer
import com.capstone.project.kerjamin.databinding.ActivityDetailJobBinding

class DetailJobActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailJobBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var jobList : ArrayList<Job>
    private lateinit var adapter : JobAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailJobBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Detail Pekerjaan"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initRecyclerView()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun initRecyclerView(){
        recyclerView = findViewById(R.id.rv_job)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)

        jobList = ArrayList()

        jobList.add(Job("Mengukur kelembapan tembok"))
        jobList.add(Job("Mengaduk semen dan pasir"))
        jobList.add(Job("Memperbaiki tembok yang retak"))
        jobList.add(Job("Mengecat tembok yang telah diperbaiki"))

        adapter = JobAdapter(jobList)
        recyclerView.adapter = adapter
    }
}