package com.example.kel4_pariwisatadigital.tutorial

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class TutorialFragmentAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    // Menentukan jumlah halaman (sesuai Modul 9)
    override fun getItemCount(): Int {
        return 3
    }

    // Mengatur urutan halaman yang ditampilkan
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> Tutorial1Fragment()
            1 -> Tutorial2Fragment()
            2 -> Tutorial3Fragment()
            else -> Tutorial1Fragment()
        }
    }
}