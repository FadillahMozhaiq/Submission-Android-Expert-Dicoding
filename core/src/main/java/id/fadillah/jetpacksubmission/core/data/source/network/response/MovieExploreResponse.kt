package id.fadillah.jetpacksubmission.core.data.source.network.response

import com.google.gson.annotations.SerializedName

data class MovieExploreResponse(

	@field:SerializedName("results")
	val results: List<ResultsItemExplore>

)

data class ResultsItemExplore(

	@field:SerializedName("overview")
	val overview: String,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("poster_path")
	val posterPath: String? = null,

	@field:SerializedName("backdrop_path")
	val backdropPath: String? = null,

	@field:SerializedName("release_date")
	val releaseDate: String? = null,

	@field:SerializedName("vote_average")
	val voteAverage: Double,

	@field:SerializedName("id")
	val id: Int? = null
)
