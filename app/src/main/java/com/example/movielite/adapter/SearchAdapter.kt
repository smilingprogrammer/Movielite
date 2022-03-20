package com.example.movielite.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.movielite.databinding.ItemSearchBinding
import com.example.movielite.response.search.SearchResult

class SearchAdapter(val listener: (SearchResult) -> Unit)
    : PagingDataAdapter<SearchResult, SearchAdapter.SearchViewHolder>(ListItemCallback()) {

    private class ListItemCallback : DiffUtil.ItemCallback<SearchResult>() {
        override fun areItemsTheSame(oldItem: SearchResult, newItem: SearchResult): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: SearchResult, newItem: SearchResult): Boolean {
            return oldItem == newItem
        }
    }

    inner class SearchViewHolder(private val binding: ItemSearchBinding):
            RecyclerView.ViewHolder(binding.root){
                fun bind(searchResult: SearchResult){
                    binding.search = searchResult
                    binding.searchImage.load("https://image.tmdb.org/t/p/w342${searchResult.posterPath}")
                    binding.title.text = searchResult.name
                    binding.executePendingBindings()

                    binding.searchImage.setOnClickListener {
                        listener.invoke(searchResult)
                    }
                }
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(
            ItemSearchBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
//        holder.bind(searchResult[position])
        getItem(position)?.let {
            holder.bind(it)
        }
    }

//    override fun getItemCount(): Int {
//        return searchResult.size
//    }
}