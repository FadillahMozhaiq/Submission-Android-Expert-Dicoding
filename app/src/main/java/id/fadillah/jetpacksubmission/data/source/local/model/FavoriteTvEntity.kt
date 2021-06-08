package id.fadillah.jetpacksubmission.data.source.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_tv")
data class FavoriteTvEntity(
    @PrimaryKey
    val idTvShow: Int
)