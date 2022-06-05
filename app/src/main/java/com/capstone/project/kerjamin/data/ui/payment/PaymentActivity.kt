package com.capstone.project.kerjamin.data.ui.payment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import com.capstone.project.kerjamin.data.ui.order.DetailOrderProcessActivity
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
            val view = Intent(this@PaymentActivity, DetailOrderProcessActivity::class.java)
            startActivity(view)
        }
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
    }

    override fun onNothingSelected(parent: AdapterView<*>) {
        // Another interface callback
    }
}