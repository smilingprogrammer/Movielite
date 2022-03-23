package com.example.movielite.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.movielite.ViewModelFactory.MovieDetailViewModelFactory
import com.example.movielite.databinding.FragmentMovieDetailBinding
import com.example.movielite.network.MovieApi
import com.example.movielite.network.repository.MovieDetailRepository
import com.example.movielite.response.moviedetailresponse.MovieDetail
import com.example.movielite.response.popularresponse.Movie
import com.example.movielite.response.search.SearchResult
import com.example.movielite.ui.DiscoverFragment.Companion.ID_AGS
import com.example.movielite.ui.MainFragment.Companion.ID_ARGS
import com.example.movielite.viewmodel.DetailViewModel
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import timber.log.Timber


class MovieDetailFragment : Fragment() {
    private var binding: FragmentMovieDetailBinding? = null
    private val movieDetail: MovieDetail? = null
    private var TAG = "Debug"
    private lateinit var movie:Movie
    private lateinit var searchResult: SearchResult

    private val viewModel: DetailViewModel by lazy {
        ViewModelProvider(this, MovieDetailViewModelFactory(MovieDetailRepository(MovieApi.retrofitService)))
            .get(DetailViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieDetailBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            movie = it.get(ID_ARGS) as Movie
        }

        viewModel.moviesDetailLiveData.observe(requireActivity()) {
//            binding?.imageViewBackdrop?.load("https://image.tmdb.org/t/p/w780${it.backdrop_path}")
            binding?.textViewOverView?.text = it.overview
            binding?.more?.setOnClickListener {
                binding?.textViewOverView?.ellipsize = null
                binding?.textViewOverView?.maxLines = Integer.MAX_VALUE
            }
            lifecycle.addObserver(binding?.youTubePlayerView!!)
//            binding?.textViewMovieTitleWithDate?.text = it.title
            it.videos?.videoResponses?.forEach { video ->
                when (video.name) {
                    "Official Main Trailler" -> {
                        handlePlayer(video.key!!)
                    }
                    else -> {
                        handlePlayer(video.key!!)
                    }
                }
            }
        }
        viewModel.movieDetail(movie.id!!)
//        viewModel.movieDetail(searchResult.id!!)

    }

        private fun handlePlayer(key: String) {

            binding?.youTubePlayerView?.addYouTubePlayerListener(object : AbstractYouTubePlayerListener(){
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    super.onReady(youTubePlayer)
                    youTubePlayer.cueVideo(key, 0f)
                    Timber.d("Player key $key")
                }
            })
    }
}