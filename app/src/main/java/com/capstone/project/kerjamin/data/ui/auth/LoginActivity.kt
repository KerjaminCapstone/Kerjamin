package com.capstone.project.kerjamin.data.ui.auth

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.capstone.project.kerjamin.R
import com.capstone.project.kerjamin.data.api.ApiConfiguration
import com.capstone.project.kerjamin.data.database.model.LoginModel
import com.capstone.project.kerjamin.data.database.preference.ClientPreferences
import com.capstone.project.kerjamin.data.database.response.ResponseLogin
import com.capstone.project.kerjamin.data.database.viewmodel.ClientViewModel
import com.capstone.project.kerjamin.data.database.viewmodel.ViewModelFactory
import com.capstone.project.kerjamin.data.ui.MenuActivity
import com.capstone.project.kerjamin.databinding.ActivityLoginBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "token")

    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel : ClientViewModel
    private lateinit var login : ResponseLogin

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        validateButton()
        setupViewModel()
        loginAccount()

    }

    private fun validateButton() {
        binding.btnRegister.setOnClickListener {
            val view = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(view)
        }
        binding.edtPassword.doOnTextChanged { text, _, _, _ ->
            if (text!!.length < 6) {
                binding.passwordInput.error = "Password tidak boleh kurang dari 6 huruf"
            } else
                binding.passwordInput.error = null
        }
    }

    private fun validEmail():String? {
        val emailValid = binding.edtEmail.text.toString()
        if (!Patterns.EMAIL_ADDRESS.matcher(emailValid).matches()) {
            return getString(R.string.invalid_email)
        } else {
            loginAccount()

        }
        return null
    }


    private fun setupViewModel(){
        val preference= ClientPreferences.getInstanceClient(dataStore)
        viewModel = ViewModelProvider(
            this@LoginActivity,
            ViewModelFactory(preference)
        )[ClientViewModel::class.java]

        viewModel.tokenGet().observe(this){login->
            this.login = login
        }
    }

    private fun loginAccount(){
        binding.btnLogin.setOnClickListener {
            val emailAccount = binding.edtEmail.text.toString()
            val passwordAccount = binding.edtPassword.text.toString().trim()
            val roleAccount = "client"

            when {
                emailAccount.isEmpty() -> {
                    binding.emailInput.error = getString(R.string.empty_email)
                }
                passwordAccount.isEmpty() -> {
                    binding.passwordInput.error = getString(R.string.empty_password)
                }
                else -> {
                    binding.emailInput.error = validEmail()

                }

            }

            showLoading(true)
            ApiConfiguration().getApiClient()
                .loginClient("application/json",LoginModel(emailAccount, passwordAccount, roleAccount))
                .enqueue(object : Callback<ResponseLogin> {

                    override fun onResponse(
                        call: Call<ResponseLogin>,
                        response: Response<ResponseLogin>,
                    ) {
                        val responseBody = response.body()
                        if (response.isSuccessful && responseBody != null) {
                            viewModel.tokenSave(ResponseLogin(
                                login.error,
                                login.message,
                                login.token,
                                login.isLogin
                            ))
                            showLoading(false)

                            Toast.makeText(applicationContext,
                                getString((R.string.success_login)),
                                Toast.LENGTH_SHORT).show()
                            val intentClient = Intent(this@LoginActivity, MenuActivity::class.java)
                            intentClient.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                            startActivity(intentClient)
                            finish()
                        } else {

                            showLoading(false)

                            Toast.makeText(applicationContext,
                                getString(R.string.failed_login),
                                Toast.LENGTH_SHORT).show()
                            Log.d(LoginActivity::class.java.simpleName,
                                response.body()?.message.toString())
                        }
                    }

                    override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
                        Log.d("failure: ", t.message.toString())
                    }


                })
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