package com.example.kel4_pariwisatadigital.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.kel4_pariwisatadigital.LoginActivity
import com.example.kel4_pariwisatadigital.databinding.FragmentAccountBinding

class AccountFragment : Fragment() {

    private var _binding: FragmentAccountBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. Membaca SharedPreferences (Modul 6)
        val sharedPref = requireContext().getSharedPreferences("user_pref", Context.MODE_PRIVATE)
        val username = sharedPref.getString("username", "Traveler")
        val email = sharedPref.getString("email", "email@domain.com")
        val isNotifEnabled = sharedPref.getBoolean("notif_enabled", true)

        // 2. Memasang data ke UI
        binding.tvAccountName.text = username
        binding.tvAccountEmail.text = email
        binding.switchNotification.isChecked = isNotifEnabled

        // 3. Menyimpan preferensi Notifikasi (Modul 6)
        binding.switchNotification.setOnCheckedChangeListener { _, isChecked ->
            sharedPref.edit().putBoolean("notif_enabled", isChecked).apply()
            val status = if (isChecked) "diaktifkan" else "dimatikan"
            Toast.makeText(requireContext(), "Notifikasi $status", Toast.LENGTH_SHORT).show()
        }

        // 4. Fitur Logout dengan AlertDialog (Modul 6)
        binding.btnLogout.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("Logout")
                .setMessage("Apakah Anda yakin ingin keluar dari TravelGO!?")
                .setPositiveButton("Ya") { dialog, _ ->
                    // Hapus semua sesi login
                    sharedPref.edit().clear().apply()

                    dialog.dismiss()

                    // Pindah ke LoginActivity dan hapus seluruh back stack
                    val intent = Intent(requireContext(), LoginActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                }
                .setNegativeButton("Batal") { dialog, _ ->
                    dialog.dismiss() // Hanya menutup dialog
                }
                .show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}