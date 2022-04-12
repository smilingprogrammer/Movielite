package com.example.movielite.ui

import android.app.Dialog
import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import coil.load
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
            binding.movieImage.load("https://image.tmdb.org/t/p/w780${it.backdropPath}")
            val movieDetail = it.id
            binding.button1.setOnClickListener {
                val manager = requireActivity().applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE)
                        as ConnectivityManager
                val networkInfo = manager.activeNetworkInfo
                if(networkInfo != null){
                    findNavController().navigate(R.id.action_movieDetailFragment_to_trailerFragment,
                        Bundle().apply
                        { putInt("trailerId", movieDetail) })
                } else {
                    val dialog = Dialog(requireActivity())
                    dialog.setContentView(R.layout.no_internet)
                    dialog.setCancelable(true)
                    dialog.window!!.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)
                    dialog.show()
                }
            }
        }
        viewModel.movieDetail(result)

    }

    override fun invoke(cast: Cast) {
        val manager = requireActivity().applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager
        val networkInfo = manager.activeNetworkInfo
        if(networkInfo != null){
            findNavController().navigate(R.id.action_movieDetailFragment_to_artistDetailFragment,
                Bundle().apply
                { putInt("person", cast.id) })
        } else {
            val dialog = Dialog(requireActivity())
            dialog.setContentView(R.layout.no_internet)
            dialog.setCancelable(true)
            dialog.window!!.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)
            dialog.show()
        }
    }
}