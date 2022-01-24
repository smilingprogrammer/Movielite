package com.example.movielite.ui

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.example.movielite.R
import com.example.movielite.adapter.MainAdapter
import com.example.movielite.databinding.FragmentMainBinding
import com.example.movielite.main.MainViewModelFactory
import com.example.movielite.network.Movie
import com.example.movielite.network.MovieApi
import com.example.movielite.network.repository.MovieRepository
import com.example.movielite.viewmodel.MainViewModel
import kotlin.math.abs

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: MainAdapter

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
                findNavController().navigate(
                    R.id.action_mainFragment_to_movieDetailFragment,
                bundleOf(ID_ARGS to it))
            }
            binding.show.layoutManager =
                StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)
            binding.show.adapter = adapter

            binding.show.clipToPadding = false
            binding.show.clipChildren = false
            binding.show.setof = 3
            binding.show.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

            val compositePageTransformer = CompositePageTransformer()
            compositePageTransformer.addTransformer(MarginPageTransformer(20))
            compositePageTransformer.addTransformer{ page, position ->
                val r = 1 - abs(position)
                page.scaleY = 0.85f + r * 0.25f
            }
            binding.show.setPageTransformer(compositePageTransformer)


            val videoView = binding.comingSoonVideo
            val onlineUri = Uri.parse("")
            videoView.setVideoURI(onlineUri)
            videoView.requestFocus()
            videoView.start()
            adapter.notifyDataSetChanged()
        })
    }

    companion object {
        val ID_ARGS = MainFragment::class.java.simpleName
    }

}