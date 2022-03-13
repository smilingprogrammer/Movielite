package com.example.movielite.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import coil.load
import com.example.movielite.databinding.HorizontalScrollViewBinding
import com.example.movielite.response.popularresponse.Movie


class MainAdapter(private val movies: List<Movie>, private val listener: (Movie) -> Unit
        ): RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            HorizontalScrollViewBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ))
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    inner class MainViewHolder(private val binding: HorizontalScrollViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.imageSlide.load("https://image.tmdb.org/t/p/w342${movie.posterPath}")
//            binding.textViewTitle.text = movie.title
//            binding.textViewReleaseDate.text = movie.releaseDate
//            binding.textViewTotalVotes.text = movie.voteCount.toString()

            binding.imageSlide.setOnClickListener {
                listener.invoke(movie)
            }
//            binding.rating.rating = movie.voteAverage!!.div(2)
        }
    }


    override fun getItemCount(): Int {
        return movies.size
    }
}
