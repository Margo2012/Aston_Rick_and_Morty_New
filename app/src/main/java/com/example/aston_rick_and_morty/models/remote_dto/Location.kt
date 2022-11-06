package com.example.aston_rick_and_morty.models.remote_dto

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "locations")
@Parcelize
data class Location(
    @PrimaryKey val id: Int,
    val name: String,
    val type: String,
    val url: String,
    val dimension: String,
    val created: String
): Parcelable