package com.example.aston_rick_and_morty.data.repository


import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.aston_rick_and_morty.api.ApiService
import com.example.aston_rick_and_morty.data.db.RickAndMortyDatabase
import com.example.aston_rick_and_morty.models.remote_dto.Character


@OptIn(ExperimentalPagingApi::class)
class CharacterRemoteMediator(
    private val db: RickAndMortyDatabase,
    private val apiService: ApiService
) : RemoteMediator<Int, Character>() {
    private val characterDao = db.characterDao()

    private var pageIndex = 0
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Character>
    ): MediatorResult {

        pageIndex = getPageIndex(loadType) ?:
                return MediatorResult.Success(endOfPaginationReached = true)

        val limit = state.config.pageSize

        return try {
            val response = pageIndex.let { apiService.getAllCharacters(it) }
            val characters = response.body()?.results ?: emptyList()

            if (loadType == LoadType.REFRESH) {
                characterDao.deleteAllCharacters()
                characterDao.insertCharacters(characters)
            } else {
                characterDao.getAllCharacters()
            }
            MediatorResult.Success(
                endOfPaginationReached = characters.size < limit
            )
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }

    private fun getPageIndex(loadType: LoadType): Int? {
        pageIndex = when(loadType){
            LoadType.REFRESH -> 0
            LoadType.PREPEND -> return null
            LoadType.APPEND -> ++pageIndex
        }
        return pageIndex
    }

}
