package com.example.movielite.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.movielite.R
import com.example.movielite.viewmodel.RecommendedViewModel

class RecommendedFragment : Fragment() {

    companion object {
        fun newInstance() = RecommendedFragment()
    }

    private lateinit var viewModel: RecommendedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recommended, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RecommendedViewModel::class.java)
        // TODO: Use the ViewModel
    }

}