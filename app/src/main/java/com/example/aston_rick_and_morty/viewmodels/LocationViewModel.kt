package com.example.aston_rick_and_morty.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.aston_rick_and_morty.api.ApiService
import com.example.aston_rick_and_morty.data.repository.paging.LocationPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel()
class LocationViewModel
@Inject constructor(private val apiService: ApiService): ViewModel()
{
    val listData = Pager(PagingConfig(pageSize = 1)){
        LocationPagingSource(apiService)
    }.flow.cachedIn(viewModelScope)
}