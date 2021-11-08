package com.mateuszkukiel.weatherforecast.features.weather.search.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mateuszkukiel.core.base.viewBinding
import com.mateuszkukiel.weatherforecast.R
import com.mateuszkukiel.weatherforecast.databinding.FragmentSearchBinding
import com.mateuszkukiel.weatherforecast.features.weather.result.presentation.ResultFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search) {

    private val viewModel: SearchViewModel by viewModels()

    private val binding: FragmentSearchBinding by viewBinding(FragmentSearchBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchField.editText?.doAfterTextChanged { editable ->
            viewModel.validateInput(editable.toString())
        }

        binding.nextButton.setOnClickListener {
            goToResultFragment()
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.isQueryValid.observe(viewLifecycleOwner) { isValid ->
            binding.nextButton.isEnabled = isValid
        }
    }

    private fun goToResultFragment() {
        val searchQuery = binding.searchField.editText?.text.toString()
        findNavController().navigate(
            R.id.action_SearchFragment_to_ResultFragment,
            bundleOf(ResultFragment.BUNDLE_SEARCH_QUERY_KEY to searchQuery)
        )
    }
}