package com.example.movielite.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.movielite.databinding.ItemSeriesBinding
import com.example.movielite.response.artistresponse.Artist
import com.example.movielite.response.shows.Series

class SeriesAdapter(private val listener: (Series) -> Unit):
    ListAdapter<Series, SeriesAdapter.SeriesViewHolder>(SeriesCallBack()) {

    private class SeriesCallBack : DiffUtil.ItemCallback<Series>() {
        override fun areItemsTheSame(oldItem: Series, newItem: Series): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Series, newItem: Series): Boolean {
            return oldItem == newItem
        }
    }

    inner class SeriesViewHolder(private val binding: ItemSeriesBinding):
            RecyclerView.ViewHolder(binding.root){
                fun bind(series: Series){
                    binding.seriesImage.load("https://image.tmdb.org/t/p/w780${series.backdropPath}")
                    binding.seriesTitle.text = series.name
                    binding.seriesAirDate.text = series.firstAirDate
                    binding.textView2.text = series.voteAverage.toString()
                    binding.seriesImage.setOnClickListener {
                        listener.invoke(series)
                    }
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
        getItem(position)?.let {
            holder.bind(it)
        }
    }

}