package com.example.movielite.viewmodel

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.example.movielite.R
import com.example.movielite.ViewModelFactory.MovieDetailViewModelFactory
import com.example.movielite.databinding.FragmentArtistBinding
import com.example.movielite.databinding.FragmentArtistDetailBinding
import com.example.movielite.network.MovieApi
import com.example.movielite.network.repository.MovieDetailRepository
import com.example.movielite.response.artistresponse.Artist
import com.example.movielite.ui.ArtistFragment.Companion.PID_ARGS

class ArtistDetailFragment : Fragment() {

    private var _binding: FragmentArtistDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var artist: Artist

    private val viewModel: MovieDetailFragmentViewModel by lazy {
        ViewModelProvider(this, MovieDetailViewModelFactory(MovieDetailRepository(MovieApi.retrofitService)))
            .get(MovieDetailFragmentViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentArtistDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            artist = it.get(PID_ARGS) as Artist
        }

        viewModel.artistDetailsLiveData.observe(requireActivity(), {
            binding.profileImage.load("https://image.tmdb.org/t/p/w780${it.profilePath}")
            binding.artistName.text = it.name
            binding.biography.text = it.biography
            binding.birthday.text = it.birthday
            binding.deathDay.text = it.deathday
            binding.homePage.text = it.homepage
        })

        viewModel.getArtistDetails(artist.id!!)
    }

}