package com.example.kel4_pariwisatadigital

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.kel4_pariwisatadigital.databinding.ActivityOnBoardingBinding

class OnboardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnBoardingBinding
    private var step = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnNext.setOnClickListener {
            if (step == 1) {
                // Pindah ke step 2 (Ubah teks dan gambar secara dinamis)
                step = 2
                binding.tvTitle.text = "Siapkan Rencana Perjalananmu"
                binding.tvDesc.text = "Simpan destinasi favorit dan buat daftar bawaan dengan mudah."
                binding.btnBack.visibility = View.VISIBLE
                binding.btnNext.text = "Mulai"
            } else {
                // Selesai onboarding, simpan ke SharedPreferences
                val sharedPref = getSharedPreferences("user_pref", Context.MODE_PRIVATE)
                sharedPref.edit().putBoolean("isFirstTime", false).apply()

                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }

        binding.btnBack.setOnClickListener {
            if (step == 2) {
                step = 1
                binding.tvTitle.text = "Discover Your Next Travel"
                binding.tvDesc.text = "Jelajahi banyak tempat indah di seluruh Indonesia dan temukan destinasi wisata impianmu dengan aplikasi TravelGO!"
                binding.btnBack.visibility = View.INVISIBLE
                binding.btnNext.text = "Next"
            }
        }
    }
}