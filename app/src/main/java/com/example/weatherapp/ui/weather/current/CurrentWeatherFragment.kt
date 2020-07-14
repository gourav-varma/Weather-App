package com.example.weatherapp.ui.weather.current

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.example.weatherapp.R
import com.example.weatherapp.data.network.ConnectivityIntercepterImpl
import com.example.weatherapp.data.network.WeatherApiService
import com.example.weatherapp.data.network.WeatherNetworkDataSourceImpl
import com.example.weatherapp.ui.base.ScopedFragment
import kotlinx.android.synthetic.main.current_weather_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class CurrentWeatherFragment : ScopedFragment(), KodeinAware {
    override val kodein by closestKodein()

    private val viewModelFactory: CurrentWeatherViewModelFactory by instance()

    private lateinit var viewModel: CurrentWeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.current_weather_fragment, container, false)
    }

    @SuppressLint("UseRequireInsteadOfGet", "FragmentLiveDataObserve")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)
                                    .get(CurrentWeatherViewModel::class.java)

        bindUI()
//        val apiService = WeatherApiService(ConnectivityIntercepterImpl(this.context!!)) //TODO possible error
//        val weatherNetworkDataSource = WeatherNetworkDataSourceImpl(apiService)
//
//        weatherNetworkDataSource.downloadedCurrentWeather.observe(this, Observer {
//            textView.text = it.toString()
//        })
//
//        GlobalScope.launch(Dispatchers.Main) {
//            weatherNetworkDataSource.fetchCurrentWeather("india")
//        }
    }

    @SuppressLint("FragmentLiveDataObserve")
    private fun bindUI() = launch{
        val currentWeather = viewModel.weather.await()
        currentWeather.observe(this@CurrentWeatherFragment, Observer {
            if (it==null) return@Observer
            textView.text = it.toString()
        })
    }

}
