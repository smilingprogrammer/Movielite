package com.example.movielite.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.movielite.databinding.ArtistItemBinding
import com.example.movielite.response.artistresponse.Artist
import com.example.movielite.response.artistresponse.KnownFor

class ArtistAdapter(private val artist: List<Artist>):
    RecyclerView.Adapter<ArtistAdapter.ArtistViewHolder>() {

    inner class ArtistViewHolder(private val binding: ArtistItemBinding):
            RecyclerView.ViewHolder(binding.root){
                fun bind(artist: Artist) {
                    binding.artistImage.load("https://image.tmdb.org/t/p/w342${artist.profile_path}")
                    binding.artistName.text = artist.name
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
        holder.bind(artist[position])
    }

    override fun getItemCount(): Int {
        return artist.size
    }

}