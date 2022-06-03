package com.capstone.project.kerjamin.data.ui.list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.capstone.project.kerjamin.R
import com.capstone.project.kerjamin.data.ui.list.model.Freelancer

class MainAdapter(private val freelancerList:ArrayList<Freelancer>) : RecyclerView.Adapter<MainAdapter.FreelancerViewHolder>() {

    var onItemClick : ((Freelancer) -> Unit)? = null

    class FreelancerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val imageItem : ImageView = itemView.findViewById(R.id.img_item)
        val nameItem : TextView = itemView.findViewById(R.id.username_item)
        val bidangItem : TextView = itemView.findViewById(R.id.specialist_item)
        val distanceItem : TextView = itemView.findViewById(R.id.distant_item)
        val ratingItem : TextView = itemView.findViewById(R.id.rating_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FreelancerViewHolder {
        val  view = LayoutInflater.from(parent.context).inflate(R.layout.item_row_freelance, parent, false)
        return FreelancerViewHolder(view)
    }

    override fun onBindViewHolder(holder: FreelancerViewHolder, position: Int) {
        val freelancer = freelancerList[position]
        holder.imageItem.setImageResource(freelancer.image)
        holder.nameItem.text = freelancer.namaFreelancer
        holder.bidangItem.text = freelancer.bidang
        holder.distanceItem.text = freelancer.distance
        holder.ratingItem.text = freelancer.rating

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(freelancer)
        }
    }

    override fun getItemCount(): Int {
        return freelancerList.size
    }
}