package com.example.weatherapp.data.db

import android.content.Context
import androidx.room.*
import com.example.weatherapp.data.db.entity.CurrentWeatherEntry
import com.example.weatherapp.data.db.entity.FutureWeatherEntry
import com.example.weatherapp.data.db.entity.WeatherLocation

@Database(
    entities = [CurrentWeatherEntry::class, FutureWeatherEntry::class, WeatherLocation::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(LocalDateConverter::class)
abstract class ForecastDatabase : RoomDatabase(){
    abstract fun currentWeatherDao(): CurrentWeatherDao
    abstract fun futureWeatherDao(): FutureWeatherDao
    abstract fun weatherLocationDao(): WeatherLocationDao

    companion object{
        @Volatile private var instance: ForecastDatabase?=null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also{ instance = it}
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                ForecastDatabase::class.java, "forecast.db")
                .build()
    }
}

