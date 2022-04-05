package com.example.movielite.ui

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.example.movielite.R
import com.example.movielite.ViewModelFactory.MainViewModelFactory
import com.example.movielite.adapter.ImageSliderAdapter
import com.example.movielite.adapter.MainAdapter
import com.example.movielite.databinding.FragmentMainBinding
import com.example.movielite.network.MovieApi
import com.example.movielite.network.repository.MovieRepository
import com.example.movielite.response.popularresponse.Movie
import com.example.movielite.response.upcoming.Upcoming
import com.example.movielite.viewmodel.MainViewModel
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView
import timber.log.Timber

class MainFragment : Fragment(), (Any) -> Unit{

    private lateinit var viewPager2: ViewPager2
    private val sliderHandler = Handler()
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var sliderView: SliderView

    private var movies = mutableListOf<Movie>()
    private var upcoming = mutableListOf<Upcoming>()
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
        viewModel.upcomingLiveData.observe(viewLifecycleOwner) {
            Timber.d("${viewModel.upcomingLiveData.value}")
            upcoming.addAll(it)
            val upcomingId = it[0].id
            sliderView = binding.imageSlider
            val imageSliderAdapter = ImageSliderAdapter(upcoming, this)
            sliderView.setSliderAdapter(imageSliderAdapter)
            sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM)
            sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION)
            sliderView.startAutoCycle()
        }
        viewModel.popularMoviesLiveData.observe(viewLifecycleOwner) {
            viewPager2 = binding.viewPager2
            movies.addAll(it)
            viewPager2.adapter = MainAdapter(movies, this)
            viewPager2.clipToPadding = false
            viewPager2.clipChildren = false
            viewPager2.offscreenPageLimit = 3
            viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

            val compositePageTransformer = CompositePageTransformer()
            compositePageTransformer.addTransformer(MarginPageTransformer(40))
            compositePageTransformer.addTransformer { page, position ->
                val r = 1 - Math.abs(position)
                page.scaleY = 0.85f + r * 0.15f
            }
            viewPager2.setPageTransformer(compositePageTransformer)
            viewPager2.registerOnPageChangeCallback(object : OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    sliderHandler.removeCallbacks(sliderRunnable)
                    sliderHandler.postDelayed(sliderRunnable, 3000)
                }
            })
        }
    }

    private val sliderRunnable =
        Runnable { viewPager2.currentItem = viewPager2.currentItem + 1 }

    override fun invoke(any: Any) {
        when (any) {
            is Upcoming -> {
                findNavController().navigate(R.id.action_mainFragment_to_movieDetailFragment,
                Bundle().apply
                 { putInt("movie", any.id!!) })
            }
            is Movie -> {
                findNavController().navigate(R.id.action_mainFragment_to_movieDetailFragment,
                Bundle().apply
                 { putInt("movie", any.id!!) })
            }
        }
    }

}