package com.capstone.project.kerjamin.data.ui.ui.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.capstone.project.kerjamin.data.ui.DetailClientActivity
import com.capstone.project.kerjamin.data.ui.ListFreelancerActivity
import com.capstone.project.kerjamin.databinding.FragmentHomeBinding
import com.capstone.project.kerjamin.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.layoutAccount.setOnClickListener {
            val intent = Intent(getActivity(), DetailClientActivity::class.java)
            getActivity()?.startActivity(intent)

        }
    }
}