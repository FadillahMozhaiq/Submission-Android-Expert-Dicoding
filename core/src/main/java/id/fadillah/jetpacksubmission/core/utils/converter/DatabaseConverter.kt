package id.fadillah.jetpacksubmission.core.utils.converter

import androidx.room.TypeConverter

class DatabaseConverter {
    @TypeConverter
    fun genreToString(genre: List<String>?): String? =
        genre?.joinToString(", ") { it }

    @TypeConverter
    fun stringToGenre(genre: String?): List<String>? =
        genre?.split(", ")
}