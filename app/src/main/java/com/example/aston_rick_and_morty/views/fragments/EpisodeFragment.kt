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
import com.example.aston_rick_and_morty.databinding.FragmentEpisodesBinding
import com.example.aston_rick_and_morty.viewmodels.EpisodeViewModel
import com.example.aston_rick_and_morty.views.adapter.EpisodePagedAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

private const val EPISODE_BACKSTACK_TAG = "episode backstack tag"

@AndroidEntryPoint
class EpisodeFragment : Fragment() {
    private var _binding: FragmentEpisodesBinding? = null
    private val binding get() = _binding!!
    private lateinit var episodeAdapter: EpisodePagedAdapter
    private val viewModel: EpisodeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEpisodesBinding.inflate(inflater, container, false)
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
                episodeAdapter.submitData(it)
            }
        }
    }

    private fun linkWithRecycleView() {
        episodeAdapter = EpisodePagedAdapter(){episode ->
            activity?.supportFragmentManager?.beginTransaction()?.apply {
                val fragment = EpisodeDetailsFragment.newInstance(episode)
                replace(R.id.fragment_container, fragment)
                addToBackStack(EPISODE_BACKSTACK_TAG)
                commit()
            }
        }
        binding.recyclerEpisodeView.apply {
            layoutManager = StaggeredGridLayoutManager(
                2,
                StaggeredGridLayoutManager.VERTICAL
            )
            adapter = episodeAdapter
            setHasFixedSize(true)
        }

    }

}