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
        val temp = hour.tempC.toString() + "°C"
        with(binding) {
            Glide.with(root).load("https:" + hour.condition.icon).into(imageHour)
            textHourTemp.text = temp
            textLocalTime.text = hour.localTime
            textWillItRain.text =
                if (hour.willItRain) {
                    root.context.getString(R.string.yes)
                } else {
                    root.context.getString(R.string.no)
                }
        }
    }
}