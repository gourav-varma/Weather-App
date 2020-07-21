package com.example.weatherapp.ui.weather.future.list


import com.example.weatherapp.R
import com.example.weatherapp.data.db.unitlocalized.future.MetricSimpleFutureWeatherEntry
import com.example.weatherapp.data.db.unitlocalized.future.UnitSpecificSimpleFutureWeatherEntry
import com.example.weatherapp.internal.glide.GlideApp
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_future_weather.*


class FutureWeatherItem(
    val weatherEntry: UnitSpecificSimpleFutureWeatherEntry
) : Item() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.apply {
            textView_condition.text = weatherEntry.conditionText
            updateDate()
            updateConditionImage()
            updateTemperature()
        }
    }

    override fun getLayout() = R.layout.item_future_weather

    private fun GroupieViewHolder.updateDate(){
        val dtFormatter = org.threeten.bp.format.DateTimeFormatter.ofLocalizedDate(org.threeten.bp.format.FormatStyle.MEDIUM)
        textView_date.text = weatherEntry.date.format(dtFormatter)
    }

    private fun GroupieViewHolder.updateTemperature(){
        val unitAbbrevation = if (weatherEntry is MetricSimpleFutureWeatherEntry) "°C"
        else "°F"
        textView_temperature.text = "${weatherEntry.avgTemperature}$unitAbbrevation"
    }

    private fun GroupieViewHolder.updateConditionImage(){
        GlideApp.with(this.containerView)
            .load("http:"+ weatherEntry.conditionIconUrl)
            .into(imageView_condition_icon)
    }
}