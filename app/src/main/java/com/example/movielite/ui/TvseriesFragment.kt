package com.example.movielite.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movielite.R
import com.example.movielite.ViewModelFactory.MainViewModelFactory
import com.example.movielite.adapter.SeriesAdapter
import com.example.movielite.databinding.FragmentTvseriesBinding
import com.example.movielite.network.MovieApi
import com.example.movielite.network.repository.MovieRepository
import com.example.movielite.response.shows.Series
import com.example.movielite.viewmodel.MainViewModel

class TvseriesFragment : Fragment(), (Series) -> Unit {

    private var _binding: FragmentTvseriesBinding? = null
    private val binding get() = _binding!!
    private var series = mutableListOf<Series>()

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this, MainViewModelFactory(MovieRepository(MovieApi.retrofitService)))
            .get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentTvseriesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.popularSeriesLiveData.observe(viewLifecycleOwner) {
            series.addAll(it)
            binding.series.layoutManager =
                GridLayoutManager(activity, 2)
            val adapter = SeriesAdapter(this)
            binding.series.adapter = adapter
        }
    }

    override fun invoke(series: Series) {
        findNavController().navigate(R.id.action_topSeriesFragment_to_seriesDetailFragment,
        Bundle().apply
         { putInt("tv", series.id) })
    }
}