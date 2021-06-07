package id.fadillah.jetpacksubmission.data.source.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import id.fadillah.jetpacksubmission.BuildConfig.API_KEY
import id.fadillah.jetpacksubmission.data.source.network.response.*
import id.fadillah.jetpacksubmission.utils.helper.EspressoIdlingResource.decrement
import id.fadillah.jetpacksubmission.utils.helper.EspressoIdlingResource.increment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource(
    private val apiService: ApiService
) {

    companion object {
        private const val TAG = "RemoteDataSource"
        private const val messageEmpty: String = "Null Data!"
    }

    /*
     * Home Fragment
     */
    fun getUpcoming(): LiveData<ApiResponse<List<ResultsUpcomingItem>>> {
        val listUpcoming = MutableLiveData<ApiResponse<List<ResultsUpcomingItem>>>()

        increment()
        apiService.getUpcoming(API_KEY).enqueue(object :
            Callback<UpcomingMovieResponse> {
            override fun onResponse(
                call: Call<UpcomingMovieResponse>,
                response: Response<UpcomingMovieResponse>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()?.resultsUpcoming
                    if (data != null) {
                        listUpcoming.value =
                            ApiResponse.success(data)
                    } else {
                        listUpcoming.value =
                            ApiResponse.empty(messageEmpty, emptyList())
                    }
                    decrement()
                } else {
                    Log.e(TAG, "onResponse: ${response.message()}")
                    listUpcoming.value =
                        ApiResponse.error(response.message(), emptyList())
                }
            }

            override fun onFailure(call: Call<UpcomingMovieResponse>, t: Throwable) {
                t.printStackTrace()
                listUpcoming.value = ApiResponse.error(t.message.toString(), emptyList())
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
        return listUpcoming
    }

    fun getNowPlaying(): LiveData<ApiResponse<List<ResultsNowPlayingItem>>> {
        val listNowPlaying = MutableLiveData<ApiResponse<List<ResultsNowPlayingItem>>>()

        increment()
        apiService.getNowPlaying(API_KEY).enqueue(object : Callback<NowPlayingResponse> {
            override fun onResponse(
                call: Call<NowPlayingResponse>,
                response: Response<NowPlayingResponse>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()?.results
                    if (data != null) {
                        listNowPlaying.value =
                            ApiResponse.success(data)
                    } else {
                        listNowPlaying.value =
                            ApiResponse.empty(messageEmpty, emptyList())
                    }
                    decrement()
                } else {
                    Log.e(TAG, "onResponse: ${response.message()}")
                    listNowPlaying.value =
                        ApiResponse.error(response.message(), emptyList())
                }
            }

            override fun onFailure(call: Call<NowPlayingResponse>, t: Throwable) {
                t.printStackTrace()
                listNowPlaying.value = ApiResponse.error(t.message.toString(), emptyList())
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })

        return listNowPlaying
    }

    fun getPopular(): LiveData<ApiResponse<List<ResultsPopularMovieItem>>> {
        val listPopular = MutableLiveData<ApiResponse<List<ResultsPopularMovieItem>>>()

        increment()
        apiService.getPopular(API_KEY).enqueue(object : Callback<PopularMovieResponse> {
            override fun onResponse(
                call: Call<PopularMovieResponse>,
                response: Response<PopularMovieResponse>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()?.results
                    if (data != null) {
                        listPopular.value =
                            ApiResponse.success(data)
                    } else {
                        listPopular.value =
                            ApiResponse.empty(messageEmpty, emptyList())
                    }
                    decrement()
                } else {
                    Log.e(TAG, "onResponse: ${response.message()}")
                    listPopular.value =
                        ApiResponse.error(response.message(), emptyList())
                }
            }

            override fun onFailure(call: Call<PopularMovieResponse>, t: Throwable) {
                t.printStackTrace()
                listPopular.value = ApiResponse.error(t.message.toString(), emptyList())
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })

        return listPopular
    }

    fun getTopRated(): LiveData<ApiResponse<List<ResultsTopRatedMovieItem>>> {
        val listTopRated = MutableLiveData<ApiResponse<List<ResultsTopRatedMovieItem>>>()

        increment()
        apiService.getTopRated(API_KEY).enqueue(object : Callback<TopRatedMovieResponse> {
            override fun onResponse(
                call: Call<TopRatedMovieResponse>,
                response: Response<TopRatedMovieResponse>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()?.results
                    if (data != null) {
                        listTopRated.value =
                            ApiResponse.success(data)
                    } else {
                        listTopRated.value =
                            ApiResponse.empty(messageEmpty, emptyList())
                    }
                    decrement()
                } else {
                    Log.e(TAG, "onResponse: ${response.message()}")
                    listTopRated.value =
                        ApiResponse.error(response.message(), emptyList())
                }
            }

            override fun onFailure(call: Call<TopRatedMovieResponse>, t: Throwable) {
                t.printStackTrace()
                Log.e(TAG, "onFailure: ${t.message}")
                listTopRated.value = ApiResponse.error(t.message.toString(), emptyList())
            }
        })

        return listTopRated
    }

    /*
     * Explore Fragment
     */
    fun getMovieExplore(query: String): LiveData<ApiResponse<List<ResultsItemExplore>>> {
        val listMovie = MutableLiveData<ApiResponse<List<ResultsItemExplore>>>()

        increment()
        apiService.getMovieExplore(API_KEY, query, "true")
            .enqueue(object : Callback<MovieExploreResponse> {
                override fun onResponse(
                    call: Call<MovieExploreResponse>,
                    response: Response<MovieExploreResponse>
                ) {
                    if (response.isSuccessful) {
                        val data = response.body()?.results
                        if (data != null) {
                            listMovie.value =
                                ApiResponse.success(data)
                        } else {
                            listMovie.value =
                                ApiResponse.empty(messageEmpty, emptyList())
                        }
                        decrement()
                    } else {
                        Log.e(TAG, "onResponse: ${response.message()}")
                        listMovie.value =
                            ApiResponse.error(response.message(), emptyList())
                    }
                }

                override fun onFailure(call: Call<MovieExploreResponse>, t: Throwable) {
                    t.printStackTrace()
                    Log.e(TAG, "onFailure: ${t.message}")
                    listMovie.value = ApiResponse.error(t.message.toString(), emptyList())
                }
            })

        return listMovie
    }

    fun getTvExplore(query: String): LiveData<ApiResponse<List<ResultsTvExploreItem>>> {
        val listTv = MutableLiveData<ApiResponse<List<ResultsTvExploreItem>>>()

        increment()
        apiService.getTvExplore(API_KEY, query, "true")
            .enqueue(object : Callback<TvExploreResponse> {
                override fun onResponse(
                    call: Call<TvExploreResponse>,
                    response: Response<TvExploreResponse>
                ) {
                    if (response.isSuccessful) {
                        val data = response.body()?.resultsTvExplore
                        if (data != null) {
                            listTv.value =
                                ApiResponse.success(data)
                        } else {
                            listTv.value =
                                ApiResponse.empty(messageEmpty, emptyList())
                        }
                        decrement()
                    } else {
                        Log.e(TAG, "onResponse: ${response.message()}")
                        listTv.value =
                            ApiResponse.error(response.message(), emptyList())
                    }
                }

                override fun onFailure(call: Call<TvExploreResponse>, t: Throwable) {
                    t.printStackTrace()
                    Log.e(TAG, "onFailure: ${t.message}")
                    listTv.value = ApiResponse.error(t.message.toString(), emptyList())
                }
            })

        return listTv
    }

    fun getPersonExplore(query: String): LiveData<ApiResponse<List<ResultsPersonExploreItem>>> {
        val listPerson = MutableLiveData<ApiResponse<List<ResultsPersonExploreItem>>>()

        increment()
        apiService.getPersonExplore(API_KEY, query, "true")
            .enqueue(object : Callback<PersonExploreResponse> {
                override fun onResponse(
                    call: Call<PersonExploreResponse>,
                    response: Response<PersonExploreResponse>
                ) {
                    if (response.isSuccessful) {
                        val data = response.body()?.results
                        if (data != null) {
                            listPerson.value =
                                ApiResponse.success(data)
                        } else {
                            listPerson.value =
                                ApiResponse.empty(messageEmpty, emptyList())
                        }
                        decrement()
                    } else {
                        Log.e(TAG, "onResponse: ${response.message()}")
                        listPerson.value =
                            ApiResponse.error(response.message(), emptyList())
                    }
                }

                override fun onFailure(call: Call<PersonExploreResponse>, t: Throwable) {
                    t.printStackTrace()
                    Log.e(TAG, "onFailure: ${t.message}")
                    listPerson.value = ApiResponse.error(t.message.toString(), emptyList())
                }
            })

        return listPerson
    }

    fun getCompanyExplore(query: String): LiveData<ApiResponse<List<ResultsItem>>> {
        val listCompany = MutableLiveData<ApiResponse<List<ResultsItem>>>()

        increment()
        apiService.getCompanyExplore(API_KEY, query, "true").enqueue(object :
            Callback<CompanyExploreResponse> {
            override fun onResponse(
                call: Call<CompanyExploreResponse>,
                response: Response<CompanyExploreResponse>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()?.results
                    if (data != null) {
                        listCompany.value =
                            ApiResponse.success(data)
                    } else {
                        listCompany.value =
                            ApiResponse.empty(messageEmpty, emptyList())
                    }
                    decrement()
                } else {
                    Log.e(TAG, "onResponse: ${response.message()}")
                    listCompany.value =
                        ApiResponse.error(response.message(), emptyList())
                }
            }

            override fun onFailure(call: Call<CompanyExploreResponse>, t: Throwable) {
                t.printStackTrace()
                Log.e(TAG, "onFailure: ${t.message}")
                listCompany.value = ApiResponse.error(t.message.toString(), emptyList())
            }
        })

        return listCompany
    }

    fun getMultiSearchExplore(query: String): LiveData<ApiResponse<List<ResultsMultiSearchItem>>> {
        val listExplore = MutableLiveData<ApiResponse<List<ResultsMultiSearchItem>>>()

        increment()
        apiService.getMultiExplore(API_KEY, query, "true")
            .enqueue(object : Callback<MultiSearchResponse> {
                override fun onResponse(
                    call: Call<MultiSearchResponse>,
                    response: Response<MultiSearchResponse>
                ) {
                    if (response.isSuccessful) {
                        val data = response.body()?.results
                        if (data != null) {
                            listExplore.value =
                                ApiResponse.success(data)
                        } else {
                            listExplore.value =
                                ApiResponse.empty(messageEmpty, emptyList())
                        }
                        decrement()
                    } else {
                        Log.e(TAG, "onResponse: ${response.message()}")
                        listExplore.value =
                            ApiResponse.error(response.message(), emptyList())
                    }
                }

                override fun onFailure(call: Call<MultiSearchResponse>, t: Throwable) {
                    t.printStackTrace()
                    Log.e(TAG, "onFailure: ${t.message}")
                    listExplore.value = ApiResponse.error(t.message.toString(), emptyList())
                }
            })

        return listExplore
    }

    /*
    * Movie Fragment
     */
    fun getMovieTrending(): LiveData<ApiResponse<List<ResultsMovieTrendingItem>>> {
        val listMovie = MutableLiveData<ApiResponse<List<ResultsMovieTrendingItem>>>()

        increment()
        apiService.getTrendingMovie(API_KEY).enqueue(object : Callback<MovieTrendingResponse> {
            override fun onResponse(
                call: Call<MovieTrendingResponse>,
                response: Response<MovieTrendingResponse>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()?.results
                    if (data != null) {
                        listMovie.value =
                            ApiResponse.success(data)
                    } else {
                        listMovie.value =
                            ApiResponse.empty(messageEmpty, emptyList())
                    }
                    decrement()
                } else {
                    Log.e(TAG, "onResponse: ${response.message()}")
                    listMovie.value =
                        ApiResponse.error(response.message(), emptyList())
                }
            }

            override fun onFailure(call: Call<MovieTrendingResponse>, t: Throwable) {
                t.printStackTrace()
                Log.e(TAG, "onFailure: ${t.message}")
                listMovie.value = ApiResponse.error(t.message.toString(), emptyList())
            }
        })

        return listMovie
    }

    /*
    * TV Fragment
     */
    fun getTvTrending(): LiveData<ApiResponse<List<ResultsTvTrendingItem>>> {
        val listTv = MutableLiveData<ApiResponse<List<ResultsTvTrendingItem>>>()

        increment()
        apiService.getTrendingTv(API_KEY).enqueue(object : Callback<TvTrendingResponse> {
            override fun onResponse(
                call: Call<TvTrendingResponse>,
                response: Response<TvTrendingResponse>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()?.results
                    if (data != null) {
                        listTv.value =
                            ApiResponse.success(data)
                    } else {
                        listTv.value =
                            ApiResponse.empty(messageEmpty, emptyList())
                    }
                    decrement()
                } else {
                    Log.e(TAG, "onResponse: ${response.message()}")
                    listTv.value =
                        ApiResponse.error(response.message(), emptyList())
                }
            }

            override fun onFailure(call: Call<TvTrendingResponse>, t: Throwable) {
                t.printStackTrace()
                Log.e(TAG, "onFailure: ${t.message}")
                listTv.value = ApiResponse.error(t.message.toString(), emptyList())
            }
        })

        return listTv
    }


    /*
     * Detail Activity
     */
    fun getDetailMovie(id: Int): LiveData<ApiResponse<DetailMovieResponse>> {
        val detailMovie = MutableLiveData<ApiResponse<DetailMovieResponse>>()

        increment()
        apiService.getDetailMovie(id, API_KEY).enqueue(object : Callback<DetailMovieResponse> {
            override fun onResponse(
                call: Call<DetailMovieResponse>,
                response: Response<DetailMovieResponse>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()
                    if (data != null) {
                        detailMovie.value =
                            ApiResponse.success(data)
                    } else {
                        detailMovie.value =
                            ApiResponse.empty(
                                messageEmpty,
                                DetailMovieResponse(
                                    id = 0,
                                    overview = "",
                                    posterPath = "",
                                    title = "Unknown",
                                    voteAverage = 0.0
                                )
                            )
                    }
                    decrement()
                } else {
                    Log.e(TAG, "onResponse: ${response.message()}")
                    detailMovie.value =
                        ApiResponse.error(
                            response.message(),
                            DetailMovieResponse(
                                id = 0,
                                overview = "",
                                posterPath = "",
                                title = "Unknown",
                                voteAverage = 0.0
                            )
                        )
                }
            }

            override fun onFailure(call: Call<DetailMovieResponse>, t: Throwable) {
                t.printStackTrace()
                Log.e(TAG, "onFailure: ${t.message}")
                detailMovie.value = ApiResponse.error(
                    t.message.toString(),
                    DetailMovieResponse(
                        id = 0,
                        overview = "",
                        posterPath = "",
                        title = "Unknown",
                        voteAverage = 0.0
                    )
                )
            }
        })

        return detailMovie
    }

    fun getDetailTv(id: Int): LiveData<ApiResponse<DetailTvResponse>> {
        val detailTv = MutableLiveData<ApiResponse<DetailTvResponse>>()

        increment()
        apiService.getDetailTv(id, API_KEY).enqueue(object : Callback<DetailTvResponse> {
            override fun onResponse(
                call: Call<DetailTvResponse>,
                response: Response<DetailTvResponse>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()
                    if (data != null) {
                        detailTv.value =
                            ApiResponse.success(data)
                    } else {
                        detailTv.value =
                            ApiResponse.empty(
                                messageEmpty,
                                DetailTvResponse(
                                    id = 0,
                                    overview = "",
                                    posterPath = "",
                                    name = "Unknown",
                                    voteAverage = 0.0
                                )
                            )
                    }
                    decrement()
                } else {
                    Log.e(TAG, "onResponse: ${response.message()}")
                    detailTv.value =
                        ApiResponse.error(
                            response.message(),
                            DetailTvResponse(
                                id = 0,
                                overview = "",
                                posterPath = "",
                                name = "Unknown",
                                voteAverage = 0.0
                            )
                        )
                }
            }

            override fun onFailure(call: Call<DetailTvResponse>, t: Throwable) {
                t.printStackTrace()
                Log.e(TAG, "onFailure: ${t.message}")
                detailTv.value = ApiResponse.error(
                    t.message.toString(),
                    DetailTvResponse(
                        id = 0,
                        overview = "",
                        posterPath = "",
                        name = "Unknown",
                        voteAverage = 0.0
                    )
                )
            }
        })

        return detailTv
    }

}