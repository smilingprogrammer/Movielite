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
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingData
import com.example.movielite.R
import com.example.movielite.adapter.SearchAdapter
import com.example.movielite.databinding.FragmentDiscoverBinding
import com.example.movielite.response.search.SearchResult
import com.example.movielite.viewmodel.MultiSearchViewModel
import com.example.movielite.viewmodel.SearchViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class DiscoverFragment : Fragment(), (SearchResult) -> Unit {

    private var _binding: FragmentDiscoverBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<MultiSearchViewModel>()
    private val searchViewModel by viewModels<SearchViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentDiscoverBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this
        val adapter = SearchAdapter(this)
        binding.results.adapter = adapter

        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    searchViewModel.updateSearchQuery(it)
                    return true
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    searchViewModel.updateSearchQuery(it)
                    when {
                        it.isEmpty() -> {
                            adapter.submitData(lifecycle, PagingData.empty())
                            binding.searchDesc.visibility = View.VISIBLE
                        }
                    }
                }
                return true
            }
        })

        searchViewModel.query.observe(viewLifecycleOwner) {
            viewModel.updateSearch(it)
        }

        viewModel.liveQuery.observe(viewLifecycleOwner) {
            lifecycleScope.launch {
                viewModel.getSearchPaging(it, true).collectLatest { data ->
                    binding.searchDesc.visibility = View.GONE
                    adapter.submitData(lifecycle, data)
                }
            }
        }
    }

    override fun invoke(data: SearchResult) {
        data.let {
            val manager = requireActivity().applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE)
                    as ConnectivityManager
            val networkInfo = manager.activeNetworkInfo
            if(networkInfo != null){
                when {
                    it.mediaType.equals("person") -> findNavController().navigate(
                        R.id.action_discoverFragment_to_artistDetailFragment, Bundle().apply {
                            putInt("person", data.id!!)
                        })
                    it.mediaType.equals("movie") -> findNavController().navigate(
                        R.id.action_discoverFragment_to_movieDetailFragment, Bundle().apply{
                            putInt("movie", data.id!!)
                        })
                    it.mediaType.equals("tv") -> findNavController().navigate(
                        R.id.action_discoverFragment_to_seriesDetailFragment, Bundle().apply {
                            putInt("tv", data.id!!)
                        })
                }
            } else {
                val dialog = Dialog(requireActivity())
                dialog.setContentView(R.layout.no_internet)
                dialog.setCancelable(true)
                dialog.window!!.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)
                dialog.show()
            }
        }
    }


}