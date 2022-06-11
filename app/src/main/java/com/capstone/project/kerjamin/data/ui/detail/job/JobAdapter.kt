package com.capstone.project.kerjamin.data.ui.detail.job

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.capstone.project.kerjamin.R
import com.capstone.project.kerjamin.data.ui.list.adapter.MainAdapter

data class JobAdapter(private val jobList:ArrayList<Job>) : RecyclerView.Adapter<JobAdapter.JobViewHolder>(){

    class JobViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val description : TextView = itemView.findViewById(R.id.tv_job)
    }

    var onItemClick : ((Job) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobViewHolder {
        val  view = LayoutInflater.from(parent.context).inflate(R.layout.item_row_job, parent, false)
        return JobAdapter.JobViewHolder(view)
    }

    override fun onBindViewHolder(holder: JobViewHolder, position: Int) {
        val job = jobList[position]
        holder.description.text = job.job
    }

    override fun getItemCount(): Int {
        return jobList.size
    }
}
