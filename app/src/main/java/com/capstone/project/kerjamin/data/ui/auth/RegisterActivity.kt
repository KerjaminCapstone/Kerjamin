package com.capstone.project.kerjamin.data.ui.auth

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.widget.doOnTextChanged
import com.capstone.project.kerjamin.R
import com.capstone.project.kerjamin.data.api.ApiConfiguration
import com.capstone.project.kerjamin.data.database.model.RegisterModel
import com.capstone.project.kerjamin.data.database.response.ResponseRegister
import com.capstone.project.kerjamin.data.database.viewmodel.ClientViewModel
import com.capstone.project.kerjamin.data.ui.MenuActivity
import com.capstone.project.kerjamin.databinding.ActivityRegisterBinding
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bindingConfigure()

        supportActionBar?.hide()

    }

    private fun bindingConfigure() {
        binding.edtPassword.doOnTextChanged { text, _, _, _ ->
            if (text!!.length < 6) {
                binding.passwordInput.error = "Password tidak boleh kurang dari 6 huruf"
            }
                binding.passwordInput.error = null
            }

        binding.btnRegister.setOnClickListener {

            val nikAccount = binding.edtNik.text.toString()
            val username = binding.edtUsername.text.toString()
            val nameAccount = binding.edtName.text.toString()
            val addressAccount = binding.edtAddress.text.toString()
            val genderAccount = binding.edtGender.text.toString()
            val bornDate = binding.edtBorndate.text.toString()
            val phoneAccount = binding.edtPhone.text.toString()
            val emailAccount = binding.edtEmail.text.toString()
            val passwordAccount = binding.edtPassword.text.toString()


            when {

                nikAccount.isEmpty() -> {
                    binding.nikInput.error = getString(R.string.empty_nik)
                }
                username.isEmpty() -> {
                    binding.usernameInput.error = getString(R.string.empty_username)
                }
                nameAccount.isEmpty() -> {
                    binding.nameInput.error = getString(R.string.empty_name)
                }
                addressAccount.isEmpty() -> {
                    binding.addressInput.error = getString(R.string.empty_address)
                }
                genderAccount.isEmpty() -> {
                    binding.genderInput.error = getString(R.string.empty_gender)
                }
                bornDate.isEmpty() -> {
                    binding.borndateInput.error = getString(R.string.empty_borndate)
                }
                phoneAccount.isEmpty() -> {
                    binding.phoneInput.error = getString(R.string.empty_phone)
                }
                emailAccount.isEmpty() -> {
                    binding.emailInput.error = getString(R.string.empty_email)
                }
                passwordAccount.isEmpty() -> {
                    binding.passwordInput.error = getString(R.string.empty_password)
                }
                else ->{
                    binding.emailInput.error = validEmail()

                }

            }

        }


    }



    private fun validEmail():String? {
        val emailValid = binding.edtEmail.text.toString()
        if (!Patterns.EMAIL_ADDRESS.matcher(emailValid).matches()) {
            return getString(R.string.invalid_email)
        } else {
            val registrationIntentAccount = Intent(this@RegisterActivity, LoginActivity::class.java)
            startActivity(registrationIntentAccount)
            finish()
            registrationAccount()

        }
        return null
    }


    private fun registrationAccount() {
        val accountNik = binding.edtNik.text.toString().trim()
        val accountUsername = binding.edtUsername.text.toString().trim()
        val accountName = binding.edtName.text.toString().trim()
        val accountAddress = binding.edtAddress.text.toString().trim()
        val accountGender = binding.edtGender.text.toString().trim()
        val borndate = binding.edtBorndate.text.toString().trim()
        val accountPhone = binding.edtPhone.text.toString().trim()
        val accountEmail = binding.edtEmail.text.toString().trim()
        val accountPassword = binding.edtPassword.text.toString().trim()
        val accountRole = "client"


        showLoading(true)

        ApiConfiguration().getApiClient().registerClient("application/json",
            RegisterModel(
                accountNik,
                accountUsername,
                accountName,
                accountAddress,
                accountGender,
                borndate,
                accountPhone,
                accountEmail,
                accountPassword,
                accountRole))
            .enqueue(object : Callback<ResponseRegister> {
                override fun onFailure(call: Call<ResponseRegister>, t: Throwable) {
                    showLoading(false)
                    Log.d("failure: ", t.message.toString())
                }

                override fun onResponse(
                    call: Call<ResponseRegister>,
                    response: Response<ResponseRegister>,
                ) {
                    if (response.code() == 201) {
                        showLoading(true)
                        Toast.makeText(
                            applicationContext,
                            getString(R.string.success_register),
                            Toast.LENGTH_LONG
                        ).show()
                        finish()

                    } else {
                        showLoading(true)
                        Toast.makeText(
                            applicationContext,
                            getString(R.string.failed_register),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

            })
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}