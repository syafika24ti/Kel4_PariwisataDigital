package com.example.travelgo.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.kel4_pariwisatadigital.DestinationAdapter
import com.example.kel4_pariwisatadigital.databinding.FragmentSearchBinding
import com.example.kel4_pariwisatadigital.model.DestinationModel
import com.example.kel4_pariwisatadigital.activity.DetailActivity

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val destList = listOf(
            DestinationModel(
                "Danau Labuan Cermin",
                "Kalimantan Timur",
                "4.7",
                "Rp. 300.000",
                "https://images.unsplash.com/photo-1542601906990-b4d3fb778b09"
            ),
            DestinationModel(
                "Morotai Beach",
                "Maluku Utara",
                "4.8",
                "Rp. 250.000",
                "https://images.unsplash.com/photo-1506953823976-52e1fdc0149a"
            ),
            DestinationModel(
                "Bukit Rimpi",
                "Kalimantan Selatan",
                "4.4",
                "Rp. 150.000",
                "https://images.unsplash.com/photo-1517722014278-c256a91a6fba"
            ),
            DestinationModel(
                "Gunung Prau",
                "Jawa Tengah",
                "4.9",
                "Rp. 50.000",
                "https://images.unsplash.com/photo-1589182373726-e4f658ab50f0"
            ),
            DestinationModel(
                "Pulau Peucang",
                "Banten",
                "4.6",
                "Rp. 400.000",
                "https://images.unsplash.com/photo-1559628233-eb1b1a45564b"
            )
        )

        binding.tvTotalResult.text = "${destList.size} Hasil Ditemukan"

        // 2. Pasang Custom Adapter (Modul 7)
        val adapter = DestinationAdapter(requireContext(), destList)
        binding.lvDestinations.adapter = adapter

        // 3. Aksi Klik ListView
        binding.lvDestinations.setOnItemClickListener { _, _, position, _ ->
            val selectedItem = destList[position]

            // Pindah ke DetailActivity bawa data (Modul 6)
            val intent = Intent(
                requireContext(),
                com.example.kel4_pariwisatadigital.activity.DetailActivity::class.java
            )
            intent.putExtra("EXTRA_NAME", selectedItem.name)
            intent.putExtra("EXTRA_LOCATION", selectedItem.location)
            intent.putExtra("EXTRA_RATING", selectedItem.rating)
            intent.putExtra("EXTRA_PRICE", selectedItem.price)
            intent.putExtra("EXTRA_IMAGE", selectedItem.imageUrl)
            startActivity(intent)
        }

        fun onDestroyView() {
            super.onDestroyView()
            _binding = null
        }
    }
}