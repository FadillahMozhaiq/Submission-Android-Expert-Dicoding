package id.fadillah.jetpacksubmission.data.source.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_movie")
data class FavoriteMovieEntity(
    @PrimaryKey
    val idMovie: Int
)