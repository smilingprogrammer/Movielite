package com.example.movielite.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.movielite.R
import com.example.movielite.ViewModelFactory.MovieDetailViewModelFactory
import com.example.movielite.adapter.TrailerAdapter
import com.example.movielite.databinding.FragmentTrailerBinding
import com.example.movielite.network.MovieApi
import com.example.movielite.network.repository.MovieDetailRepository
import com.example.movielite.response.moviedetailresponse.Videos
import com.example.movielite.viewmodel.DetailViewModel

class TrailerFragment : Fragment() {

    private var _binding: FragmentTrailerBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DetailViewModel by lazy {
        ViewModelProvider(this, MovieDetailViewModelFactory(MovieDetailRepository(MovieApi.retrofitService)))
            .get(DetailViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTrailerBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val videoResult = requireArguments().getInt("trailerId")
        val trailerAdapter = TrailerAdapter()
        binding.comingSoon.adapter = trailerAdapter

        viewModel.moviesDetailLiveData.observe(requireActivity()) {
            trailerAdapter.submitList(it.videos.videoResponses)
        }
        viewModel.movieDetail(videoResult)
    }

}