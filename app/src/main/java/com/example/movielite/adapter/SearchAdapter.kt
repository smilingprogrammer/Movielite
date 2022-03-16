package com.example.movielite.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.movielite.databinding.ItemSearchBinding
import com.example.movielite.response.search.SearchResult

class SearchAdapter(private val searchResult: List<SearchResult>)
    : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    inner class SearchViewHolder(private val binding: ItemSearchBinding):
            RecyclerView.ViewHolder(binding.root){
                fun bind(searchResult: SearchResult){
                    binding.searchImage.load("https://image.tmdb.org/t/p/w342${searchResult.posterPath}")
                    binding.title.text = searchResult.name
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
        holder.bind(searchResult[position])
    }

    override fun getItemCount(): Int {
        return searchResult.size
    }
}