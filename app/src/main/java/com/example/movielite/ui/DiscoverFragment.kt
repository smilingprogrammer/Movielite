package com.example.movielite.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import com.example.movielite.ViewModelFactory.MovieDetailViewModelFactory
import com.example.movielite.adapter.SearchAdapter
import com.example.movielite.databinding.FragmentDiscoverBinding
import com.example.movielite.network.MovieApi
import com.example.movielite.network.repository.MovieDetailRepository
import com.example.movielite.response.search.SearchResult
import com.example.movielite.viewmodel.DetailViewModel
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
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDiscoverBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this
        val adapter = SearchAdapter(this)
        binding.results.adapter = adapter

        binding.searchEditText.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
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
//        viewModel.searchLiveData.observe(viewLifecycleOwner) {
//            searchResult.addAll(it)
//            val adapter = SearchAdapter(searchResult)
//            binding.results.adapter = adapter
//
//            binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
//                override fun onQueryTextSubmit(query: String?): Boolean {
//                    query?.let {
//                        viewModel.search(it)
//                        return true
//                    }
//                    return false
//                }
//
//                override fun onQueryTextChange(newText: String?): Boolean {
//                    newText?.let {
//                        viewModel.search(it)
//                        when{
//                            it.isEmpty() -> {
//                                adapter.notifyDataSetChanged()
//                                binding.searchDesc.visibility = View.VISIBLE
//                            }
//                        }
//                    }
//                    return true
//                }
//
//            })
//
//            viewModel.searchLiveData.observe(this){
//                viewModel.search(it.toString())
//            }
//
//            viewModel.searchLiveData.observe(this){
//                lifecycleScope.launch {
//                    viewModel.search
//                }
//            }
//        }
    }

    override fun invoke(p1: SearchResult) {
        TODO("Not yet implemented")
    }

}