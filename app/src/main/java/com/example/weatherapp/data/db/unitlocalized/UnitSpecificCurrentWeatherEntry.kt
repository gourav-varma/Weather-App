package com.example.weatherapp.data.db.unitlocalized

interface UnitSpecificCurrentWeatherEntry {
    val temperature: Double
    val conditionText: String
    val conditionIconUrl: String
    val windSpeed: Double
    val windDirection: String
    val precipitaionVolume: Double
    val feelsLikeTemperature: Double
    val visibilityDistance: Double
}