package com.example.aston_rick_and_morty.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.aston_rick_and_morty.R
import com.example.aston_rick_and_morty.databinding.FragmentLocationsBinding
import com.example.aston_rick_and_morty.viewmodels.LocationViewModel
import com.example.aston_rick_and_morty.views.adapter.LocationPagedAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

private const val LOCATION_BACKSTACK_TAG = "location backstack tag"

@AndroidEntryPoint
class LocationFragment : Fragment() {
    private var _binding: FragmentLocationsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: LocationViewModel by viewModels()
    private lateinit var characterAdapter: LocationPagedAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLocationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        linkWithRecycleView()
        loadData()

    }

    private fun loadData() {
        lifecycleScope.launch {
            viewModel.listData.collect {
                characterAdapter.submitData(it)
            }
        }
    }

    private fun linkWithRecycleView() {
        characterAdapter = LocationPagedAdapter(){location ->
            activity?.supportFragmentManager?.beginTransaction()?.apply {
                val fragment = LocationDetailsFragment.newInstance(location)
                replace(R.id.fragment_container, fragment)
                addToBackStack(LOCATION_BACKSTACK_TAG)
                commit()
            }

        }
        binding.recyclerLocationView.apply {
            layoutManager = StaggeredGridLayoutManager(
                2,
                StaggeredGridLayoutManager.VERTICAL
            )
            adapter = characterAdapter
            setHasFixedSize(true)
        }
    }
}