package com.example.movielite.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.example.movielite.response.MovieDetail
import com.example.movielite.viewmodel.MovieDetailFragmentViewModel
import com.example.movielite.ViewModelFactory.MovieDetailViewModelFactory
import com.example.movielite.ui.MainFragment.Companion.ID_ARGS
import com.example.movielite.databinding.FragmentMovieDetailBinding
import com.example.movielite.response.Movie
import com.example.movielite.network.MovieApi
import com.example.movielite.network.repository.MovieDetailRepository


class MovieDetailFragment : Fragment() {
    private var binding: FragmentMovieDetailBinding? = null
    private val movieDetail: MovieDetail? = null
    private var TAG = "Debug"
    private lateinit var movie: Movie


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
        arguments?.let {
            movie = it.get(ID_ARGS) as Movie
        }
//        binding?.imageViewBackdrop?.load("https://image.tmdb.org/t/p/w780${movie.backdropPath}")
//        binding?.textViewOverView?.text = movie.overview
//        binding?.more?.setOnClickListener {
//            binding?.textViewOverView?.ellipsize = null
//            binding?.textViewOverView?.maxLines = Integer.MAX_VALUE
//        }
//        binding?.textViewMovieTitleWithDate?.text = movie.title
//        binding?.textViewMovieReleaseStatus?.text = movie.releaseDate
        activity?.title = movie.title
        viewModel.popularMoviesDetailLiveData.observe(requireActivity(),{
            binding?.imageViewBackdrop?.load("https://image.tmdb.org/t/p/w780${it.backdrop_path}")
            binding?.textViewOverView?.text = it.overview
            binding?.more?.setOnClickListener {
                binding?.textViewOverView?.ellipsize = null
                binding?.textViewOverView?.maxLines = Integer.MAX_VALUE
            }
            binding?.textViewMovieTitleWithDate?.text = it.title
        })
        viewModel.getPopularMovieDetails(movie.id!!)

    }
}