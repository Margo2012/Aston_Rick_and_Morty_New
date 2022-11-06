package com.example.aston_rick_and_morty.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.aston_rick_and_morty.api.ApiService
import com.example.aston_rick_and_morty.data.db.RickAndMortyDatabase
import com.example.aston_rick_and_morty.models.remote_dto.Character
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton


@ExperimentalPagingApi
@Singleton
class DefaultCharactersRepository @Inject constructor(
    private val db: RickAndMortyDatabase,
    private val api: ApiService
) : CharactersRepository {
    private val characterDao = db.characterDao()

    override fun getCharacters(): Flow<PagingData<Character>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                initialLoadSize = PAGE_SIZE // not required, may be deleted
            ),
            remoteMediator = CharacterRemoteMediator(db, api),
            pagingSourceFactory = { characterDao.getAllCharacters() }
        ).flow


    }

    private companion object {
        const val PAGE_SIZE = 30
    }
}
