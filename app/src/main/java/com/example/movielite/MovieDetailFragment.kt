package com.example.movielite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.example.movielite.MainFragment.Companion.ID_ARGS
import com.example.movielite.databinding.FragmentMovieDetailBinding
import com.example.movielite.main.MainViewModel
import com.example.movielite.main.MainViewModelFactory
import com.example.movielite.network.Movie
import com.example.movielite.network.MovieApi
import com.example.movielite.network.repository.MovieRepository

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MovieDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MovieDetailFragment : Fragment() {

    private var binding: FragmentMovieDetailBinding? = null
    private val movieDetail = mutableListOf<Movie>()

    private val viewModel: MovieDetailFragmentViewModel by lazy {
        ViewModelProvider(this, MovieDetailViewModelFactory(MovieRepository(MovieApi.retrofitService)))
            .get(MovieDetailFragmentViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this
        binding = FragmentMovieDetailBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var id = 0
        arguments?.let {
            id = it.getInt(ID_ARGS).toInt()
        }
        viewModel.getPopularMovieDetail(id)
        viewModel.popularMoviesDetailLiveData.observe(/*this*/viewLifecycleOwner, Observer {
            movieDetail.addAll(it)
        })
        binding?.imageViewBackdrop?.load("https://image.tmdb.org/t/p/w342${movieDetail[0].backdropPath}")
        binding?.textViewOverView?.text = movieDetail[0].overview
        activity?.title = movieDetail[0].title
    }
}