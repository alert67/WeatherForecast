package com.mateuszkukiel.weatherforecast.features.weather.search.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mateuszkukiel.core.base.viewBinding
import com.mateuszkukiel.weatherforecast.R
import com.mateuszkukiel.weatherforecast.databinding.FragmentSearchBinding
import com.mateuszkukiel.weatherforecast.features.weather.result.presentation.ResultFragment
import com.mateuszkukiel.weatherforecast.features.weather.search.presentation.model.SearchViewActions
import com.mateuszkukiel.weatherforecast.features.weather.search.presentation.model.SearchViewEvents
import com.mateuszkukiel.weatherforecast.features.weather.search.presentation.model.SearchViewState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search) {

    private val viewModel: SearchViewModel by viewModels()
    private val binding: FragmentSearchBinding by viewBinding(FragmentSearchBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.editTextSearch.doAfterTextChanged { editable ->
            viewModel.onAction(SearchViewActions.OnSetQuery(editable.toString()))
        }
        binding.nextButton.setOnClickListener {
            viewModel.onAction(SearchViewActions.OnNextButtonClicked)
        }
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.viewState.observe(viewLifecycleOwner) { state ->
            bind(state)
        }
        viewModel.viewEvents.observe(viewLifecycleOwner) { event ->
            when (event) {
                is SearchViewEvents.GoToResultScreen -> goToResultFragment(event.query)
            }
        }
    }

    private fun bind(state: SearchViewState) {
        binding.nextButton.isEnabled = state.isNextButtonEnabled
        binding.textInputSearch.error = state.searchQueryError
        binding.progressBar.isVisible = state.isLoading
        binding.nextButton.text = if (state.isLoading) "" else getString(R.string.next)
    }

    private fun goToResultFragment(query: String) {
        findNavController().navigate(
            R.id.action_SearchFragment_to_ResultFragment,
            bundleOf(ResultFragment.BUNDLE_SEARCH_QUERY_KEY to query)
        )
    }
}