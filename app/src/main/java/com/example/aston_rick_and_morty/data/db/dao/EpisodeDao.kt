package com.example.aston_rick_and_morty.data.db.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.aston_rick_and_morty.models.remote_dto.Episode

@Dao
interface EpisodeDao {
    @Query("SELECT * FROM episodes")
    fun getAllEpisodes(): PagingSource<Int, Episode>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEpisodes(episodes: List<Episode>)

    @Query("DELETE FROM episodes")
    suspend fun deleteAllEpisodes()
}