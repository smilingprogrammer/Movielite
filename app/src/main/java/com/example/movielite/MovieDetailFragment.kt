package com.example.movielite

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.example.movielite.MainFragment.Companion.ID_ARGS
import com.example.movielite.databinding.FragmentMovieDetailBinding
import com.example.movielite.network.MovieApi
import com.example.movielite.network.repository.MovieDetailRepository
import com.example.movielite.network.repository.MovieRepository


class MovieDetailFragment : Fragment() {

    private var binding: FragmentMovieDetailBinding? = null
    private val movieDetail = mutableListOf<MovieDetail>()

    private val viewModel: MovieDetailFragmentViewModel by lazy {
        ViewModelProvider(this, MovieDetailViewModelFactory(MovieDetailRepository(MovieApi.retrofitService)))
            .get(MovieDetailFragmentViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this
        binding = FragmentMovieDetailBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var movieId = 0
        arguments?.let {
            movieId = it.getInt(ID_ARGS)
        }

        viewModel.getPopularMovieDetails(movieId)
        viewModel.popularMoviesDetailLiveData.observe(/*this*/viewLifecycleOwner, Observer {
            movieDetail.addAll(it)
        })
        binding?.imageViewBackdrop?.load("https://image.tmdb.org/t/p/w342${movieDetail[0].backdropPath}")
        binding?.textViewOverView?.text = movieDetail[0].overview
        activity?.title = movieDetail[0].title
    }
}