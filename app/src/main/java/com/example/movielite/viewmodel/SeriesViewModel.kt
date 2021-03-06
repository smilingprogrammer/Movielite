package com.example.movielite.viewmodel

import androidx.lifecycle.*
import com.example.movielite.network.repository.SeriesRepository
import com.example.movielite.response.shows.Series
import com.example.movielite.service.DEFAULT_SERIES_TYPE
import com.example.movielite.service.SeriesType
import com.example.movielite.service.Result
import kotlinx.coroutines.launch

class SeriesViewModel: ViewModel() {

    private val seriesRepository = SeriesRepository()

    private val seriesType = MutableLiveData(DEFAULT_SERIES_TYPE)
    private val _seriesLiveData = MutableLiveData<List<Series>>()
    val seriesLiveData: LiveData<List<Series>> get() = _seriesLiveData

    fun getAllSeries() = Transformations.switchMap(seriesType) {
        val dataList = MutableLiveData<List<Series>>()
        viewModelScope.launch {
            val result = when (it) {
                SeriesType.TOP_RATED -> getTopRatedSeries()
                SeriesType.SHOWING_TODAY -> getSeriesShowingToday()
                SeriesType.NOW_SHOWING -> getSeriesNowShowing()
                SeriesType.TRENDING_TODAY -> getTodayTrendingSeries()
                SeriesType.TRENDING_THIS_WEEK -> getWeekTrendingSeries()
                else -> getPopularSeries()
            }
            when (result) {
                is Result.Success -> dataList.postValue(result.value.series)
                else -> Result.Error()
            }
        }
        dataList
    }


    private suspend fun getTopRatedSeries() =
        seriesRepository.getTopRatedSeries(TMDB_API_KEY, 1)

    private suspend fun getSeriesShowingToday() = seriesRepository.getSeriesShowingToday(
        TMDB_API_KEY,
        1
    )

    private suspend fun getSeriesNowShowing() =
        seriesRepository.getSeriesNowShowing(TMDB_API_KEY, 1)

    private suspend fun getPopularSeries() =
        seriesRepository.getPopularSeries(TMDB_API_KEY, 1)

    private suspend fun getTodayTrendingSeries() =
        seriesRepository.getTrendingSeries(SeriesRepository.TimeFrame.DAY, 1)

    private suspend fun getWeekTrendingSeries() =
        seriesRepository.getTrendingSeries(SeriesRepository.TimeFrame.WEEK, 1)

    fun updateSeriesType(type: SeriesType) {
        seriesType.postValue(type)
    }
}