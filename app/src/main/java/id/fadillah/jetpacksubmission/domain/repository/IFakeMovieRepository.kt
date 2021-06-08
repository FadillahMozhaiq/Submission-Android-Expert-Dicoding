package id.fadillah.jetpacksubmission.domain.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import id.fadillah.jetpacksubmission.data.model.MovieEntity
import id.fadillah.jetpacksubmission.data.source.local.model.MovieDatabaseEntity
import id.fadillah.jetpacksubmission.vo.Resource

interface IFakeMovieRepository {
    //     Home Fragment
    fun getUpcoming(): LiveData<Resource<PagedList<MovieDatabaseEntity>>>
    fun getNowPlaying(): LiveData<Resource<PagedList<MovieDatabaseEntity>>>
    fun getPopular(): LiveData<Resource<PagedList<MovieDatabaseEntity>>>
    fun getTopRated(): LiveData<Resource<PagedList<MovieDatabaseEntity>>>

    //    Explore Fragment
    fun getMovieExplore(query: String): LiveData<Resource<PagedList<MovieDatabaseEntity>>>
    fun getTvExplore(query: String): LiveData<Resource<PagedList<MovieDatabaseEntity>>>
    fun getPersonExplore(query: String): LiveData<Resource<PagedList<MovieDatabaseEntity>>>
    fun getCompanyExplore(query: String): LiveData<Resource<PagedList<MovieDatabaseEntity>>>
    fun getMultiSearch(query: String): LiveData<Resource<PagedList<MovieDatabaseEntity>>>

    //    MovieFragment
    fun getTrendingMovie(): LiveData<Resource<PagedList<MovieDatabaseEntity>>>

    //    Tv Fragment
    fun getTrendingTv(): LiveData<Resource<PagedList<MovieDatabaseEntity>>>

    //    Detail Activity
    fun getDetailMovie(id: Int): LiveData<Resource<MovieDatabaseEntity>>
    fun getDetailTv(id: Int): LiveData<Resource<MovieDatabaseEntity>>
    fun isItFavorite(id: Int, type: Int): LiveData<Boolean>
    suspend fun setFavorite(status: Boolean, id: Int, type: Int)

    //    Favorite Movie Fragment
    fun getAllFavoriteMovie(): LiveData<PagedList<MovieDatabaseEntity>>

    //    Favorite Tv Show
    fun getAllFavoriteTv(): LiveData<PagedList<MovieDatabaseEntity>>
}