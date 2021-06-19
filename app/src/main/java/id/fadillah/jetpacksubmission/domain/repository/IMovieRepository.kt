package id.fadillah.jetpacksubmission.domain.repository

import id.fadillah.jetpacksubmission.data.model.MovieEntity
import id.fadillah.jetpacksubmission.vo.Resource
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    //     Home Fragment
    fun getUpcoming(): Flow<Resource<List<MovieEntity>>>
    fun getNowPlaying(): Flow<Resource<List<MovieEntity>>>
    fun getPopular(): Flow<Resource<List<MovieEntity>>>
    fun getTopRated(): Flow<Resource<List<MovieEntity>>>

    //    Explore Fragment
    fun getMovieExplore(query: String): Flow<Resource<List<MovieEntity>>>
    fun getTvExplore(query: String): Flow<Resource<List<MovieEntity>>>
    fun getPersonExplore(query: String): Flow<Resource<List<MovieEntity>>>
    fun getCompanyExplore(query: String): Flow<Resource<List<MovieEntity>>>
    fun getMultiSearch(query: String): Flow<Resource<List<MovieEntity>>>

    //    MovieFragment
    fun getTrendingMovie(): Flow<Resource<List<MovieEntity>>>

    //    Tv Fragment
    fun getTrendingTv(): Flow<Resource<List<MovieEntity>>>

    //    Detail Activity
    fun getDetailMovie(id: Int): Flow<Resource<MovieEntity>>
    fun getDetailTv(id: Int): Flow<Resource<MovieEntity>>
    fun isItFavorite(id: Int, type: Int): Flow<Boolean>
    suspend fun setFavorite(status: Boolean, id: Int, type: Int)

    //    Favorite Movie Fragment
    fun getAllFavoriteMovie(): Flow<List<MovieEntity>>

    //    Favorite Tv Show
    fun getAllFavoriteTv(): Flow<List<MovieEntity>>
}