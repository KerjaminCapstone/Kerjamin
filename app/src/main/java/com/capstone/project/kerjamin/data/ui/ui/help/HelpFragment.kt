package com.capstone.project.kerjamin.data.ui.ui.help

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.capstone.project.kerjamin.R
import com.capstone.project.kerjamin.data.ui.list.FreelancerServiceActivity
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val num = "+6283870804464"
        val text = "Hello"

        binding.layoutWa.setOnClickListener {

            val installed: Boolean = isAppInstalled("com.whatsapp")
            if (installed) {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse("https://api.whatsapp.com/send?phone=$num&text=$text")
                getActivity()?.startActivity(intent)
            } else {
                Toast.makeText(getActivity(),"Whatsapp is not installed!!",Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun isAppInstalled(s: String): Boolean {
        val packageManager = getActivity()?.packageManager
        var is_installed: Boolean
        try {
            packageManager?.getPackageInfo(s, PackageManager.GET_ACTIVITIES)
            is_installed = true
        } catch (e: PackageManager.NameNotFoundException) {
            is_installed = false
            e.printStackTrace()
        }
        return is_installed
    }
}