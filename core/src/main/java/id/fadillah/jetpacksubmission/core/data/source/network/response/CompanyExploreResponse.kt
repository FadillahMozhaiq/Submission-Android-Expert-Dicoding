package id.fadillah.jetpacksubmission.core.data.source.network.response

import com.google.gson.annotations.SerializedName

data class CompanyExploreResponse(

    @field:SerializedName("page")
	val page: Int? = null,

    @field:SerializedName("total_pages")
	val totalPages: Int? = null,

    @field:SerializedName("results")
	val results: List<ResultsItem>,

    @field:SerializedName("total_results")
	val totalResults: Int? = null
)

data class ResultsItem(

	@field:SerializedName("logo_path")
	val logoPath: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("origin_country")
	val originCountry: String? = null
)
