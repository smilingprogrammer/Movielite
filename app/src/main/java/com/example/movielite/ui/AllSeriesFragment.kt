package com.example.movielite.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movielite.adapter.AllSeriesAdapter
import com.example.movielite.adapter.SeriesAdapter
import com.example.movielite.databinding.FragmentAllSeriesBinding
import com.example.movielite.response.shows.Series
import com.example.movielite.service.SeriesType
import com.example.movielite.viewmodel.AllSeriesViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class AllSeriesFragment : Fragment(), (Series) -> Unit {

    private var _binding: FragmentAllSeriesBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<AllSeriesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAllSeriesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val seriesType = requireArguments().getSerializable("allSeries") as SeriesType

        val adapter= AllSeriesAdapter(this)
        binding.allSeries.apply {
            this.adapter
            layoutManager = GridLayoutManager(activity, 2)
        }

        lifecycleScope.launch {
            viewModel.pagingFlow(seriesType).collectLatest {
                adapter.submitData(it)
            }
        }
    }

    override fun invoke(series: Series) {

    }

}