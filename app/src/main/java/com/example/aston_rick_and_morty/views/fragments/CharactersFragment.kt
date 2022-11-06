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
import com.example.aston_rick_and_morty.databinding.FragmentCharactersBinding
import com.example.aston_rick_and_morty.viewmodels.CharacterViewModel
import com.example.aston_rick_and_morty.views.adapter.CharacterPagedAdapter

import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

private const val FRAGMENT_DETAIL_TAG = "DETAIL_TAG"
private const val BACKSTACK_TAG = "backstack tag"

@AndroidEntryPoint
class CharactersFragment: Fragment() {

    private var _binding: FragmentCharactersBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CharacterViewModel by viewModels()
    private lateinit var characterAdapter: CharacterPagedAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        _binding = FragmentCharactersBinding.inflate(inflater, container, false)
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
                if (it != null) {
                    characterAdapter.submitData(it)
                }
            }
        }
    }

    private fun linkWithRecycleView() {
        characterAdapter = CharacterPagedAdapter(){character ->
            activity?.supportFragmentManager?.beginTransaction()?.apply {
                val fragment = CharacterDetailsFragment.newInstance(character)
                replace(R.id.fragment_container, fragment, FRAGMENT_DETAIL_TAG)
                addToBackStack(BACKSTACK_TAG)
                commit()
            }
        }
        binding.recyclerCharacterView.apply {
            layoutManager = StaggeredGridLayoutManager(
                2,
                StaggeredGridLayoutManager.VERTICAL
            )
            adapter = characterAdapter
            setHasFixedSize(true)
        }
    }
}