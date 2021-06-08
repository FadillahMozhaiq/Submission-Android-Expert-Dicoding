package id.fadillah.jetpacksubmission.data.source.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieDatabaseEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val posterPath: String,
    val overview: String,
    val backgroundPath: String? = null,
    val genres: List<String>? = null,
    val type: Int = 0,
    val status: String? = null,
    val tagLine: String? = null,
    val date: String? = null,
    val rating: Double? = null,
    val mediaType: String? = null,
    var saveFor: String? = null
)