package com.capstone.project.kerjamin.data.ui.ui.home

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.capstone.project.kerjamin.data.ui.list.FreelancerArsitecActivity
import com.capstone.project.kerjamin.data.ui.list.FreelancerBuilderActivity
import com.capstone.project.kerjamin.data.ui.list.FreelancerCleanerActivity
import com.capstone.project.kerjamin.data.ui.list.FreelancerServiceActivity
import com.capstone.project.kerjamin.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnService.setOnClickListener {
            val intent = Intent(getActivity(), FreelancerServiceActivity::class.java)
            getActivity()?.startActivity(intent)
        }

        binding.btnCleaner.setOnClickListener {
            val intent = Intent(getActivity(), FreelancerCleanerActivity::class.java)
            getActivity()?.startActivity(intent)
        }

        binding.btnBuilder.setOnClickListener {
            val intent = Intent(getActivity(), FreelancerBuilderActivity::class.java)
            getActivity()?.startActivity(intent)
        }

        binding.btnArsitec.setOnClickListener {
            val intent = Intent(getActivity(), FreelancerArsitecActivity::class.java)
            getActivity()?.startActivity(intent)
        }
    }
}