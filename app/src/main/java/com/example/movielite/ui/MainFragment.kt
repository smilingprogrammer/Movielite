package com.example.movielite.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.example.movielite.R
import com.example.movielite.ViewModelFactory.MainViewModelFactory
import com.example.movielite.adapter.MainAdapter
import com.example.movielite.databinding.FragmentMainBinding
import com.example.movielite.network.MovieApi
import com.example.movielite.network.repository.MovieRepository
import com.example.movielite.response.popularresponse.Movie
import com.example.movielite.viewmodel.MainViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private var movies = mutableListOf<Movie>()
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this, MainViewModelFactory(MovieRepository(MovieApi.retrofitService)))
            .get(MainViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.popularMoviesLiveData.observe(/*this*/viewLifecycleOwner, {
            movies.addAll(it)
            val adapter = MainAdapter(movies){
                findNavController().navigate(R.id.action_mainFragment_to_movieDetailFragment,
                bundleOf(ID_ARGS to it))
            }
            binding.show.layoutManager =
                StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)
            binding.show.adapter = adapter

            adapter.notifyDataSetChanged()
        })
    }

    companion object {
        val ID_ARGS = MainFragment::class.java.simpleName
    }

}