package com.example.movielite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.movielite.databinding.FragmentMovieDetailBinding
import com.example.movielite.databinding.GridViewItemBinding
import com.example.movielite.network.Movie


class MainAdapter(private val movies: List<Movie>, private val listener: (Movie) -> Unit
        ): RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            GridViewItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ))
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    inner class MainViewHolder(private val binding: GridViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.imageViewPoster.load("https://image.tmdb.org/t/p/w342${movie.posterPath}")
            binding.textViewTitle.text = movie.title
            binding.textViewReleaseDate.text = movie.releaseDate
            binding.textViewTotalVotes.text = movie.voteCount.toString()

            binding.cardViewMovieDetails.setOnClickListener {
                listener.invoke(movie)
            }
//            binding.rating.rating = movie.voteAverage!!.div(2)
        }
    }


    override fun getItemCount(): Int {
        return movies.size
    }
}