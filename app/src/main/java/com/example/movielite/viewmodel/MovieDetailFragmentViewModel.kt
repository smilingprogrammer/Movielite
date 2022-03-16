package com.example.movielite.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movielite.response.moviedetailresponse.MovieDetail
import com.example.movielite.network.repository.MovieDetailRepository
import com.example.movielite.response.artistresponse.ArtistInfo
import com.example.movielite.response.search.Search
import com.example.movielite.response.search.SearchResult
import kotlinx.coroutines.launch

class MovieDetailFragmentViewModel(private val movieDetailRepository: MovieDetailRepository): ViewModel() {

    private val TAG = MovieDetailFragmentViewModel::class.java.simpleName

    private val _popularMoviesDetailLiveData = MutableLiveData<MovieDetail>()
    val popularMoviesDetailLiveData: LiveData<MovieDetail>
        get() = _popularMoviesDetailLiveData

    private val _artistDetailsLiveData = MutableLiveData<ArtistInfo>()
    val artistDetailsLiveData: LiveData<ArtistInfo>
    get() = _artistDetailsLiveData

    private val _searchLiveData = MutableLiveData<List<SearchResult>>()
    val searchLiveData: LiveData<List<SearchResult>>
            get() = _searchLiveData

    init {
        search()
    }

//    private val pagingConfig = PagingConfig(pageSize = 20, initialLoadSize = 10, enablePlaceholders = false)
//    fun getSearchPaging(query: String, adult: Boolean) = Pager(pagingConfig) {
//        getDataSource(query, adult)
//    }.flow.cachedIn(viewModelScope)

    fun getPopularMovieDetails(movieId: Int)  {
        try {
            viewModelScope.launch {
                _popularMoviesDetailLiveData.value = movieDetailRepository.getMovieDetails(
                    movieId, TMDB_API_KEY, "en-US", "images, reviews, credits, videos")
            }
        } catch (e: Exception) {
            Log.e(TAG, e.message.toString())
        }
    }

    fun getArtistDetails(personId: Int) {
        try{
            viewModelScope.launch {
                _artistDetailsLiveData.value = movieDetailRepository.getArtistDetails(
                        personId, TMDB_API_KEY, "movie_credits,tv_credits,images,tagged_images"
                        )
            }
        } catch (e: java.lang.Exception){
            Log.e(TAG, e.message.toString())
        }
    }

    fun search(){
        try {
            viewModelScope.launch {
                _searchLiveData.value = movieDetailRepository.search(
                    TMDB_API_KEY, "", 1, true
                ).searchResult
            }
        }
        catch (e: Exception){
            Log.e(TAG, e.message.toString())
        }
    }
}