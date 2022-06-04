package com.capstone.project.kerjamin.data.ui.list.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.recyclerview.widget.RecyclerView
import com.capstone.project.kerjamin.data.ui.list.model.FreelancerModel
import com.capstone.project.kerjamin.data.ui.detail.freelancer.DetailFreelancerActivity
import com.capstone.project.kerjamin.databinding.ItemRowFreelanceBinding

class FreelancerAdapter : RecyclerView.Adapter<FreelancerAdapter.FreelancerViewHolder>() {

    private var listFreelancer :List<FreelancerModel>? = null

    fun setFreelancer (listFreelancer: List<FreelancerModel>?){
        this.listFreelancer = listFreelancer
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FreelancerViewHolder {
        val viewFreelancer =ItemRowFreelanceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FreelancerViewHolder(viewFreelancer)
    }

    override fun onBindViewHolder(holder: FreelancerViewHolder, position: Int) {
        holder.freelancerBind(listFreelancer?.get(position)!!)
    }

    override fun getItemCount(): Int {
        return if (listFreelancer == null)0
            else listFreelancer?.size!!
    }

    inner class FreelancerViewHolder(private val binding: ItemRowFreelanceBinding) :RecyclerView.ViewHolder(binding.root) {
        fun freelancerBind(dataFreelancer : FreelancerModel){
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailFreelancerActivity::class.java)
                intent.putExtra("dataFreelancer", dataFreelancer)

                val optionsCompat : ActivityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    itemView.context as Activity,

                    Pair(binding.imgItem, "image"),
                    Pair(binding.usernameItem, "username"),
                    Pair(binding.specialistItem, "specialist"),
                    Pair(binding.distantItem, "distance"),
                    Pair(binding.ratingItem, "rating")
                )

                itemView.context.startActivity(intent, optionsCompat.toBundle())
            }

            binding.apply {
                usernameItem.text = dataFreelancer.name
                specialistItem.text = dataFreelancer.job_child_name
                distantItem.text = dataFreelancer.distance.toString()
                ratingItem.text = dataFreelancer.rating
            }
        }
    }
}