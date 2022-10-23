package com.mateuszkukiel.weatherforecast.features.weather.result.presentation.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mateuszkukiel.core.ui.MarginItemDecoration
import com.mateuszkukiel.weatherforecast.R
import com.mateuszkukiel.weatherforecast.databinding.ItemDayWeatherBinding
import com.mateuszkukiel.weatherforecast.features.weather.domain.model.DayWeather

class DayWeatherViewHolder(private val binding: ItemDayWeatherBinding) :
    RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun inflate(parent: ViewGroup): DayWeatherViewHolder {
            return DayWeatherViewHolder(
                ItemDayWeatherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        }
    }

    init {
        binding.recyclerViewHours.adapter = HourWeatherListAdapter()
        binding.recyclerViewHours.addItemDecoration(
            MarginItemDecoration(
                binding.root.context.resources.getDimensionPixelSize(R.dimen.margin_very_low),
                LinearLayoutManager.HORIZONTAL
            )
        )
    }

    fun bind(dayWeather: DayWeather) {
        binding.textDayTemp.text = dayWeather.tempC.toString() + "°C"
        binding.textDayDate.text = dayWeather.localTime
        (binding.recyclerViewHours.adapter as? HourWeatherListAdapter)?.submitList(dayWeather.hoursWeather)
    }
}