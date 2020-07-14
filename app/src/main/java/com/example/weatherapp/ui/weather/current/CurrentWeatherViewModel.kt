package com.example.weatherapp.ui.weather.current

import androidx.lifecycle.ViewModel
import com.example.weatherapp.data.repository.ForecastRepository
import com.example.weatherapp.internal.UnitSystem
import com.example.weatherapp.internal.lazyDeferred

class CurrentWeatherViewModel(
    private val forecastRepository: ForecastRepository
) : ViewModel() {
    private val unitSystem = UnitSystem.METRIC  //get from settings

    val isMetric: Boolean
        get() = unitSystem == UnitSystem.METRIC

    val weather by lazyDeferred {
        forecastRepository.getCurrentWeather(isMetric)
    }
}
