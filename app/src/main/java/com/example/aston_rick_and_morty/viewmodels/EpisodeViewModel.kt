package com.example.aston_rick_and_morty.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.aston_rick_and_morty.api.ApiService
import com.example.aston_rick_and_morty.data.repository.paging.EpisodePagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel()
class EpisodeViewModel
@Inject constructor(private val apiService: ApiService): ViewModel(){
    val listData = Pager(PagingConfig(pageSize = 1)){
        EpisodePagingSource(apiService)
    }.flow.cachedIn(viewModelScope)
}