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

        // 1. SharedPreferences (Modul 9): Cek apakah tutorial sudah pernah dilihat
        val sharedPref = requireContext().getSharedPreferences("tutorial_pref", android.content.Context.MODE_PRIVATE)
        val isTutorialSeen = sharedPref.getBoolean("is_tutorial_seen", false)

        if (!isTutorialSeen) {
            // Jika belum pernah lihat, paksa buka halaman Tutorial sekarang juga
            val intent = android.content.Intent(requireContext(), com.example.kel4_pariwisatadigital.tutorial.TutorialTravelActivity::class.java)
            startActivity(intent)
        }

        // 2. MenuProvider (Modul 9): Menambahkan ikon Info di Toolbar
        requireActivity().addMenuProvider(object : androidx.core.view.MenuProvider {
            override fun onCreateMenu(menu: android.view.Menu, menuInflater: android.view.MenuInflater) {
                menuInflater.inflate(com.example.kel4_pariwisatadigital.R.menu.menu_info, menu)
            }

            override fun onMenuItemSelected(menuItem: android.view.MenuItem): Boolean {
                return when (menuItem.itemId) {
                    com.example.kel4_pariwisatadigital.R.id.action_info -> {
                        // Buka tutorial saat ikon diklik
                        val intent = android.content.Intent(requireContext(), com.example.kel4_pariwisatadigital.tutorial.TutorialTravelActivity::class.java)
                        startActivity(intent)
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, androidx.lifecycle.Lifecycle.State.RESUMED)

        // 3. KODE KLIK PROFIL (Tombol Manual untuk membuka Tutorial)
        binding.ivProfile.setOnClickListener {
            val intent = android.content.Intent(requireContext(), com.example.kel4_pariwisatadigital.tutorial.TutorialTravelActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}