package com.example.movielite.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movielite.R
import com.example.movielite.adapter.SeriesAdapter
import com.example.movielite.databinding.FragmentTvseriesBinding
import com.example.movielite.response.shows.Series
import com.example.movielite.service.DEFAULT_SERIES_TYPE
import com.example.movielite.service.SeriesType
import com.example.movielite.viewmodel.SeriesViewModel

class TvseriesFragment : Fragment(), (Series) -> Unit {

    private val TAG = "Debuger"

    private var _binding: FragmentTvseriesBinding? = null
    private val binding get() = _binding!!
    private var series = mutableListOf<Series>()
    private var seriesType = DEFAULT_SERIES_TYPE

    private val seriesViewModel by viewModels<SeriesViewModel>()

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

        val adapter = SeriesAdapter(this)

        binding.series.apply {
            binding.series.adapter = adapter
            layoutManager = GridLayoutManager(activity, 2)
        }

        binding.categories.apply {
            lifecycleOwner = this@TvseriesFragment
            setOnSpinnerItemSelectedListener<String> { _, _, newIndex, newItem ->
                seriesType = when(newIndex){
                    0 -> SeriesType.POPULAR
                    1 -> SeriesType.TOP_RATED
                    2 -> SeriesType.NOW_SHOWING
                    3 -> SeriesType.SHOWING_TODAY
                    5 -> SeriesType.TRENDING_TODAY
                    4 -> SeriesType.TRENDING_THIS_WEEK
                    else -> SeriesType.POPULAR
                }
                binding.textHome.text = newItem
                seriesViewModel.updateSeriesType(seriesType)
            }
            setOnSpinnerOutsideTouchListener { _, motionEvent ->
                if (motionEvent.actionButton == 0) {
                    binding.categories.dismiss()
                }
            }
        }

        seriesViewModel.getAllSeries().observe(viewLifecycleOwner) {
            //submit list to list adapter
            adapter.submitList(it)
        }
    }

    override fun invoke(series: Series) {
        findNavController().navigate(R.id.action_topSeriesFragment_to_seriesDetailFragment,
        Bundle().apply
         { putInt("tv", series.id) })
    }
}