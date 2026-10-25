package com.example.kel4_pariwisatadigital.activity

import android.os.Bundle
import android.view.MenuItem
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.kel4_pariwisatadigital.R
import com.example.kel4_pariwisatadigital.databinding.ActivityWebViewBinding

class WebViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWebViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Simulasi Web Booking"
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        }
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
        // Setup WebView (Modul 5)
        val url = intent.getStringExtra("EXTRA_URL") ?: "https://google.com"

        binding.webView.webViewClient = WebViewClient()
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.loadUrl(url)
    }

    // Aksi Tombol Back pada Toolbar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish() // Menggunakan finish() akan langsung menutup dan mengembalikan ke halaman sebelumnya
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}