package com.example.movielite.service

import android.content.Context
import android.content.Intent
import com.example.movielite.ui.ArtistDetailFragment
import com.example.movielite.ui.MovieDetailFragment
import com.example.movielite.ui.SeriesDetailFragment

    fun navigateSeriesDetails(context: Context?, series: Int?) {
        val intent = Intent(context, SeriesDetailFragment::class.java)
        intent.putExtra("series", series)
        context?.startActivity(intent)
    }

    fun navigateMovieDetails(context: Context?, movie: Int?) {
        val intent = Intent(context, MovieDetailFragment::class.java)
        intent.putExtra("movie", movie)
        context?.startActivity(intent)
    }

    fun navigateArtistDetails(context: Context?, id: Int?) {
        val intent = Intent(context, ArtistDetailFragment::class.java)
        intent.putExtra("artist", id)
        context?.startActivity(intent)
    }