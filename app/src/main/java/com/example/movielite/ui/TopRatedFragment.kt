package com.example.movielite.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.get
import com.example.movielite.R
import com.example.movielite.ViewModelFactory.TopRatedViewModelFactory
import com.example.movielite.databinding.FragmentTopRatedBinding
import com.example.movielite.network.MovieApi
import com.example.movielite.network.repository.TopRatedRepository
import com.example.movielite.response.toprated.TopRated
import com.example.movielite.viewmodel.TopRatedViewModel

class TopRatedFragment : Fragment() {

    private var _binding: FragmentTopRatedBinding? = null
    private val binding get() = _binding!!

    private var topRated = mutableListOf<TopRated>()
    private val viewModel: TopRatedViewModel by lazy {
        ViewModelProvider(this, TopRatedViewModelFactory(TopRatedRepository(MovieApi.retrofitService)))
            .get(TopRatedViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTopRatedBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}