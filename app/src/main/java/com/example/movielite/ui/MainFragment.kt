package com.example.movielite.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.SnapHelper
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.example.movielite.R
import com.example.movielite.ViewModelFactory.MainViewModelFactory
import com.example.movielite.adapter.FinalFragment
import com.example.movielite.adapter.MainAdapter
import com.example.movielite.adapter.VPAdapter
import com.example.movielite.databinding.FragmentMainBinding
import com.example.movielite.network.MovieApi
import com.example.movielite.network.repository.MovieRepository
import com.example.movielite.response.popularresponse.Movie
import com.example.movielite.viewmodel.MainViewModel
import com.google.android.material.tabs.TabLayout

class MainFragment : Fragment() {

    private var tabLayout: TabLayout? = null
    private var viewPager: ViewPager? = null

    private lateinit var viewPager2: ViewPager2
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var snapHelper: SnapHelper
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
                findNavController().navigate(R.id.action_mainFragment_to_movieDetailFragment,
                bundleOf(ID_ARGS to it))
            }
//            binding.show.layoutManager =
//                StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)
//            binding.show.adapter = adapter

            tabLayout = binding.tabLayout
            viewPager = binding.viewPager

            tabLayout!!.setupWithViewPager(viewPager)
            val vpAdapter = VPAdapter(
                getSupportFragmentManager(),
                FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
            )
            vpAdapter.addFragment(MainFragment(), "Home")
            vpAdapter.addFragment(TopRatedFragment(), "Top Rated")
            vpAdapter.addFragment(FinalFragment(), "Wednesday")
            viewPager!!.adapter = vpAdapter


//            snapHelper = PagerSnapHelper()
//            layoutManager = ProminentLayoutManager(this)
//
//            val spacing = resources.getDimensionPixelSize(R.dimen.carousel_spacing)
//            addItemDecoration(LinearHorizontalSpacingDecoration(spacing))
//            addItemDecoration(BoundsOffsetDecoration())
//
//            snapHelper.attachToRecyclerView(binding.show)
//
//            initRecyclerViewPosition(position)
//
//            val videoView = binding.comingSoonVideo
//            val onlineUri = Uri.parse("")
//            videoView.setVideoURI(onlineUri)
//            videoView.requestFocus()
//            videoView.start()
            adapter.notifyDataSetChanged()
        })

    }

//    private fun initRecyclerViewPosition(position: Int) {
//        // This initial scroll will be slightly off because it doesn't respect the SnapHelper.
//        // Do it anyway so that the target view is laid out, then adjust onPreDraw.
//        layoutManager.scrollToPosition(position)
//
//        binding.show.doOnPreDraw {
//            val targetView = layoutManager.findViewByPosition(position) ?: return@doOnPreDraw
//            val distanceToFinalSnap =
//                snapHelper.calculateDistanceToFinalSnap(layoutManager, targetView)
//                    ?: return@doOnPreDraw
//
//            layoutManager.scrollToPositionWithOffset(position, -distanceToFinalSnap[0])
//        }
//    }

    companion object {
        val ID_ARGS = MainFragment::class.java.simpleName
    }

}