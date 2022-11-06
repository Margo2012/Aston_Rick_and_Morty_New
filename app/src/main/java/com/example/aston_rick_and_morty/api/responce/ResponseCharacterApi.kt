package com.example.aston_rick_and_morty.api.responce
import com.example.aston_rick_and_morty.models.remote_dto.Character

data class ResponseCharacterApi(
    val results: List<Character>
)
