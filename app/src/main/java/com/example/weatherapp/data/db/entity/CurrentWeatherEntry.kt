package com.example.weatherapp.data.db.entity


import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.weatherapp.Converters
import com.google.gson.annotations.SerializedName

const val CURRENT_WEATHER_ID = 0

@Entity(tableName = "current_weather")
@TypeConverters(value = [(Converters::class)])
data class CurrentWeatherEntry(
    val feelslike: Int,
    @SerializedName("is_day")
    val isDay: String,
    @SerializedName("observation_time")
    val observationTime: String,
    val precip: Double,
    val temperature: Int,
    @SerializedName("weather_descriptions")
    @TypeConverters(Converters::class)
    val weatherDescriptions: List<String>,//list of strings
    @SerializedName("weather_icons")
    @TypeConverters(Converters::class)
    val weatherIcons: List<String>,//list of strings
    @SerializedName("wind_dir")
    val windDir: String,
    @SerializedName("wind_speed")
    val windSpeed: Double,
    val visibility: Double
){
    @PrimaryKey(autoGenerate = false)
    var id: Int = CURRENT_WEATHER_ID
}