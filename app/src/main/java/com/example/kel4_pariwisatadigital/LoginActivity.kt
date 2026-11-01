package com.example.kel4_pariwisatadigital

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.kel4_pariwisatadigital.BaseActivity
import com.example.kel4_pariwisatadigital.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPref = getSharedPreferences("user_pref", Context.MODE_PRIVATE)
        val isLoggedIn = sharedPref.getBoolean("is_logged_in", false)

        // Jika sudah login, langsung lempar ke BaseActivity (Home) tanpa harus menampilkan form login
        if (isLoggedIn) {
            val intent = Intent(this, BaseActivity::class.java) // Sesuaikan nama BaseActivity-mu
            startActivity(intent)
            finish()
            return
        }
        // Listener MaterialButton sesuai instruksi Modul 9
        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            var isValid = true

            // 1. Validasi Nama Traveler
            if (username.isEmpty()) {
                binding.tilUsername.error = "Nama Traveler tidak boleh kosong" // Menampilkan pesan error merah
                isValid = false
            } else {
                binding.tilUsername.error = null // Menghilangkan pesan error jika sudah diisi
            }

            // 2. Validasi Kata Sandi
            if (password.isEmpty()) {
                binding.tilPassword.error = "Kata sandi tidak boleh kosong"
                isValid = false
            } else {
                binding.tilPassword.error = null
            }

            // 3. Jika input valid, proses Login dan simpan ke SharedPreferences
            if (isValid) {
                // Tampilkan Toast sapaan
                Toast.makeText(this, "Selamat datang, $username!", Toast.LENGTH_SHORT).show()

                // Simpan status login agar user tidak perlu login terus menerus
                val sharedPref = getSharedPreferences("user_pref", Context.MODE_PRIVATE)
                sharedPref.edit().apply {
                    putString("username", username)
                    putString("email", "$username@travelgo.com") // Dummy email
                    putBoolean("is_logged_in", true)
                    apply()
                }

                // Pindah ke Halaman Utama (Bottom Navigation)
                val intent = Intent(this, BaseActivity::class.java)
                startActivity(intent)
                finish() // Tutup LoginActivity dari backstack
            }
        }
    }
}