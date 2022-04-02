package com.example.movielite.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.movielite.R
import com.example.movielite.ViewModelFactory.TopRatedViewModelFactory
import com.example.movielite.adapter.ArtistAdapter
import com.example.movielite.databinding.FragmentArtistBinding
import com.example.movielite.network.MovieApi
import com.example.movielite.network.repository.TopRatedRepository
import com.example.movielite.response.artistresponse.Artist
import com.example.movielite.viewmodel.ArtistViewModel
import com.example.movielite.viewmodel.TopRatedViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ArtistFragment : Fragment(), (Artist) -> Unit {

    private val TAG = "Debug"
    private var _binding: FragmentArtistBinding? = null
    private val binding get() = _binding!!

    private val artistViewModel by viewModels<ArtistViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentArtistBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val artistsAdapter = ArtistAdapter(this)
        binding.artists.apply {
            binding.artists.adapter = artistsAdapter
            layoutManager = StaggeredGridLayoutManager(3, RecyclerView.VERTICAL)
        }

        lifecycleScope.launch {
            artistViewModel.artistPaging.collectLatest {
                //submit list to list adapter
                artistsAdapter.submitData(it)
                binding.artists.visibility = View.VISIBLE
            }
        }
    }

    override fun invoke(artist: Artist) {
        findNavController().navigate(R.id.action_artistFragment_to_artistDetailFragment, Bundle().apply {
            putInt("person", artist.id!!)
        })
    }
}