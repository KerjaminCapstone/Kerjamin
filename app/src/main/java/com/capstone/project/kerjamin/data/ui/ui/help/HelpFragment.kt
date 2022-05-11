package com.capstone.project.kerjamin.data.ui.ui.help

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.capstone.project.kerjamin.R
import com.capstone.project.kerjamin.data.ui.ui.order.ListOrderViewModel
import com.capstone.project.kerjamin.databinding.FragmentHelpBinding
import com.capstone.project.kerjamin.databinding.FragmentHomeBinding
import com.capstone.project.kerjamin.databinding.FragmentListOrderBinding

class HelpFragment : Fragment() {
    private var _binding: FragmentHelpBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHelpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}