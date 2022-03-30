package com.example.movielite.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.movielite.databinding.ItemSeriesBinding
import com.example.movielite.response.shows.Series

class AllSeriesAdapter(private val listener: (Series) -> Unit)
    :PagingDataAdapter<Series, AllSeriesAdapter.AllSeriesViewHolder>(ListItemCallback()) {

    private class ListItemCallback : DiffUtil.ItemCallback<Series>() {
        override fun areItemsTheSame(oldItem: Series, newItem: Series): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Series, newItem: Series): Boolean {
            return oldItem == newItem
        }
    }

    inner class AllSeriesViewHolder(private val binding: ItemSeriesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(series: Series) {
            binding.seriesImage.load("https://image.tmdb.org/t/p/w780${series.backdropPath}")
            binding.seriesTitle.text = series.name
            binding.seriesAirDate.text = series.firstAirDate

            binding.seriesImage.setOnClickListener {
                listener.invoke(series)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllSeriesViewHolder {
        return AllSeriesViewHolder(
            ItemSeriesBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: AllSeriesViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

}