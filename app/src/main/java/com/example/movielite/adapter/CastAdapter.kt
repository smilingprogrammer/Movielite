package com.example.movielite.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.movielite.databinding.CastItemBinding
import com.example.movielite.response.castandcrew.Cast

class CastAdapter(private val listener: (Cast) -> Unit)
    :ListAdapter<Cast, CastAdapter.CastViewHolder>(ItemCallback()) {

    private class ItemCallback : DiffUtil.ItemCallback<Cast>() {

        override fun areItemsTheSame(oldItem: Cast, newItem: Cast): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Cast, newItem: Cast): Boolean {
            return oldItem == newItem
        }
    }

        inner class CastViewHolder(private val binding: CastItemBinding)
            :RecyclerView.ViewHolder(binding.root){
                fun bind(cast: Cast){
                    binding.imageView.load("https://image.tmdb.org/t/p/w780${cast.profilePath}")
                    binding.character.text = cast.character
                    binding.name.text = cast.name
                    binding.castAll.setOnClickListener {
                        listener.invoke(cast)
                    }
                }
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        return CastViewHolder(
            CastItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }
}