package com.example.aston_rick_and_morty.data.db.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.aston_rick_and_morty.models.remote_dto.Location

@Dao
interface LocationDao {
    @Query("SELECT * FROM locations")
    fun getAllLocations(): PagingSource<Int, Location>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLocations(locations: List<Location>)

    @Query("DELETE FROM locations")
    suspend fun deleteAllLocations()
}