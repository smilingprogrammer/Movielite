package com.example.movielite.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.movielite.R
import com.example.movielite.ViewModelFactory.MovieDetailViewModelFactory
import com.example.movielite.adapter.CastAdapter
import com.example.movielite.databinding.FragmentMovieDetailBinding
import com.example.movielite.network.MovieApi
import com.example.movielite.network.repository.MovieDetailRepository
import com.example.movielite.response.castandcrew.Cast
import com.example.movielite.service.getGenre
import com.example.movielite.viewmodel.DetailViewModel
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import timber.log.Timber


class MovieDetailFragment : Fragment(), (Cast) -> Unit {

    private lateinit var binding: FragmentMovieDetailBinding

    private val viewModel: DetailViewModel by lazy {
        ViewModelProvider(this, MovieDetailViewModelFactory(MovieDetailRepository(MovieApi.retrofitService)))
            .get(DetailViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_detail,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val result = requireArguments().getInt("movie")

        val castAdapter = CastAdapter(this)
        binding.movieCast.adapter = castAdapter

        viewModel.moviesDetailLiveData.observe(requireActivity()) {
            binding.movieDetail = it
            castAdapter.submitList(it.credits.casts)
            binding.genre.text = getGenre(it.genres)
            lifecycle.addObserver(binding.youTubePlayerView)
            it.videos.videoResponses?.forEach { video ->
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
        viewModel.movieDetail(result)

    }

        private fun handlePlayer(key: String) {

            binding.youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener(){
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    super.onReady(youTubePlayer)
                    youTubePlayer.cueVideo(key, 0f)
                    Timber.d("Player key $key")
                }
            })
    }

    override fun invoke(cast: Cast) {
        findNavController().navigate(R.id.action_movieDetailFragment_to_artistDetailFragment,
            Bundle().apply
            { putInt("person", cast.id) })
    }
}