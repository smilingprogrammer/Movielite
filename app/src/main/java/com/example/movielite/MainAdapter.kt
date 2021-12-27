package com.example.movielite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.movielite.databinding.FragmentMainBinding
import com.example.movielite.databinding.GridViewItemBinding
import com.example.movielite.network.Movie


class MainAdapter(private val movies: List<Movie>): RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(FragmentMainBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ))
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    inner class MainViewHolder(private val binding: FragmentMainBinding) :
        RecyclerView.ViewHolder(binding.root){
        fun bind(movie: Movie) {
            binding.region.text = movie.title
            binding.image.text = movie.originalTitle
            /*binding.image.load(movie.image)*/
        }
    }

    override fun getItemCount(): Int {
        return 0
    }
}