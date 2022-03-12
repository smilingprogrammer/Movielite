package com.example.movielite.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.movielite.ViewModelFactory.MainViewModelFactory
import com.example.movielite.ViewModelFactory.TopRatedViewModelFactory
import com.example.movielite.adapter.ArtistAdapter
import com.example.movielite.adapter.PopularAdapter
import com.example.movielite.adapter.TopRatedAdapter
import com.example.movielite.databinding.FragmentTopRatedBinding
import com.example.movielite.network.MovieApi
import com.example.movielite.network.repository.MovieRepository
import com.example.movielite.network.repository.TopRatedRepository
import com.example.movielite.response.artistresponse.Artist
import com.example.movielite.response.popularresponse.Movie
import com.example.movielite.response.toprated.TopRated
import com.example.movielite.viewmodel.MainViewModel
import com.example.movielite.viewmodel.TopRatedViewModel

class TopRatedFragment : Fragment() {

    private val TAG = "Debug"

    private var _binding: FragmentTopRatedBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: TopRatedAdapter
    private lateinit var adapter1: PopularAdapter

    private var popularMovies = mutableListOf<Movie>()
    private var topRated = mutableListOf<TopRated>()
    private var artist = mutableListOf<Artist>()
    private val viewModel1: MainViewModel by lazy {
        ViewModelProvider(this, MainViewModelFactory(MovieRepository(MovieApi.retrofitService)))
            .get(MainViewModel::class.java)
    }
    private val viewModel: TopRatedViewModel by lazy {
        ViewModelProvider(this, TopRatedViewModelFactory(TopRatedRepository(MovieApi.retrofitService)))
            .get(TopRatedViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTopRatedBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.topRatedLiveData.observe(viewLifecycleOwner, {
            topRated.addAll(it!!)
            val adapter = TopRatedAdapter(topRated)
            binding.topRated.layoutManager =
                StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
            binding.topRated.adapter = adapter
            Log.d(TAG, "toprated")
        })
        viewModel1.popularMoviesLiveData.observe(viewLifecycleOwner, {
            popularMovies.addAll(it)
            val adapter1 = PopularAdapter(popularMovies)
            binding.popularMovies.layoutManager =
                StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)
            binding.popularMovies.adapter = adapter1
        })
        viewModel.popularArtistData.observe(viewLifecycleOwner, {
            artist.addAll(it!!)
            val adapter = ArtistAdapter(artist)
            binding.popularArtist.layoutManager =
                StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
            binding.popularArtist.adapter = adapter
        })
    }
}