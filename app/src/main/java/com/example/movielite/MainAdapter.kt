package com.example.movielite

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bumptech.glide.Glide
import com.bumptech.glide.annotation.GlideModule
import com.example.movielite.databinding.GridViewItemBinding
import com.example.movielite.network.Movie


class MainAdapter(private val context: Context, private val movies: List<Movie>): RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            GridViewItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ))
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    @GlideModule
    inner class MainViewHolder(private val binding: GridViewItemBinding) :
        RecyclerView.ViewHolder(binding.root){
        fun bind(movie: Movie) {
            Glide.with(context)
                .load(movie.backdropPath)
                .into(binding.marsImage)
            binding.marsImage.load(movie.backdropPath)
            binding.region.text = movie.title
            binding.rating.rating = movie.voteAverage!!.div(2)
            /*binding.image.load(movie.image)*/
        }
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}