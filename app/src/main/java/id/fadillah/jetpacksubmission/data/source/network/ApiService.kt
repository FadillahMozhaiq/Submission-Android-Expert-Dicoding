package id.fadillah.jetpacksubmission.data.source.network

import id.fadillah.jetpacksubmission.data.source.network.response.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    /*
     * Home Fragment
     */
    @GET("movie/upcoming")
    fun getUpcoming(
        @Query("api_key") apiKey: String
    ): Call<UpcomingMovieResponse>

    @GET("movie/now_playing")
    fun getNowPlaying(
        @Query("api_key") apiKey: String
    ): Call<NowPlayingResponse>

    @GET("movie/popular")
    fun getPopular(
        @Query("api_key") apiKey: String
    ): Call<PopularMovieResponse>

    @GET("movie/top_rated")
    fun getTopRated(
        @Query("api_key") apiKey: String
    ): Call<TopRatedMovieResponse>

    /*
     * Explore Fragment
     */
    @GET("search/movie")
    fun getMovieExplore(
        @Query("api_key") apiKey: String,
        @Query("query") query: String,
        @Query("include_adult") includeAdult: String
    ): Call<MovieExploreResponse>

    @GET("search/tv")
    fun getTvExplore(
        @Query("api_key") apiKey: String,
        @Query("query") query: String,
        @Query("include_adult") includeAdult: String
    ): Call<TvExploreResponse>

    @GET("search/person")
    fun getPersonExplore(
        @Query("api_key") apiKey: String,
        @Query("query") query: String,
        @Query("include_adult") includeAdult: String
    ): Call<PersonExploreResponse>

    @GET("search/company")
    fun getCompanyExplore(
        @Query("api_key") apiKey: String,
        @Query("query") query: String,
        @Query("include_adult") includeAdult: String
    ): Call<CompanyExploreResponse>

    @GET("search/multi")
    fun getMultiExplore(
        @Query("api_key") apiKey: String,
        @Query("query") query: String,
        @Query("include_adult") includeAdult: String
    ): Call<MultiSearchResponse>

    /*
    * Movie Fragment
     */
    @GET("trending/movie/week")
    fun getTrendingMovie(
        @Query("api_key") apiKey: String
    ): Call<MovieTrendingResponse>

    /*
    * TV Fragment
     */
    @GET("trending/tv/week")
    fun getTrendingTv(
        @Query("api_key") apiKey: String
    ): Call<TvTrendingResponse>

    /*
    * Detail Activity
     */
    @GET("movie/{id}")
    fun getDetailMovie(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String
    ): Call<DetailMovieResponse>

    @GET("tv/{id}")
    fun getDetailTv(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String
    ): Call<DetailTvResponse>
}