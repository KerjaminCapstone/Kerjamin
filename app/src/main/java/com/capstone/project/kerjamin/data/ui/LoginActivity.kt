package com.capstone.project.kerjamin.data.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.capstone.project.kerjamin.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.btnLogin.setOnClickListener {
            val view = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(view)
            finish()
        }
        binding.btnRegister.setOnClickListener {
            val view = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(view)
            finish()
        }
    }
}