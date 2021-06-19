package id.fadillah.jetpacksubmission.data.source.network

import android.util.Log
import id.fadillah.jetpacksubmission.BuildConfig.API_KEY
import id.fadillah.jetpacksubmission.data.source.network.response.*
import id.fadillah.jetpacksubmission.utils.helper.EspressoIdlingResource.decrement
import id.fadillah.jetpacksubmission.utils.helper.EspressoIdlingResource.increment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

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
    suspend fun getUpcoming(): Flow<ApiResponse<List<ResultsUpcomingItem>>> =
        flow {
            try {
                increment()
                val response = apiService.getUpcoming(API_KEY)
                val dataArray = response.resultsUpcoming
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.resultsUpcoming))
                    decrement()
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e(TAG, e.toString())
            }
        }.flowOn(Dispatchers.IO)

    suspend fun getNowPlaying(): Flow<ApiResponse<List<ResultsNowPlayingItem>>> =
        flow {
            try {
                increment()
                val response = apiService.getNowPlaying(API_KEY)
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                    decrement()
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e(TAG, e.toString())
            }
        }.flowOn(Dispatchers.IO)

    suspend fun getPopular(): Flow<ApiResponse<List<ResultsPopularMovieItem>>> =
        flow {
            try {
                increment()
                val response = apiService.getPopular(API_KEY)
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                    decrement()
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e(TAG, e.toString())
            }
        }.flowOn(Dispatchers.IO)

    suspend fun getTopRated(): Flow<ApiResponse<List<ResultsTopRatedMovieItem>>> =
        flow {
            try {
                increment()
                val response = apiService.getTopRated(API_KEY)
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                    decrement()
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e(TAG, e.toString())
            }
        }.flowOn(Dispatchers.IO)

    /*
     * Explore Fragment
     */
    suspend fun getMovieExplore(query: String): Flow<ApiResponse<List<ResultsItemExplore>>> =
        flow {
            try {
                increment()
                val response = apiService.getMovieExplore(API_KEY, query)
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                    decrement()
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e(TAG, e.toString())
            }
        }.flowOn(Dispatchers.IO)

    suspend fun getTvExplore(query: String): Flow<ApiResponse<List<ResultsTvExploreItem>>> =
        flow {
            try {
                increment()
                val response = apiService.getTvExplore(API_KEY, query)
                val dataArray = response.resultsTvExplore
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.resultsTvExplore))
                    decrement()
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e(TAG, e.toString())
            }
        }.flowOn(Dispatchers.IO)

    suspend fun getPersonExplore(query: String): Flow<ApiResponse<List<ResultsPersonExploreItem>>> =
        flow {
            try {
                increment()
                val response = apiService.getPersonExplore(API_KEY, query)
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                    decrement()
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e(TAG, e.toString())
            }
        }.flowOn(Dispatchers.IO)

    suspend fun getCompanyExplore(query: String): Flow<ApiResponse<List<ResultsItem>>> =
        flow {
            try {
                increment()
                val response = apiService.getCompanyExplore(API_KEY, query)
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                    decrement()
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e(TAG, e.toString())
            }
        }.flowOn(Dispatchers.IO)

    suspend fun getMultiSearchExplore(query: String): Flow<ApiResponse<List<ResultsMultiSearchItem>>> =
        flow {
            try {
                increment()
                val response = apiService.getMultiExplore(API_KEY, query)
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                    decrement()
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e(TAG, e.toString())
            }
        }.flowOn(Dispatchers.IO)

    /*
    * Movie Fragment
     */
    suspend fun getMovieTrending(): Flow<ApiResponse<List<ResultsMovieTrendingItem>>> =
        flow {
            try {
                increment()
                val response = apiService.getTrendingMovie(API_KEY)
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                    decrement()
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e(TAG, e.toString())
            }
        }.flowOn(Dispatchers.IO)

    /*
    * TV Fragment
     */
    suspend fun getTvTrending(): Flow<ApiResponse<List<ResultsTvTrendingItem>>> =
        flow {
            try {
                increment()
                val response = apiService.getTrendingTv(API_KEY)
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                    decrement()
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e(TAG, e.toString())
            }
        }.flowOn(Dispatchers.IO)


    /*
     * Detail Activity
     */
    suspend fun getDetailMovie(id: Int): Flow<ApiResponse<DetailMovieResponse>> =
        flow {
            try {
                increment()
                val response = apiService.getDetailMovie(id, API_KEY)
                emit(ApiResponse.Success(response))
                decrement()
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e(TAG, e.toString())
            }
        }.flowOn(Dispatchers.IO)

    suspend fun getDetailTv(id: Int): Flow<ApiResponse<DetailTvResponse>> =
        flow {
            try {
                increment()
                val response = apiService.getDetailTv(id, API_KEY)
                emit(ApiResponse.Success(response))
                decrement()
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e(TAG, e.toString())
            }
        }.flowOn(Dispatchers.IO)

}