package com.mateuszkukiel.weatherforecast.features.weather.result.presentation.list

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.mateuszkukiel.weatherforecast.features.weather.domain.model.Hour

class HourListAdapter : ListAdapter<Hour, HourViewHolder>(HourDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourViewHolder {
        return HourViewHolder.inflate(parent)
    }

    override fun onBindViewHolder(holder: HourViewHolder, position: Int) {
        getItem(position)?.let { hour ->
            holder.bind(hour)
        }
    }

    class HourDiffUtil : DiffUtil.ItemCallback<Hour>() {

        override fun areItemsTheSame(oldItem: Hour, newItem: Hour): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun areContentsTheSame(oldItem: Hour, newItem: Hour): Boolean {
            return oldItem == newItem
        }
    }
}