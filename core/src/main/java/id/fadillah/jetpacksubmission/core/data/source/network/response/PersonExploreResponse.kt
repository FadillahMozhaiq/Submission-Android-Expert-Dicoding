package id.fadillah.jetpacksubmission.core.data.source.network.response

import com.google.gson.annotations.SerializedName

data class PersonExploreResponse(

    @field:SerializedName("page")
	val page: Int? = null,

    @field:SerializedName("total_pages")
	val totalPages: Int? = null,

    @field:SerializedName("results")
	val results: List<ResultsPersonExploreItem>,

    @field:SerializedName("total_results")
	val totalResults: Int? = null
)

data class ResultsPersonExploreItem(

	@field:SerializedName("gender")
	val gender: Int? = null,

	@field:SerializedName("known_for_department")
	val knownForDepartment: String? = null,

	@field:SerializedName("popularity")
	val popularity: Double? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("profile_path")
	val profilePath: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("adult")
	val adult: Boolean? = null
)
