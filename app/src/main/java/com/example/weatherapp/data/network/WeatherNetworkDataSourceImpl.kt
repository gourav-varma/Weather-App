package com.example.weatherapp.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
//import com.example.weatherapp.data.network.WeatherApiService
import com.example.weatherapp.data.network.response.CurrentWeatherResponse
import com.example.weatherapp.internal.NoConnectivityException

class WeatherNetworkDataSourceImpl(
    private val weatherApiService: WeatherApiService
) : WeatherNetworkDataSource {

    private val _downloadedCurrentWeather = MutableLiveData<CurrentWeatherResponse>()
    override val downloadedCurrentWeather: LiveData<CurrentWeatherResponse>
        get() = _downloadedCurrentWeather

    override suspend fun fetchCurrentWeather(location: String) {
        try{
            val fetchedCurrentWeather = weatherApiService
                .getCurrentWeather(location)
                .await()
            _downloadedCurrentWeather.postValue(fetchedCurrentWeather)
        }catch (e: NoConnectivityException){
            Log.e("Connectivity", "No internet connection", e)
        }
    }
}