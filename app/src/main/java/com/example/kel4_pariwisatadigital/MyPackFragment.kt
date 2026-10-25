package com.example.kel4_pariwisatadigital.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.SimpleAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.kel4_pariwisatadigital.databinding.FragmentMyPackBinding

class MyPackFragment : Fragment() {

    private var _binding: FragmentMyPackBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentMyPackBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // 1. ARRAY ADAPTER (Modul 7) -> simple_list_item_1

        val checklist = arrayOf("Kamera & Baterai Cadangan", "Jaket Tebal Gunung", "Obat-obatan Pribadi", "Sepatu Trekking", "Powerbank & Kabel", "Pakaian Ganti")

        val arrayAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1, // Layout bawaan Android (1 baris teks)
            checklist
        )
        binding.lvChecklist.adapter = arrayAdapter

        binding.lvChecklist.setOnItemClickListener { _, _, position, _ ->
            Toast.makeText(requireContext(), "Dibawa: ${checklist[position]}", Toast.LENGTH_SHORT).show()
        }


        // 2. SIMPLE ADAPTER (Modul 7) -> simple_list_item_2

        val travelInfoList = ArrayList<HashMap<String, String>>()

        val plan1 = HashMap<String, String>()
        plan1["title"] = "Gunung Prau, Jawa Tengah"
        plan1["desc"] = "Berangkat: 25 Okt 2026 (Pendakian 2 Hari 1 Malam)"
        travelInfoList.add(plan1)

        val plan2 = HashMap<String, String>()
        plan2["title"] = "Danau Labuan Cermin"
        plan2["desc"] = "Berangkat: 10 Nov 2026 (Eksplorasi 4 Hari)"
        travelInfoList.add(plan2)

        val simpleAdapter = SimpleAdapter(
            requireContext(),
            travelInfoList,
            android.R.layout.simple_list_item_2, // Layout bawaan Android (Judul & Subjudul)
            arrayOf("title", "desc"),
            intArrayOf(android.R.id.text1, android.R.id.text2)
        )
        binding.lvTravelInfo.adapter = simpleAdapter

        binding.lvTravelInfo.setOnItemClickListener { _, _, position, _ ->
            val selected = travelInfoList[position]
            Toast.makeText(requireContext(), "Membuka detail ${selected["title"]}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}