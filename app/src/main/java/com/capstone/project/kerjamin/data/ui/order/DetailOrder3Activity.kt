package com.capstone.project.kerjamin.data.ui.order

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.capstone.project.kerjamin.data.ui.detail.job.DetailJobActivity
import com.capstone.project.kerjamin.databinding.ActivityDetailOrder3Binding

class DetailOrder3Activity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailOrder3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailOrder3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Detail Pesanan"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val num = "+6289513169767"
        val text = "Hello"

        binding.btnWhatsapp.setOnClickListener {
            val installed: Boolean = isAppInstalled("com.whatsapp")
            if (installed) {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse("https://api.whatsapp.com/send?phone=$num&text=$text")
                startActivity(intent)
            } else {
                Toast.makeText(this@DetailOrder3Activity, "Whatsapp is not installed!", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        binding.btnList.setOnClickListener {
            val view = Intent(this@DetailOrder3Activity, DetailJobActivity::class.java)
            startActivity(view)
        }
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