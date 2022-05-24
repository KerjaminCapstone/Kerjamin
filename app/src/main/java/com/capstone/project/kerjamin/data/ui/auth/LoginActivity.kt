package com.capstone.project.kerjamin.data.ui.auth

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.viewModels
import com.capstone.project.kerjamin.R
import com.capstone.project.kerjamin.data.database.model.LoginModel
import com.capstone.project.kerjamin.data.database.viewmodel.ClientViewModel
import com.capstone.project.kerjamin.data.ui.MenuActivity
import com.capstone.project.kerjamin.databinding.ActivityLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel by viewModels<ClientViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        validateButton()
        accountToken()
        style()

        viewModel.responAccount.observe(this) {
            if (!it) {
                Toast.makeText(this, R.string.failed_login, Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.showLoading.observe(this) {
            showLoading(it)
        }
    }

    private fun validateButton() {
        binding.apply {
            btnLogin.setOnClickListener {
                accountLogin()
            }
            btnRegister.setOnClickListener {
                startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
            }
        }
    }

    private fun validateEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun accountLogin() {
        val accountEmail = binding.edtEmail.text.toString()
        val accountPassword = binding.edtPassword.text.toString()
        val accountRole = "client"

        when {
            accountEmail.isEmpty() -> {
                binding.emailInput.error = getString(R.string.empty_email)
            }
            accountPassword.isEmpty() -> {
                binding.passwordInput.error = getString(R.string.empty_password)
            }
            else -> {
                if(validateEmail(accountEmail)) {
                    viewModel.clientLogin(LoginModel(accountEmail, accountPassword, accountRole))
                } else {
                    Toast.makeText(this, R.string.invalid_email, Toast.LENGTH_SHORT).show()
                }

                viewModel.clientLogin.observe(this) {
                    viewModel.tokenSave(it)
                }
            }
        }
    }

    private fun accountToken() {
        viewModel.tokenGet().observe(this) {
            if (it.token.trim() != "") {
                val intent = Intent(this@LoginActivity, MenuActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
                finish()
            }
        }
    }

    private fun style() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}