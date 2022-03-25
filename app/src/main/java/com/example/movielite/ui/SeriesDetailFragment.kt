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
import com.example.movielite.databinding.FragmentSeriesDetailBinding
import com.example.movielite.network.MovieApi
import com.example.movielite.network.repository.MovieDetailRepository
import com.example.movielite.response.shows.Series
import com.example.movielite.service.getGenre
import com.example.movielite.viewmodel.DetailViewModel

class SeriesDetailFragment : Fragment() {

    private var _binding: FragmentSeriesDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var series: Series

    private val viewModel: DetailViewModel by lazy {
        ViewModelProvider(this, MovieDetailViewModelFactory(MovieDetailRepository(MovieApi.retrofitService)))
            .get(DetailViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSeriesDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val seriesResult = requireArguments().getInt("tv")

        binding.toolbar.setOnClickListener{
            findNavController().navigate(
                R.id.action_seriesDetailFragment_to_topSeriesFragment
            )
        }

        viewModel.tvDetailLiveData.observe(requireActivity()) {
            binding.seriesImage.load("https://image.tmdb.org/t/p/w780${it.backdropPath}")
            binding.seriesName.text = it.name
            binding.releaseDate.text = it.firstAirDate
            binding.voting.text = it.voteAverage.toString()
            binding.voteCount.text = it.voteCount.toString()
            binding.overView.text = it.overview
            binding.seasonNo.text = it.numberOfSeasons.toString()
            binding.episodesNo.text = it.numberOfEpisodes.toString()
            binding.status.text = it.status
            binding.genre.text = getGenre(it.genres)
        }

        viewModel.getSeriesDetails(seriesResult)
    }
}