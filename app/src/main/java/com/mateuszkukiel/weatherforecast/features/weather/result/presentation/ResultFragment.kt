package com.mateuszkukiel.weatherforecast.features.weather.result.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.mateuszkukiel.core.base.viewBinding
import com.mateuszkukiel.weatherforecast.R
import com.mateuszkukiel.weatherforecast.databinding.FragmentResultBinding
import com.mateuszkukiel.weatherforecast.features.weather.domain.model.WeatherQuery
import com.mateuszkukiel.weatherforecast.features.weather.result.presentation.list.DayWeatherListAdapter
import com.mateuszkukiel.weatherforecast.features.weather.result.presentation.model.ResultViewAction
import com.mateuszkukiel.weatherforecast.features.weather.result.presentation.model.ResultViewEvents
import com.mateuszkukiel.weatherforecast.features.weather.result.presentation.model.ResultViewState
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
            viewModel.onAction(ResultViewAction.OnPullToRefresh)
        }
        binding.recyclerViewResultHours.adapter = daysAdapter

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.viewState.observe(viewLifecycleOwner, ::bind)
        viewModel.viewEvents.observe(viewLifecycleOwner, ::onViewEvent)
    }

    private fun bind(state: ResultViewState) {
        with(binding) {
            swipeToRefresh.isRefreshing = state.isLoading

            state.errorMessage?.let { textError.text = it }

            if (state.weatherQuery != null) {
                textError.visibility = View.GONE
                content.visibility = View.VISIBLE

                textResultLocationCountry.text = state.weatherQuery.country
                textResultLocationName.text = state.weatherQuery.name
                textResultLocationLatitude.text = state.weatherQuery.lat.toString()
                textResultLocationLongitude.text = state.weatherQuery.lon.toString()

                daysAdapter.submitList(state.weatherQuery.daysWeather)
            } else {
                textError.visibility = View.VISIBLE
                content.visibility = View.GONE
            }

        }
    }

    private fun onViewEvent(event: ResultViewEvents) {
        when (event) {
            is ResultViewEvents.ShowError -> showSnackbar(event.message)
        }
    }

    private fun showSnackbar(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
    }

}