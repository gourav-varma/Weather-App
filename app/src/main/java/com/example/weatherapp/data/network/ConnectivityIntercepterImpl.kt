package com.example.weatherapp.data.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.weatherapp.internal.NoConnectivityException
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class ConnectivityIntercepterImpl(context: Context) : ConnectivityIntercepter {

    private val appContext = context.applicationContext

    @RequiresApi(Build.VERSION_CODES.M)
    override fun intercept(chain: Interceptor.Chain): Response {
        if(!isOnline())
            throw NoConnectivityException()
        return chain.proceed(chain.request())
    }


    @RequiresApi(Build.VERSION_CODES.M)
    private fun isOnline(): Boolean{
        val connectivityManager = appContext.getSystemService(Context.CONNECTIVITY_SERVICE)
        as ConnectivityManager
//        val networkInfo = connectivityManager.activeNetwork
//        return networkInfo != null && networkInfo.

        val networkCapabilities = connectivityManager.activeNetwork ?: return false
        val actNw =
            connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
        return when {
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }

    }
}