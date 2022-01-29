package com.example.movielite.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.movielite.databinding.HorizontalScrollViewBinding
import com.example.movielite.response.popularresponse.Movie

class PopularAdapter(private val popularMovie: List<Movie>): RecyclerView.Adapter<PopularAdapter.PopularViewHolder>() {

    inner class PopularViewHolder(private val binding: HorizontalScrollViewBinding):
        RecyclerView.ViewHolder(binding.root){
            fun bind(popularMovie: Movie) {
                binding.image.load("https://image.tmdb.org/t/p/w342${popularMovie.posterPath}")
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {
        return PopularViewHolder(
            HorizontalScrollViewBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
        holder.bind(popularMovie[position])
    }

    override fun getItemCount(): Int {
        return popularMovie.size
    }
}