package com.example.movielite.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.forEach
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.example.movielite.R
import com.example.movielite.viewmodel.MovieDetailFragmentViewModel
import com.example.movielite.ViewModelFactory.MovieDetailViewModelFactory
import com.example.movielite.ui.MainFragment.Companion.ID_ARGS
import com.example.movielite.databinding.FragmentMovieDetailBinding
import com.example.movielite.network.MovieApi
import com.example.movielite.network.repository.MovieDetailRepository
import com.example.movielite.response.moviedetailresponse.MovieDetail
import com.example.movielite.response.popularresponse.Movie
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import timber.log.Timber


class MovieDetailFragment : Fragment() {
    private var binding: FragmentMovieDetailBinding? = null
    private val movieDetail: MovieDetail? = null
    private var TAG = "Debug"
    private lateinit var movie:Movie
//    private lateinit var binding1: FragmentMovieDetailBinding

    private val viewModel: MovieDetailFragmentViewModel by lazy {
        ViewModelProvider(this, MovieDetailViewModelFactory(MovieDetailRepository(MovieApi.retrofitService)))
            .get(MovieDetailFragmentViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this
//        binding1 = DataBindingUtil.setContentView(this, R.layout.fragment_movie_detail)
//        binding1.lifecycleOwner = this
        binding = FragmentMovieDetailBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            movie = it.get(ID_ARGS) as Movie
        }

        activity?.title = movie.title
        viewModel.popularMoviesDetailLiveData.observe(requireActivity(), {
//            binding?.imageViewBackdrop?.load("https://image.tmdb.org/t/p/w780${it.backdrop_path}")
            binding?.textViewOverView?.text = it.overview
            binding?.more?.setOnClickListener {
                binding?.textViewOverView?.ellipsize = null
                binding?.textViewOverView?.maxLines = Integer.MAX_VALUE
            }
            binding?.textViewMovieTitleWithDate?.text = it.title
                        it.videos?.videoResponses?.forEach { video ->
                when(video.name) {
                    "Official Main Trailer" -> {
                        video.key?.let { it1 -> handlePlayer(it1) }
                    }
                    else -> {
                        video.key?.let { it1 -> handlePlayer(it1) }
                    }
                }
            }
        })
        viewModel.getPopularMovieDetails(movie.id!!)

    }

        private fun handlePlayer(key: String) {
        binding?.youtubePlayer
            ?.addYouTubePlayerListener(object : AbstractYouTubePlayerListener(){
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    super.onReady(youTubePlayer)
                    youTubePlayer.cueVideo(key, 0f)
                    Timber.d("Player key $key")
                }
            })
    }
}