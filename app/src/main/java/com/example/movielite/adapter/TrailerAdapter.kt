package com.example.movielite.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.movielite.databinding.TrailerItemBinding
import com.example.movielite.response.castandcrew.Cast
import com.example.movielite.response.moviedetailresponse.VideoResponse
import com.example.movielite.response.moviedetailresponse.Videos
import com.example.movielite.response.upcoming.Upcoming
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import timber.log.Timber

class TrailerAdapter()
    : ListAdapter<VideoResponse, TrailerAdapter.TrailerViewHolder>(ItemCallback()) {

    private class ItemCallback : DiffUtil.ItemCallback<VideoResponse>() {

        override fun areItemsTheSame(oldItem: VideoResponse, newItem: VideoResponse): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: VideoResponse, newItem: VideoResponse): Boolean {
            return oldItem == newItem
        }
    }

    inner class TrailerViewHolder(private val binding: TrailerItemBinding)
        :RecyclerView.ViewHolder(binding.root){
            fun bind(videoResponses: VideoResponse){
                when(videoResponses.name) {
                    "Official Main Trailer" -> {
                        handlePlayer(videoResponses.key!!)
                    }
                    else -> {
                        handlePlayer(videoResponses.key!!)
                    }
                }
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrailerViewHolder {
        return TrailerViewHolder(
            TrailerItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: TrailerViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

}