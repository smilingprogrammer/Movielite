package com.example.movielite.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.movielite.R
import com.example.movielite.ViewModelFactory.MovieDetailViewModelFactory
import com.example.movielite.databinding.FragmentArtistDetailBinding
import com.example.movielite.network.MovieApi
import com.example.movielite.network.repository.MovieDetailRepository
import com.example.movielite.response.artistresponse.Artist
import com.example.movielite.response.castandcrew.MovieCast
import com.example.movielite.response.castandcrew.SeriesCast
import com.example.movielite.ui.ArtistFragment.Companion.PID_ARGS
import com.example.movielite.viewmodel.DetailViewModel

class ArtistDetailFragment : Fragment(), (Any) -> Unit {

    private var _binding: FragmentArtistDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var artist: Artist

    private val viewModel: DetailViewModel by lazy {
        ViewModelProvider(this, MovieDetailViewModelFactory(MovieDetailRepository(MovieApi.retrofitService)))
            .get(DetailViewModel::class.java)
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

        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_artistDetailFragment_to_artistFragment)
        }
        binding.textView20.setOnClickListener{

        }
        binding.textView21.setOnClickListener{

        }

        viewModel.artistDetailsLiveData.observe(requireActivity()) {
            binding.profileImage.load("https://image.tmdb.org/t/p/w780${it.profilePath}")
            binding.artistName.text = it.name
            binding.biography.text = it.biography
            binding.birthday.text = it.birthday
            binding.deathDay.text = it.deathday
            binding.homePage.text = it.homepage
        }

        viewModel.getArtistDetails(artist.id!!)
    }

    override fun invoke(any: Any) {
        when (any) {
            is MovieCast -> {
                navigateMovieDetails(any.id!!)
            }
            is SeriesCast -> {
                navigateSeriesDetails(any.id!!)
            }
        }
    }

    private fun navigateMovieDetails(id: Int) {

    }
    private fun navigateSeriesDetails(id: Int) {

    }
    private fun navigateArtistsCast(id: Int){

    }

}