package com.example.kel4_pariwisatadigital

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.kel4_pariwisatadigital.databinding.ActivitySplashScreenBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Menggunakan Coroutine untuk menahan layar selama 2 detik (Pertemuan 6)
        lifecycleScope.launch {
            delay(2000)

            val sharedPref = getSharedPreferences("user_pref", Context.MODE_PRIVATE)
            val isFirstTime = sharedPref.getBoolean("isFirstTime", true)
            val isLogin = sharedPref.getBoolean("isLogin", false)

            val targetActivity = when {
                isFirstTime -> OnboardingActivity::class.java
                // PERBAIKAN DI SINI: Ubah MainActivity menjadi BaseActivity
                isLogin -> BaseActivity::class.java
                else -> LoginActivity::class.java
            }

            startActivity(Intent(this@SplashScreenActivity, targetActivity))
            finish()
        }
    }
}