package com.capstone.project.kerjamin.data.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.capstone.project.kerjamin.R
import com.capstone.project.kerjamin.data.ui.order.DetailOrder2Activity
import com.capstone.project.kerjamin.databinding.ActivityPaymentBinding

class PaymentActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var binding : ActivityPaymentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Detail Pembayaran"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.btnPay.setOnClickListener {
            val view = Intent(this@PaymentActivity, DetailOrder2Activity::class.java)
            startActivity(view)

            ArrayAdapter.createFromResource(
                this,
                R.array.payment,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                // Apply the adapter to the spinner
                binding.spinner.adapter = adapter
            }
            binding.spinner.onItemSelectedListener = this
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
    }

    override fun onNothingSelected(parent: AdapterView<*>) {
        // Another interface callback
    }
}