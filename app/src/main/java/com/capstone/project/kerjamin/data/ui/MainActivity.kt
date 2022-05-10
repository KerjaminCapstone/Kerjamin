package com.capstone.project.kerjamin.data.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.capstone.project.kerjamin.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.setLogo(R.mipmap.ic_launcher);
        supportActionBar?.setDisplayUseLogoEnabled(true);
    }
}