package com.mateuszkukiel.weatherforecast.features.weather.result.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.mateuszkukiel.core.base.UiState
import com.mateuszkukiel.core.base.viewBinding
import com.mateuszkukiel.weatherforecast.R
import com.mateuszkukiel.weatherforecast.databinding.FragmentResultBinding
import com.mateuszkukiel.weatherforecast.features.weather.result.presentation.list.DayWeatherListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResultFragment : Fragment(R.layout.fragment_result) {

    companion object {
        const val BUNDLE_SEARCH_QUERY_KEY = "bundleSearchQueryKey"
    }

    private val viewModel: ResultViewModel by viewModels()

    private val binding: FragmentResultBinding by viewBinding(FragmentResultBinding::bind)

    private val daysAdapter: DayWeatherListAdapter by lazy {
        DayWeatherListAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.swipeToRefresh.setOnRefreshListener {
            viewModel.refreshData()
        }
        binding.recyclerViewResultHours.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewResultHours.adapter = daysAdapter

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.locationQuery.observe(viewLifecycleOwner) { weather ->

            with(binding) {
                textError.visibility = View.GONE
                content.visibility = View.VISIBLE

                textResultLocationCountry.text = weather.country
                textResultLocationName.text = weather.name
                textResultLocationLatitude.text = weather.lat.toString()
                textResultLocationLongitude.text = weather.lon.toString()
            }

            daysAdapter.submitList(weather.daysWeather)
        }
        viewModel.errorMessage.observe(viewLifecycleOwner) { errorMessage ->
            binding.textError.text = errorMessage
            showSnackbar(errorMessage)
        }
        viewModel.uiState.observe(viewLifecycleOwner) { uiState ->
            binding.swipeToRefresh.isRefreshing = uiState is UiState.Pending
        }
    }

    private fun showSnackbar(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
    }

}