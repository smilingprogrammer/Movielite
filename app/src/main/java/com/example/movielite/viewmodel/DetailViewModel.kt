package com.example.movielite.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movielite.response.moviedetailresponse.MovieDetail
import com.example.movielite.network.repository.MovieDetailRepository
import com.example.movielite.response.artistresponse.ArtistInfo
import com.example.movielite.response.search.SearchResult
import com.example.movielite.response.shows.SeriesDetails
import kotlinx.coroutines.launch

class DetailViewModel(private val movieDetailRepository: MovieDetailRepository): ViewModel() {

    private val TAG = DetailViewModel::class.java.simpleName

    private val _moviesDetailLiveData = MutableLiveData<MovieDetail>()
    val moviesDetailLiveData: LiveData<MovieDetail>
        get() = _moviesDetailLiveData

    private val _artistDetailsLiveData = MutableLiveData<ArtistInfo>()
    val artistDetailsLiveData: LiveData<ArtistInfo>
    get() = _artistDetailsLiveData

    private val _searchLiveData = MutableLiveData<List<SearchResult>>()
    val searchLiveData: LiveData<List<SearchResult>>
            get() = _searchLiveData

    private val _tvDetailLiveData = MutableLiveData<SeriesDetails>()
    val tvDetailLiveData: LiveData<SeriesDetails>
        get() = _tvDetailLiveData

//    private val pagingConfig = PagingConfig(pageSize = 20, initialLoadSize = 10, enablePlaceholders = false)
//    fun getSearchPaging(query: String, adult: Boolean) = Pager(pagingConfig) {
//        getDataSource(query, adult)
//    }.flow.cachedIn(viewModelScope)

    fun movieDetail(movieId: Int)  {
        try {
            viewModelScope.launch {
                _moviesDetailLiveData.value = movieDetailRepository.getMovieDetails(
                    movieId, TMDB_API_KEY, "en-US", "videos")
                Log.d(TAG, "${_moviesDetailLiveData.value}")
            }
        } catch (e: Exception) {
            Log.e(TAG, e.message.toString())
        }
    }

    fun getSeriesDetails(tvId: Int) {
        try {
            viewModelScope.launch {
                _tvDetailLiveData.value = movieDetailRepository.getSeriesDetails(
                    tvId, TMDB_API_KEY, "images,credits,videos"
                )
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

//    fun search(query: String){
//        try {
//            viewModelScope.launch {
//                _searchLiveData.value = movieDetailRepository.search(
//                    TMDB_API_KEY, query, 1, true
//                ).searchResult
//            }
//        }
//        catch (e: Exception){
//            Log.e(TAG, e.message.toString())
//        }
//    }
}