package id.fadillah.jetpacksubmission.core.data.source.network

import id.fadillah.jetpacksubmission.core.data.source.network.response.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    /*
     * Home Fragment
     */
    @GET("movie/upcoming")
    suspend fun getUpcoming(
        @Query("api_key") apiKey: String
    ): UpcomingMovieResponse

    @GET("movie/now_playing")
    suspend fun getNowPlaying(
        @Query("api_key") apiKey: String
    ): NowPlayingResponse

    @GET("movie/popular")
    suspend fun getPopular(
        @Query("api_key") apiKey: String
    ): PopularMovieResponse

    @GET("movie/top_rated")
    suspend fun getTopRated(
        @Query("api_key") apiKey: String
    ): TopRatedMovieResponse

    /*
     * Explore Fragment
     */
    @GET("search/movie")
    suspend fun getMovieExplore(
        @Query("api_key") apiKey: String,
        @Query("query") query: String,
        @Query("include_adult") includeAdult: String = "true"
    ): MovieExploreResponse

    @GET("search/tv")
    suspend fun getTvExplore(
        @Query("api_key") apiKey: String,
        @Query("query") query: String,
        @Query("include_adult") includeAdult: String = "true"
    ): TvExploreResponse

    @GET("search/person")
    suspend fun getPersonExplore(
        @Query("api_key") apiKey: String,
        @Query("query") query: String,
        @Query("include_adult") includeAdult: String = "true"
    ): PersonExploreResponse

    @GET("search/company")
    suspend fun getCompanyExplore(
        @Query("api_key") apiKey: String,
        @Query("query") query: String,
        @Query("include_adult") includeAdult: String = "true"
    ): CompanyExploreResponse

    @GET("search/multi")
    suspend fun getMultiExplore(
        @Query("api_key") apiKey: String,
        @Query("query") query: String,
        @Query("include_adult") includeAdult: String = "true"
    ): MultiSearchResponse

    /*
    * Movie Fragment
     */
    @GET("trending/movie/week")
    suspend fun getTrendingMovie(
        @Query("api_key") apiKey: String
    ): MovieTrendingResponse

    /*
    * TV Fragment
     */
    @GET("trending/tv/week")
    suspend fun getTrendingTv(
        @Query("api_key") apiKey: String
    ): TvTrendingResponse

    /*
    * Detail Activity
     */
    @GET("movie/{id}")
    suspend fun getDetailMovie(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String
    ): DetailMovieResponse

    @GET("tv/{id}")
    suspend fun getDetailTv(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String
    ): DetailTvResponse
}