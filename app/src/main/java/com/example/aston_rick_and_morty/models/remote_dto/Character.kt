package com.example.aston_rick_and_morty.models.remote_dto

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.parcelize.Parcelize

@Entity(tableName = "characters")
@Parcelize
data class Character(
    @PrimaryKey val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,

    @TypeConverters(LocationConverters::class)
    val origin: Location,
    @TypeConverters(LocationConverters::class)
    val location: Location,

    @TypeConverters(EpisodeConverters::class)
    val episodes: List<String>,

    val image: String,
    val url: String,
    val created: String
): Parcelable

class LocationConverters {
    companion object{
        @TypeConverter
        @JvmStatic
        fun fromLocations(value: Location): String {
            return value.url }

        @TypeConverter
        @JvmStatic
        fun stringToLocation(date: String): Location {
            return Location(id = 0, name = "", type = "", url = "", dimension = "", created = "")
        }
    }

}

class EpisodeConverters{
    companion object{
        @TypeConverter
        @JvmStatic
        fun fromListEpisode(value: List<String>): String{
            return value.first()
        }

        @TypeConverter
        @JvmStatic
        fun stringToStringEpisode(date: String?): List<String>?{
            return null
        }
    }

}

@TypeConverter
fun fromValuesToList(value: ArrayList<Location?>?): String? {
    if (value == null) {
        return null
    }
    val gson = Gson()
    val type = object : TypeToken<ArrayList<Location?>?>() {}.type
    return gson.toJson(value, type)
}

@TypeConverter
fun toOptionValuesList(value: String?): ArrayList<Location?>? {
    if (value == null) {
        return null
    }
    val gson = Gson()
    val type = object : TypeToken<List<Location?>?>() {}.type
    return gson.fromJson(value, type)
}




