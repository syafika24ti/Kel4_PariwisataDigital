package com.example.kel4_pariwisatadigital

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bumptech.glide.Glide
import com.example.kel4_pariwisatadigital.databinding.ItemDestinationBinding
import com.example.kel4_pariwisatadigital.model.DestinationModel

// Implementasi Custom Adapter sesuai Modul 7
class DestinationAdapter(
    context: Context,
    private val destinations: List<DestinationModel>
) : ArrayAdapter<DestinationModel>(context, 0, destinations) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding: ItemDestinationBinding
        val view: View

        if (convertView == null) {
            binding = ItemDestinationBinding.inflate(LayoutInflater.from(context), parent, false)
            view = binding.root
            view.tag = binding
        } else {
            binding = convertView.tag as ItemDestinationBinding
            view = convertView
        }

        val data = destinations[position]

        binding.tvDestName.text = data.name
        binding.tvDestLocation.text = data.location
        binding.tvDestRating.text = "⭐ ${data.rating}"
        binding.tvDestPrice.text = data.price

        // Load gambar dengan Glide (Modul 7)
        Glide.with(context)
            .load(data.imageUrl)
            .centerCrop()
            .into(binding.ivDestImage)

        return view
    }
}