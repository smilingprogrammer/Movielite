package com.example.movielite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.movielite.databinding.FragmentMainBinding
import com.example.movielite.main.MainViewModel
import com.example.movielite.main.MainViewModelFactory
import com.example.movielite.network.Movie
import com.example.movielite.network.MovieApi
import com.example.movielite.network.repository.MovieRepository

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
                requireView().findNavController().navigate(R.id.action_mainFragment_to_movieDetailFragment,
                bundleOf(ID_ARGS to it.id))
            }
            binding.show.layoutManager =
                StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL)
            binding.show.adapter = adapter
            adapter.notifyDataSetChanged()
        })
    }

    companion object {
        val ID_ARGS: String = MainFragment::class.java.simpleName + "movieId"
    }

}