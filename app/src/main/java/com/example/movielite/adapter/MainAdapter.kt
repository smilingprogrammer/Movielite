package com.example.movielite.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import coil.load
import com.example.movielite.databinding.HorizontalScrollViewBinding
import com.example.movielite.response.Movie


class MainAdapter internal constructor(movies: MutableList<Movie>,
                                       viewPager2: ViewPager2,
                                       private val listener: (Movie) -> Unit
        ): RecyclerView.Adapter<MainAdapter.SliderViewHolder>() {
    private val movies: List<Movie>
    init {
        this.movies = movies
    }

    inner class SliderViewHolder(private val binding: HorizontalScrollViewBinding) : RecyclerView.ViewHolder(binding.root) {


        fun bind(movie: Movie) {
            binding.image.load("https://image.tmdb.org/t/p/w342${movie.posterPath}")
            binding.image.setOnClickListener {
                listener.invoke(movie)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
        return SliderViewHolder(
            HorizontalScrollViewBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ))
    }

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        holder.bind(movies[position])
    }

//    inner class MainViewHolder(private val binding: HorizontalScrollViewBinding) :
//        RecyclerView.ViewHolder(binding.root) {
//        fun bind(movie: Movie) {
//            binding.image.load("https://image.tmdb.org/t/p/w342${movie.posterPath}")
////            binding.textViewTitle.text = movie.title
////            binding.textViewReleaseDate.text = movie.releaseDate
////            binding.textViewTotalVotes.text = movie.voteCount.toString()
////
//            binding.image.setOnClickListener {
//                listener.invoke(movie)
//            }
////            binding.rating.rating = movie.voteAverage!!.div(2)
//        }
//    }


    override fun getItemCount(): Int {
        return movies.size
    }
}