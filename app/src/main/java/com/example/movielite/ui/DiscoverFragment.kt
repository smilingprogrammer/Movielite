package com.example.movielite.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingData
import com.example.movielite.R
import com.example.movielite.adapter.SearchAdapter
import com.example.movielite.databinding.FragmentDiscoverBinding
import com.example.movielite.response.search.SearchResult
import com.example.movielite.ui.ArtistFragment.Companion.PID_ARGS
import com.example.movielite.ui.MainFragment.Companion.ID_ARGS
import com.example.movielite.ui.TvseriesFragment.Companion.TV_ARGS
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
            when {
                it.mediaType.equals("person") -> findNavController().navigate(
                    R.id.action_discoverFragment_to_artistDetailFragment, bundleOf(PID_ARGS to data.id))
                it.mediaType.equals("movie") -> findNavController().navigate(
                    R.id.action_discoverFragment_to_movieDetailFragment, bundleOf(ID_AGS to data.id))
                it.mediaType.equals("tv") -> findNavController().navigate(
                    R.id.action_discoverFragment_to_seriesDetailFragment, bundleOf(TV_ARGS to data.id))
            }
        }
    }

    companion object {
        val ID_AGS = DiscoverFragment::class.java.simpleName
    }

}