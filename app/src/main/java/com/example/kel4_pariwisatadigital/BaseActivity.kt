package com.example.kel4_pariwisatadigital

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.kel4_pariwisatadigital.databinding.ActivityBaseBinding
import com.example.kel4_pariwisatadigital.fragment.AccountFragment
import com.example.kel4_pariwisatadigital.fragment.MyPackFragment
import com.example.travelgo.fragment.SearchFragment

class BaseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set Fragment default (Home) saat pertama kali dibuka
        replaceFragment(HomeFragment())

        // Mengatur aksi ketika menu navigasi bawah ditekan
        binding.bottomNavView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    replaceFragment(HomeFragment())
                    true
                }
                R.id.nav_search -> {
                    replaceFragment(SearchFragment())
                    true
                }
                R.id.nav_mypack -> {
                    replaceFragment(MyPackFragment())
                    true
                }
                R.id.nav_favorite -> {
                    replaceFragment(FavoriteFragment())
                    true
                }
                R.id.nav_account -> {
                    replaceFragment(AccountFragment())
                    true
                }
                else -> false
            }
        }
    }

    // Fungsi untuk mengganti fragment di dalam fragment_container (Modul 7)
    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }
}
