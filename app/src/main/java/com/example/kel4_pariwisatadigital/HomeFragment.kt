package com.example.kel4_pariwisatadigital

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kel4_pariwisatadigital.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Membaca SharedPreferences di dalam Fragment (Modul 6 Latihan 5.1 & 5.2)
        val sharedPref = requireContext().getSharedPreferences("user_pref", Context.MODE_PRIVATE)
        val username = sharedPref.getString("username", "Traveler")

        // Memasang nama ke UI
        binding.tvGreeting.text = "Halo, $username!\nMau kemana hari ini?"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}