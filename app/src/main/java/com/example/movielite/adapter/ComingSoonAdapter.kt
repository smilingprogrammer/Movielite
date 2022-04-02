package com.example.movielite.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movielite.databinding.ComingSoonItemBinding
import com.example.movielite.response.upcoming.Upcoming
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import timber.log.Timber

class ComingSoonAdapter(private val upcoming: List<Upcoming>)
    : RecyclerView.Adapter<ComingSoonAdapter.ComingSoonViewHolder>() {

    inner class ComingSoonViewHolder(private val binding: ComingSoonItemBinding)
        :RecyclerView.ViewHolder(binding.root){
            fun bind(upcoming: Upcoming){

            }

        private fun handlePlayer(key: String) {

            binding.youTubePlayer.addYouTubePlayerListener(object : AbstractYouTubePlayerListener(){
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    super.onReady(youTubePlayer)
                    youTubePlayer.cueVideo(key, 0f)
                    Timber.d("Player key $key")
                }
            })
        }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComingSoonViewHolder {
        return ComingSoonViewHolder(
            ComingSoonItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ComingSoonViewHolder, position: Int) {
        holder.bind(upcoming[position])
    }

    override fun getItemCount(): Int {
        return upcoming.size
    }
}