package com.example.movielite.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import coil.load
import com.example.movielite.databinding.SliderItemBinding
import com.example.movielite.response.upcoming.Upcoming
import com.smarteist.autoimageslider.SliderViewAdapter

class ImageSliderAdapter(private val upcoming: List<Upcoming>)
    : SliderViewAdapter<ImageSliderAdapter.SliderViewHolder>() {

    inner class SliderViewHolder(private val binding: SliderItemBinding)
        :SliderViewAdapter.ViewHolder(binding.root) {
            fun bind(upcoming: Upcoming) {
                binding.imageView.load("https://image.tmdb.org/t/p/w780${upcoming.backdrop_path}")
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup?): SliderViewHolder {
        return SliderViewHolder(
            SliderItemBinding.inflate(
                LayoutInflater.from(parent?.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(viewHolder: SliderViewHolder?, position: Int) {
        viewHolder?.bind(upcoming[position])
    }

    override fun getCount(): Int {
        return upcoming.size
    }
}