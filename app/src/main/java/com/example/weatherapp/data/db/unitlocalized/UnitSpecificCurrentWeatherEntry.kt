package com.example.weatherapp.data.db.unitlocalized

interface UnitSpecificCurrentWeatherEntry {
    val temperature: Int
    val conditionText: String
    val conditionIconUrl: String
    val windSpeed: Double
    val windDirection: String
    val precipitaionVolume: Double
    val feelsLikeTemperature: Int
    val visibilityDistance: Double
}