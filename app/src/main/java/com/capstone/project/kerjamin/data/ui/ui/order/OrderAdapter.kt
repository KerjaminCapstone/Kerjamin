package com.capstone.project.kerjamin.data.ui.ui.order

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.capstone.project.kerjamin.R

data class OrderAdapter(val activity: FragmentActivity?, private val orderList: ArrayList<Order>) : RecyclerView.Adapter<OrderAdapter.OrderViewHolder>(){

    var onItemClick : ((Order) -> Unit)? = null

    class OrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val category : TextView = itemView.findViewById(R.id.order_category)
        val name : TextView = itemView.findViewById(R.id.order_username)
        val dateOrder : TextView = itemView.findViewById(R.id.order_date)
        val orderStatus : TextView = itemView.findViewById(R.id.order_status)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OrderAdapter.OrderViewHolder {
        val  view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_order, parent, false)
        return OrderAdapter.OrderViewHolder(view)
    }

    override fun onBindViewHolder(holder: OrderAdapter.OrderViewHolder, position: Int) {
        val order = orderList[position]
        holder.category.text = order.category
        holder.name.text = order.name
        holder.dateOrder.text = order.date
        holder.orderStatus.text = order.status

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(order)
        }
    }

    override fun getItemCount(): Int {
        return orderList.size
    }

}
