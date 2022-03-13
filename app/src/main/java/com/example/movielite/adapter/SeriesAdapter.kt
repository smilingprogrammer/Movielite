package com.example.movielite.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.movielite.databinding.ItemSeriesBinding
import com.example.movielite.response.shows.Series

class SeriesAdapter(private val series: List<Series>):
    RecyclerView.Adapter<SeriesAdapter.SeriesViewHolder>() {

    inner class SeriesViewHolder(private val binding: ItemSeriesBinding):
            RecyclerView.ViewHolder(binding.root){
                fun bind(series: Series){
                    binding.seriesImage.load("https://image.tmdb.org/t/p/w342${series.backdropPath}")
                    binding.seriesTitle.text = series.name
                    binding.seriesAirDate.text = series.firstAirDate

                }
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeriesViewHolder {
        return SeriesViewHolder(
            ItemSeriesBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: SeriesViewHolder, position: Int) {
        holder.bind(series[position])
    }

    override fun getItemCount(): Int {
        return series.size
    }
}