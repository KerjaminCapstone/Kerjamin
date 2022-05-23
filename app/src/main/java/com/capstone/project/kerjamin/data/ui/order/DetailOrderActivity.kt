package com.capstone.project.kerjamin.data.ui.order

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.capstone.project.kerjamin.R
import com.capstone.project.kerjamin.data.ui.detail.DetailFreelancerActivity
import com.capstone.project.kerjamin.data.ui.PaymentActivity
import com.capstone.project.kerjamin.databinding.ActivityDetailOrderBinding

class DetailOrderActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailOrderBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Detail Pesanan"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.btnConfirm.setOnClickListener {
            val view = Intent(this@DetailOrderActivity, PaymentActivity::class.java)
            startActivity(view)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.whatsapp_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.whatsapp -> {
                val intent = Intent(this, DetailFreelancerActivity::class.java)
                startActivity(intent)
            }
        }
        return true
    }
}