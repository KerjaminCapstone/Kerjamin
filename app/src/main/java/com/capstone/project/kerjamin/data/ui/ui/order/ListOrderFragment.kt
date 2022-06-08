package com.capstone.project.kerjamin.data.ui.ui.order

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.capstone.project.kerjamin.data.ui.order.DetailOrderFinishActivity
import com.capstone.project.kerjamin.databinding.FragmentListOrderBinding

class ListOrderFragment : Fragment() {
    private var _binding: FragmentListOrderBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var orderList : ArrayList<Order>
    private lateinit var adapter : OrderAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListOrderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initRecyclerView()
    }

    private fun initRecyclerView(){
        recyclerView = binding.rvListOrder
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        orderList = ArrayList()

        orderList.add(Order("Servis Elektronik", "Kayla Wijayanti", "14 Juni 2022", "selesai"))
        orderList.add(Order("Servis Elektronik", "Kayla Wijayanti", "14 Juni 2022", "selesai"))

        adapter = OrderAdapter(activity, orderList)
        recyclerView.adapter = adapter

        adapter.onItemClick = {
            val intent = Intent(activity, DetailOrderFinishActivity::class.java)
            getActivity()?.startActivity(intent)
        }
    }
}