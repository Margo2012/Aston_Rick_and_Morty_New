package com.example.aston_rick_and_morty.data.repository.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.aston_rick_and_morty.api.ApiService
import com.example.aston_rick_and_morty.models.remote_dto.Episode


private const val RICK_AND_MORTY_STARTING_INDEX = 1

class EpisodePagingSource(
    private val apiService: ApiService
): PagingSource<Int, Episode>() {
    override fun getRefreshKey(state: PagingState<Int, Episode>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Episode> {
        return try {
            val currentPage = params.key?:RICK_AND_MORTY_STARTING_INDEX
            val response = apiService.getAllEpisodes(currentPage)
            val data = response.body()?.results?:emptyList()
            val responseData = mutableListOf<Episode>()
            responseData.addAll(data)

            LoadResult.Page(
                data = responseData,
                prevKey = if (currentPage == 1) null else -1,
                nextKey = currentPage.plus(1)
            )

        }catch (e: Exception){
            LoadResult.Error(e)
        }

    }

}