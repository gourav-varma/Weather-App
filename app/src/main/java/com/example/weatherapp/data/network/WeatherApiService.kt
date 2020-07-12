package com.example.weatherapp.data.network

import com.example.weatherapp.data.network.response.CurrentWeatherResponse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY = "2005edf1b2517a20ec13c77a8c36fcaa"
//http://api.weatherstack.com/current?access_key=2005edf1b2517a20ec13c77a8c36fcaa&query=india

interface WeatherApiService {

    @GET("current")
    fun getCurrentWeather(
        @Query("query")
        location: String
    ): Deferred<CurrentWeatherResponse>

    companion object{
        operator fun invoke(
            connectivityIntercepter: ConnectivityIntercepter
        ): WeatherApiService {
            val requestInterceptor = Interceptor{
                chain ->
                val url = chain.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("access_key",
                        API_KEY
                    )
                    .build()
                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()
                return@Interceptor chain.proceed(request)
            }
           val okHttpClient = OkHttpClient.Builder()
               .addInterceptor(requestInterceptor)
               .addInterceptor(connectivityIntercepter)
               .build()
            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("http://api.weatherstack.com/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WeatherApiService::class.java)


        }
    }
}