package com.capstone.project.kerjamin.data.ui.auth.register

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import com.capstone.project.kerjamin.R
import com.capstone.project.kerjamin.data.api.ApiConfiguration
import com.capstone.project.kerjamin.data.database.response.ResponseRegister
import com.capstone.project.kerjamin.data.ui.auth.login.LoginActivity
import com.capstone.project.kerjamin.databinding.ActivityRegisterBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.random.Random

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var location : Location? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bindingConfigure()

        supportActionBar?.hide()

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        binding.btnLoc.setOnClickListener { getMyLocation() }

    }

    private fun bindingConfigure() {
        binding.edtPassword.doOnTextChanged { text, _, _, _ ->
            if (text!!.length < 6) {
                binding.passwordInput.error = "Password tidak boleh kurang dari 6 huruf"
            } else
                binding.passwordInput.error = null
        }

        binding.btnRegister.setOnClickListener {

            val nikAccount = binding.edtNik.text.toString()
            val nameAccount = binding.edtName.text.toString()
            val addressAccount = binding.edtAddress.text.toString()
            val genderAccount = binding.edtGender.text.toString()
            val phoneAccount = binding.edtPhone.text.toString()
            val emailAccount = binding.edtEmail.text.toString()
            val passwordAccount = binding.edtPassword.text.toString()
            val coordinate = binding.edtCoordinate.text.toString()


            when {

                nikAccount.isEmpty() -> {
                    binding.nikInput.error = getString(R.string.empty_nik)
                }
                nameAccount.isEmpty() -> {
                    binding.nameInput.error = getString(R.string.empty_name)
                }
                addressAccount.isEmpty() -> {
                    binding.edtAddress.error = getString(R.string.empty_address)
                }
                genderAccount.isEmpty() -> {
                    binding.genderInput.error = getString(R.string.empty_gender)
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
                coordinate.isEmpty() -> {
                    binding.edtCoordinate.error = getString(R.string.empty_coordinate)
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
            registrationAccount()

        }
        return null
    }


    private fun registrationAccount() {
        val accountNik = binding.edtNik.text.toString().trim()
        val accountName = binding.edtName.text.toString().trim()
        val accountAddress = binding.edtAddress.text.toString().trim()
        val accountGender = binding.edtGender.text.toString().trim()
        val accountPhone = binding.edtPhone.text.toString().trim()
        val accountEmail = binding.edtEmail.text.toString().trim()
        val accountPassword = binding.edtPassword.text.toString().trim()
        val accountRole = "CL"
        val latitude = location?.latitude.toString().toRequestBody("text/plain".toMediaType())
        val longitude = location?.longitude.toString().toRequestBody("text/plain".toMediaType())



        showLoading(true)

        ApiConfiguration().getApiClient().registerClient("application/json",
            RegisterModel(
                accountNik,
                accountName,
                accountAddress,
                accountGender,
                accountPhone,
                accountEmail,
                accountPassword,
                accountRole,
                latitude,
                longitude)
        )
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
                        val intentClient = Intent(this@RegisterActivity, LoginActivity::class.java)
                        intentClient.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                        startActivity(intentClient)
                        finish()

                    } else {
                        showLoading(false)
                        Toast.makeText(
                            applicationContext,
                            getString(R.string.failed_register),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

            })
    }

    internal fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSION.all {
        ContextCompat.checkSelfPermission(baseContext, it) == PackageManager.PERMISSION_GRANTED
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSION) {
            if (!allPermissionsGranted()) {
                Toast.makeText(
                    this,
                    R.string.data_error,
                    Toast.LENGTH_SHORT
                ).show()
                finish()
            }
        }
    }

    private fun checkPermission(permission: String): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            permission
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun setupPermission(){
        if (!allPermissionsGranted()) {
            ActivityCompat.requestPermissions(
                this,
                REQUIRED_PERMISSION,
                REQUEST_CODE_PERMISSION
            )
        }
    }

    private fun getMyLocation() {
        if (
            checkPermission(Manifest.permission.ACCESS_FINE_LOCATION) &&
            checkPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
        ) {
            fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
                if (location != null) {
                    val addressName = getAlamat(LatLng(location.latitude, location.longitude))
                    binding.edtCoordinate.setText(addressName)
                } else {
                    Toast.makeText(this@RegisterActivity, R.string.data_error, Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            requestPermissionLauncher.launch(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            )
        }
    }

    private fun getAlamat(latLng: LatLng): String {
        return try {
            val geocoder = Geocoder(this)
            val allAddress = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1)
            if (allAddress.isEmpty()) getString(R.string.data_error) else allAddress[0].getAddressLine(
                0
            )
        } catch (e: Exception) {
            getString(R.string.data_error)
        }
    }

    private fun spesifikAlamat(locationName: String): LatLng {
        return try {
            val randomLatitude = randomCoordinate()
            val randomLongitude = randomCoordinate()

            val geocoder = Geocoder(this)
            val allLocation = geocoder.getFromLocationName(locationName, 1)
            if (allLocation.isEmpty()) {
                LatLng(randomLatitude, randomLongitude)
            } else {
                LatLng(allLocation[0].latitude, allLocation[0].longitude)
            }
        } catch (e: Exception) {
            LatLng(0.0, 0.0)
        }
    }

    private fun randomCoordinate(): Double {
        return Random.nextDouble(15.0, 100.0)
    }

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        when {
            permissions[Manifest.permission.ACCESS_FINE_LOCATION] ?: false -> getMyLocation()
            permissions[Manifest.permission.ACCESS_COARSE_LOCATION] ?: false -> getMyLocation()
            else -> {}
        }
    }

    companion object{
        private val REQUIRED_PERMISSION = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION)
        private const val REQUEST_CODE_PERMISSION = 10
        private var ACCOUNT = "token"
    }
}