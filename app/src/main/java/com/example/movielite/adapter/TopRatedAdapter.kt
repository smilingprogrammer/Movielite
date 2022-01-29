package com.example.movielite.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.movielite.databinding.GridViewItemBinding
import com.example.movielite.databinding.HorizontalScrollViewBinding
import com.example.movielite.response.toprated.TopRated

class TopRatedAdapter(private val topRated: List<TopRated>) :
    RecyclerView.Adapter<TopRatedAdapter.TopRatedViewHolder>() {

    inner class TopRatedViewHolder(private val binding: GridViewItemBinding) :
            RecyclerView.ViewHolder(binding.root) {
                fun bind(topRated: TopRated) {
                    binding.imageViewPoster.load("https://image.tmdb.org/t/p/w342${topRated.poster_path}")
                    binding.textViewTitle.text = topRated.title
                    binding.ratingBar.rating = topRated.vote_average!!.div(2).toFloat()
                    binding.textViewGenres.text = topRated.genre_ids.toString()
                }
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopRatedViewHolder {
        return TopRatedViewHolder(
            GridViewItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: TopRatedViewHolder, position: Int) {
        holder.bind(topRated[position])
    }

    override fun getItemCount(): Int {
        return topRated.size
    }
}