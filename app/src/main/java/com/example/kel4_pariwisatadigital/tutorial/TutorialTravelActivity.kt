package com.example.kel4_pariwisatadigital.tutorial

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kel4_pariwisatadigital.databinding.ActivityTutorialTravelBinding

class TutorialTravelActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTutorialTravelBinding
    private lateinit var adapter: TutorialFragmentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTutorialTravelBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Pasang Adapter
        adapter = TutorialFragmentAdapter(this)
        binding.tutorialTravelViewPager.adapter = adapter

        // Menghubungkan DotsIndicator dengan ViewPager2 (Modul 9)
        binding.dotsIndicator.attachTo(binding.tutorialTravelViewPager)

        // Tombol Selesai untuk menyimpan SharedPreferences
        binding.btnSelesaiTutorial.setOnClickListener {
            // Gunakan nama file "tutorial_pref" agar tidak bentrok dengan login
            val sharedPref = getSharedPreferences("tutorial_pref", Context.MODE_PRIVATE)
            sharedPref.edit().putBoolean("is_tutorial_seen", true).apply()

            // Tutup halaman tutorial dan kembali ke halaman sebelumnya
            finish()
        }
    }
}