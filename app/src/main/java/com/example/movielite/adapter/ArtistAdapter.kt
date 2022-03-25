package com.example.movielite.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.movielite.databinding.ArtistItemBinding
import com.example.movielite.response.artistresponse.Artist

class ArtistAdapter(private val listener: (Artist) -> Unit
        ): ListAdapter<Artist, ArtistAdapter.ArtistViewHolder>(ArtistCallback()) {

    private class ArtistCallback : DiffUtil.ItemCallback<Artist>() {
        override fun areItemsTheSame(oldItem: Artist, newItem: Artist): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Artist, newItem: Artist): Boolean {
            return oldItem == newItem
        }
    }

    inner class ArtistViewHolder(private val binding: ArtistItemBinding):
            RecyclerView.ViewHolder(binding.root){
                fun bind(artist: Artist) {
                    binding.artistImage.load("https://image.tmdb.org/t/p/w342${artist.profile_path}")
                    binding.artistName.text = artist.name

                    binding.artistImage.setOnClickListener {
                        listener.invoke(artist)
                    }
                }
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistViewHolder {
        return ArtistViewHolder(
            ArtistItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

}