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
import com.capstone.project.kerjamin.data.database.model.RegisterModel
import com.capstone.project.kerjamin.data.database.viewmodel.ClientViewModel
import com.capstone.project.kerjamin.data.ui.MenuActivity
import com.capstone.project.kerjamin.databinding.ActivityRegisterBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private val viewModel by viewModels<ClientViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        validateButton()
        accountToken()
        style()

        supportActionBar?.hide()

        viewModel.responAccount.observe(this) {
            if (!it) {
                Toast.makeText(this, R.string.failed_register, Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.showLoading.observe(this) {
            showLoading(it)
        }
    }

    private fun validateButton() {
        binding.apply {
            btnRegister.setOnClickListener{
                accountRegister()
            }
        }
    }

    private fun validateEmail(email : String) : Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun accountRegister() {
        val accountNik = binding.edtNik.text.toString().trim()
        val accountUsername = binding.edtUsername.text.toString().trim()
        val accountName = binding.edtName.text.toString().trim()
        val accountAddress = binding.edtAddress.text.toString().trim()
        val accountGender = binding.edtGender.text.toString().trim()
        val accountBornDate = binding.edtBorndate.text.toString().trim()
        val accountPhone = binding.edtPhone.text.toString().trim()
        val accountEmail = binding.edtEmail.text.toString().trim()
        val accountPassword = binding.edtPassword.text.toString().trim()
        val accountRole = "client"
        when {
            accountNik.isEmpty() -> {
                binding.nikInput.error = getString(R.string.empty_nik)
            }
            accountUsername.isEmpty() -> {
                binding.usernameInput.error = getString(R.string.empty_username)
            }
            accountName.isEmpty() -> {
                binding.nameInput.error = getString(R.string.empty_name)
            }
            accountAddress.isEmpty() -> {
                binding.addressInput.error = getString(R.string.empty_address)
            }
            accountGender.isEmpty() -> {
                binding.genderInput.error = getString(R.string.empty_gender)
            }
            accountBornDate.isEmpty() -> {
                binding.borndateInput.error = getString(R.string.empty_borndate)
            }
            accountPhone.isEmpty() -> {
                binding.phoneInput.error = getString(R.string.empty_phone)
            }
            accountEmail.isEmpty() -> {
                binding.emailInput.error = getString(R.string.empty_email)
            }
            accountPassword.isEmpty() -> {
                binding.passwordInput.error = getString(R.string.empty_password)
            }
            else -> {
                if (validateEmail(accountEmail)) {
                    viewModel.clientRegister(RegisterModel(accountNik, accountUsername, accountName, accountAddress, accountGender, accountBornDate, accountPhone, accountEmail, accountPassword, accountRole))
                    Toast.makeText(this, R.string.success_register, Toast.LENGTH_SHORT)
                        .show()
                    val mainIntent =
                        Intent(this@RegisterActivity, LoginActivity::class.java)
                    startActivity(mainIntent)
                } else {
                    Toast.makeText(this, R.string.failed_register, Toast.LENGTH_SHORT)
                        .show()
                }

                viewModel.clientLogin.observe(this) {
                    if (it.token != "") {
                        viewModel.tokenSave(it)
                    }

                }
            }
        }
    }

    private fun accountToken() {
        viewModel.tokenGet().observe(this) {
            if(it.token.trim() != "") {
                val intent = Intent(this@RegisterActivity, MenuActivity::class.java)
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