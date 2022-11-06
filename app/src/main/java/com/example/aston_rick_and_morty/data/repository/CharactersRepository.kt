package com.example.aston_rick_and_morty.data.repository

import androidx.paging.PagingData
import com.example.aston_rick_and_morty.models.remote_dto.Character

import kotlinx.coroutines.flow.Flow

interface CharactersRepository {

    fun getCharacters(): Flow<PagingData<Character>>

}