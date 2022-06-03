package com.capstone.project.kerjamin.data.ui.detail.freelancer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.capstone.project.kerjamin.data.ui.list.model.Freelancer
import com.capstone.project.kerjamin.data.ui.problem.ProblemActivity
import com.capstone.project.kerjamin.databinding.ActivityDetailFreelancerBinding
import org.w3c.dom.Text

class DetailFreelancerActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailFreelancerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailFreelancerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Detail Freelancer"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        getDetail()

        binding.btnOrder.setOnClickListener {
            val view = Intent(this@DetailFreelancerActivity, ProblemActivity::class.java)
            startActivity(view)
        }
    }

    private fun getDetail(){
        val freelancer = intent.getParcelableExtra<Freelancer>("freelancer")
        if (freelancer!=null){
            val imgFreelancer : ImageView = binding.imgFreelancer
            val username : TextView = binding.tvUsername
            val skill : TextView = binding.tvSpecialist
            val address : TextView = binding.tvAdress
            val distance : TextView = binding.tvDistance
            val gender : TextView = binding.tvGender
            val rating : TextView = binding.tvRating

            imgFreelancer.setImageResource(freelancer.image)
            username.text = freelancer.namaFreelancer
            skill.text = freelancer.bidang
            distance.text = freelancer.distance
            rating.text = freelancer.rating
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}