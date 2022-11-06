package com.example.aston_rick_and_morty.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.aston_rick_and_morty.data.db.dao.CharacterDao
import com.example.aston_rick_and_morty.data.db.dao.EpisodeDao
import com.example.aston_rick_and_morty.data.db.dao.LocationDao
import com.example.aston_rick_and_morty.models.remote_dto.*


@TypeConverters(LocationConverters::class, EpisodeConverters::class)
@Database(entities = [Character::class, Location::class, Episode::class], version = 2)
abstract class RickAndMortyDatabase: RoomDatabase() {

    abstract fun characterDao(): CharacterDao
    abstract fun episodeDao(): EpisodeDao
    abstract fun locationDao(): LocationDao

}