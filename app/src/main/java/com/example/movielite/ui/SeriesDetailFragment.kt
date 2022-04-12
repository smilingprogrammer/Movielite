package com.example.movielite.ui

import android.app.Dialog
import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.movielite.R
import com.example.movielite.ViewModelFactory.MovieDetailViewModelFactory
import com.example.movielite.adapter.CastAdapter
import com.example.movielite.databinding.FragmentSeriesDetailBinding
import com.example.movielite.network.MovieApi
import com.example.movielite.network.repository.MovieDetailRepository
import com.example.movielite.response.castandcrew.Cast
import com.example.movielite.response.shows.Series
import com.example.movielite.service.getGenre
import com.example.movielite.viewmodel.DetailViewModel

class SeriesDetailFragment : Fragment(), (Cast) -> Unit {

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

        val castAdapter = CastAdapter(this)
        binding.seriesCast.adapter = castAdapter

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
            castAdapter.submitList(it.credits.casts)
        }

        viewModel.getSeriesDetails(seriesResult)
    }

    override fun invoke(cast: Cast) {
        val manager = requireActivity().applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager
        val networkInfo = manager.activeNetworkInfo
        if(networkInfo != null){
            findNavController().navigate(R.id.action_seriesDetailFragment_to_artistDetailFragment,
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