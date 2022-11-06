package com.example.aston_rick_and_morty.api.responce

import com.example.aston_rick_and_morty.models.remote_dto.Episode


data class ResponseEpisodeApi(
    val results: List<Episode>
)
