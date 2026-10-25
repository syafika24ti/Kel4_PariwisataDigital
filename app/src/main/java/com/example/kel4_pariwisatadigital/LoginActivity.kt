package com.example.kel4_pariwisatadigital

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.kel4_pariwisatadigital.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Tombol Login Biasa
        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (email.isEmpty()) {
                binding.tilEmail.error = "Email tidak boleh kosong"
            } else if (password.isEmpty()) {
                binding.tilPassword.error = "Password tidak boleh kosong"
            } else {
                // Hapus error jika sudah diisi
                binding.tilEmail.error = null
                binding.tilPassword.error = null

                // Simpan data login di SharedPreferences (Pertemuan 6)
                val sharedPref = getSharedPreferences("user_pref", Context.MODE_PRIVATE)
                val editor = sharedPref.edit()
                editor.putBoolean("isLogin", true)
                // Mengambil nama dari email (contoh: syafika@gmail.com -> Syafika)
                val username = email.substringBefore("@").replaceFirstChar { it.uppercase() }
                editor.putString("username", username)
                editor.putString("email", email)
                editor.apply()

                // PERBAIKAN DI SINI: Ubah MainActivity menjadi BaseActivity
                startActivity(Intent(this, BaseActivity::class.java))
                finish()
            }
        }

        // Tombol Login with Google (Simulasi AlertDialog - Pertemuan 6)
        binding.btnGoogle.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Login with Google")
                .setMessage("Fitur autentikasi Google belum diintegrasikan untuk praktikum ini. Silakan gunakan login manual.")
                .setPositiveButton("Mengerti") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }
    }
}