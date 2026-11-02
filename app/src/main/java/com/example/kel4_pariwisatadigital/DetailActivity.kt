package com.example.kel4_pariwisatadigital.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.kel4_pariwisatadigital.R
import com.example.kel4_pariwisatadigital.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Setup Toolbar dan Tombol Back (Modul 5)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Detail Destinasi"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        }

        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

        // 2. Terima Data dari Intent (Modul 6)
        val destName = intent.getStringExtra("EXTRA_NAME") ?: "Nama Destinasi"
        val destLocation = intent.getStringExtra("EXTRA_LOCATION") ?: "Lokasi"
        val destRating = intent.getStringExtra("EXTRA_RATING") ?: "0.0"
        val destPrice = intent.getStringExtra("EXTRA_PRICE") ?: "Rp 0"
        val destImage = intent.getStringExtra("EXTRA_IMAGE")

        // 3. Pasang data ke UI XML
        binding.tvDetailName.text = destName
        binding.tvDetailLocation.text = destLocation
        binding.tvDetailRating.text = "⭐ $destRating"
        binding.tvDetailPrice.text = destPrice

        // Load gambar menggunakan Glide
        Glide.with(this)
            .load(destImage)
            .centerCrop()
            .into(binding.ivDetailImage)

        // 4. Fitur Rute (Intent Implisit ke Google Maps)
        binding.btnRute.setOnClickListener {
            val gmmIntentUri = Uri.parse("geo:0,0?q=${Uri.encode(destName)}")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            if (mapIntent.resolveActivity(packageManager) != null) {
                startActivity(mapIntent)
            } else {
                Toast.makeText(this, "Google Maps tidak terinstall", Toast.LENGTH_SHORT).show()
            }
        }

        // 5. Fitur Booking (Buka Halaman WebView - Modul 5)
        binding.btnBooking.setOnClickListener {
            val intent = Intent(this, WebViewActivity::class.java)
            // Mengirim URL ke WebViewActivity
            intent.putExtra("EXTRA_URL", "https://indonesia.travel")
            startActivity(intent)
        }
    }

    // Menampilkan menu di Toolbar (Ikon Share & Titik Tiga)
    override fun onCreateOptionsMenu(menu: android.view.Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        return true
    }

    // Aksi saat ikon menu di Toolbar diklik
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_share -> {
                Toast.makeText(this, "Membagikan destinasi...", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.action_about -> {
                Toast.makeText(this, "Aplikasi TravelGO! v1.0", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}