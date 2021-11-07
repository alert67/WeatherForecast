package com.mateuszkukiel.weatherforecast.features.weather.result.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mateuszkukiel.core.base.viewBinding
import com.mateuszkukiel.weatherforecast.R
import com.mateuszkukiel.weatherforecast.databinding.FragmentResultBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResultFragment : Fragment(R.layout.fragment_result) {

    companion object {
        const val BUNDLE_SEARCH_QUERY_KEY = "bundleSearchQueryKey"
    }

    private val viewModel: ResultViewModel by viewModels()

    private val binding: FragmentResultBinding by viewBinding(FragmentResultBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.query.observe(viewLifecycleOwner) {
            binding.textviewSecond.text = it
        }
    }

}