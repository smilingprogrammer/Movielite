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
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movielite.R
import com.example.movielite.adapter.AllSeriesAdapter
import com.example.movielite.databinding.FragmentAllSeriesBinding
import com.example.movielite.response.shows.Series
import com.example.movielite.service.SeriesType
import com.example.movielite.viewmodel.AllSeriesViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber

class AllSeriesFragment : Fragment(), (Series) -> Unit {

    private var _binding: FragmentAllSeriesBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<AllSeriesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAllSeriesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this
        val seriesType = requireArguments().getSerializable("allSeriesId") as SeriesType

        val adapter= AllSeriesAdapter(this)
        binding.allSeries.apply {
            binding.allSeries.adapter = adapter
            layoutManager = GridLayoutManager(activity, 2)
        }

        lifecycleScope.launch {
            viewModel.pagingFlow(seriesType).collectLatest {
                Timber.d("all series $viewModel")
                adapter.submitData(it)
            }
        }
    }

    override fun invoke(series: Series) {
        val manager = requireActivity().applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager
        val networkInfo = manager.activeNetworkInfo
        if(networkInfo != null){
            findNavController().navigate(
                R.id.action_allSeriesFragment_to_seriesDetailFragment,
                Bundle().apply
                { putInt("tv", series.id) })
        } else {
            val dialog = Dialog(requireActivity())
            dialog.setContentView(R.layout.no_internet)
            dialog.setCancelable(true)
            dialog.window!!.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)
            dialog.show()
        }
    }

}