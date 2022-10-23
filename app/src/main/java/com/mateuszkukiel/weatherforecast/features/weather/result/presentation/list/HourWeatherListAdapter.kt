package com.mateuszkukiel.weatherforecast.features.weather.result.presentation.list

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.mateuszkukiel.weatherforecast.features.weather.domain.model.HourWeather

class HourWeatherListAdapter : ListAdapter<HourWeather, HourWeatherViewHolder>(HourDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourWeatherViewHolder {
        return HourWeatherViewHolder.inflate(parent)
    }

    override fun onBindViewHolder(holder: HourWeatherViewHolder, position: Int) {
        getItem(position)?.let { hour ->
            holder.bind(hour)
        }
    }

    class HourDiffUtil : DiffUtil.ItemCallback<HourWeather>() {

        override fun areItemsTheSame(oldItem: HourWeather, newItem: HourWeather): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun areContentsTheSame(oldItem: HourWeather, newItem: HourWeather): Boolean {
            return oldItem == newItem
        }
    }
}