package com.example.movielite.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.example.movielite.databinding.GridViewItemBinding
import com.example.movielite.network.MovieApi
import kotlinx.coroutines.launch

class MainViewModel(private val binding: GridViewItemBinding) :
    RecyclerView.ViewHolder(binding.root){
        fun bind() {
        }
}