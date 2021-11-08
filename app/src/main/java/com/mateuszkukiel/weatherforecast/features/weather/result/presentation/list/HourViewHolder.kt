package com.mateuszkukiel.weatherforecast.features.weather.result.presentation.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mateuszkukiel.weatherforecast.R
import com.mateuszkukiel.weatherforecast.databinding.ItemHourBinding
import com.mateuszkukiel.weatherforecast.features.weather.domain.model.Hour

class HourViewHolder(private val binding: ItemHourBinding) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun inflate(parent: ViewGroup): HourViewHolder {
            return HourViewHolder(
                ItemHourBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        }
    }

    fun bind(hour: Hour) {
        Glide.with(binding.root).load("https:" + hour.condition.icon).into(binding.imageHour)
        val temp = hour.tempC.toString() + "°C"
        binding.textHourTemp.text = temp
        binding.textLocalTime.text = hour.localTime
        binding.textWillItRain.text =
            if (hour.willItRain) binding.root.context.getString(R.string.yes) else binding.root.context.getString(
                R.string.no
            )
    }
}