package com.capstone.project.kerjamin.data.ui.order

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.capstone.project.kerjamin.databinding.ActivityDetailOrderCancelBinding

class DetailOrderCancelActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailOrderCancelBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailOrderCancelBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Riwayat Pesanan"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}