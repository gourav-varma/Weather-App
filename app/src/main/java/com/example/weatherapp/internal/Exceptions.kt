package com.example.weatherapp.internal

import java.io.IOException
import java.lang.Exception

class NoConnectivityException: IOException()

class LocationPremissionNotGrantedException: Exception()

class DateNotFoundException: Exception()