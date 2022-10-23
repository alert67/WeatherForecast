package com.mateuszkukiel.weatherforecast.features.weather.result.presentation.list

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.mateuszkukiel.weatherforecast.features.weather.domain.model.DayWeather

class DayWeatherListAdapter : ListAdapter<DayWeather, DayWeatherViewHolder>(DayWeatherDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayWeatherViewHolder {
        return DayWeatherViewHolder.inflate(parent)
    }

    override fun onBindViewHolder(holder: DayWeatherViewHolder, position: Int) {
        getItem(position)?.let { day ->
            holder.bind(day)
        }
    }

    class DayWeatherDiffUtil : DiffUtil.ItemCallback<DayWeather>() {

        override fun areItemsTheSame(oldItem: DayWeather, newItem: DayWeather): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun areContentsTheSame(oldItem: DayWeather, newItem: DayWeather): Boolean {
            return oldItem == newItem
        }
    }
}