package com.example.aston_rick_and_morty.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import com.example.aston_rick_and_morty.data.repository.DefaultCharactersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel()
@OptIn(ExperimentalPagingApi::class)
class CharacterViewModel
@Inject constructor(repository: DefaultCharactersRepository):ViewModel(){

    @OptIn(ExperimentalPagingApi::class)
    val listData = repository.getCharacters().stateIn(
        viewModelScope, SharingStarted.Lazily, null
    )
}