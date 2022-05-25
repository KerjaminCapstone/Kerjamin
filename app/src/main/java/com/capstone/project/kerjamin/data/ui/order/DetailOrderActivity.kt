package com.capstone.project.kerjamin.data.ui.order

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.capstone.project.kerjamin.R
import com.capstone.project.kerjamin.data.ui.detail.freelancer.DetailFreelancerActivity
import com.capstone.project.kerjamin.data.ui.payment.PaymentActivity
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.whatsapp_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.whatsapp -> {
                val num = "+6289513169767"
                val text = "Hello"

                val installed: Boolean = isAppInstalled("com.whatsapp")
                if (installed) {
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse("https://api.whatsapp.com/send?phone=$num&text=$text")
                    startActivity(intent)
                } else {
                    Toast.makeText(this@DetailOrderActivity, "Whatsapp is not installed!", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed() {
        finish()
    }

    private fun isAppInstalled(s: String): Boolean {
        val packageManager = packageManager
        var is_installed: Boolean
        try {
            packageManager.getPackageInfo(s, PackageManager.GET_ACTIVITIES)
            is_installed = true
        } catch (e: PackageManager.NameNotFoundException) {
            is_installed = false
            e.printStackTrace()
        }
        return is_installed
    }
}