package id.fadillah.jetpacksubmission.core.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieEntity(
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
    val mediaType: String? = null
) : Parcelable