package com.example.kel4_pariwisatadigital.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.kel4_pariwisatadigital.DestinationAdapter
import com.example.kel4_pariwisatadigital.databinding.FragmentSearchBinding
import com.example.kel4_pariwisatadigital.model.DestinationModel
import com.google.android.material.chip.Chip // Import wajb dari Modul 9

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    // Variabel untuk menyimpan data master dan data hasil filter
    private lateinit var allDestinations: List<DestinationModel>
    private var displayedDestinations = ArrayList<DestinationModel>()
    private lateinit var adapter: DestinationAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. Inisialisasi Data Dummy lengkap dengan Kategorinya
        allDestinations = listOf(
            DestinationModel("Gunung Prau", "Jawa Tengah", "4.9", "Rp 50.000", "https://images.unsplash.com/photo-1589182373726-e4f658ab50f0", "Alam"),
            DestinationModel("Bukit Rimpi", "Kal. Selatan", "4.7", "Rp 20.000", "https://images.unsplash.com/photo-1605330368146-24ba08a38a37", "Alam"),
            DestinationModel("Pulau Peucang", "Banten", "4.8", "Rp 400.000", "https://images.unsplash.com/photo-1559628233-eb1b1a45564b", "Pulau"),
            DestinationModel("Ayam Geprek Azka", "Pekanbaru", "4.6", "Rp 25.000", "https://images.unsplash.com/photo-1626082927389-6cd097cdc6ec", "Kuliner"),
            DestinationModel("Hotel Aryaduta", "Pekanbaru", "4.7", "Rp 800.000", "https://images.unsplash.com/photo-1566073771259-6a8506099945", "Hotel")
        )

        // Setup Adapter bawaan TravelGO!
        displayedDestinations.addAll(allDestinations)
        adapter = DestinationAdapter(requireContext(), displayedDestinations)
        binding.lvDestinations.adapter = adapter

        // 2. Penerapan Listener ChipGroup (Materi Modul 9)
        binding.chipGroupFilter.setOnCheckedStateChangeListener { group, checkedIds ->
            val selectedChipId = checkedIds.firstOrNull()

            if (selectedChipId != null) {
                // Mencari Chip berdasarkan ID yang dipilih
                val chip = group.findViewById<Chip>(selectedChipId)
                val selectedCategory = chip.text.toString()

                // Memunculkan Toast sesuai Modul 9
                Toast.makeText(requireContext(), "Filter: $selectedCategory", Toast.LENGTH_SHORT).show()

                // Menjalankan logika filter ListView TravelGO!
                filterDestinations(selectedCategory)
            }
        }
    }

    // Fungsi memfilter isi ListView tanpa merusak Adapter
    private fun filterDestinations(category: String) {
        displayedDestinations.clear()

        if (category == "Semua") {
            displayedDestinations.addAll(allDestinations)
        } else {
            val filteredList = allDestinations.filter { it.category == category }
            displayedDestinations.addAll(filteredList)
        }

        // Memperbarui ListView
        adapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}