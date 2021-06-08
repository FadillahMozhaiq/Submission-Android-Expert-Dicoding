package id.fadillah.jetpacksubmission.domain.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import id.fadillah.jetpacksubmission.data.model.MovieEntity
import id.fadillah.jetpacksubmission.vo.Resource

interface IMovieRepository {
    //     Home Fragment
    fun getUpcoming(): LiveData<Resource<PagedList<MovieEntity>>>
    fun getNowPlaying(): LiveData<Resource<PagedList<MovieEntity>>>
    fun getPopular(): LiveData<Resource<PagedList<MovieEntity>>>
    fun getTopRated(): LiveData<Resource<PagedList<MovieEntity>>>

    //    Explore Fragment
    fun getMovieExplore(query: String): LiveData<Resource<PagedList<MovieEntity>>>
    fun getTvExplore(query: String): LiveData<Resource<PagedList<MovieEntity>>>
    fun getPersonExplore(query: String): LiveData<Resource<PagedList<MovieEntity>>>
    fun getCompanyExplore(query: String): LiveData<Resource<PagedList<MovieEntity>>>
    fun getMultiSearch(query: String): LiveData<Resource<PagedList<MovieEntity>>>

    //    MovieFragment
    fun getTrendingMovie(): LiveData<Resource<PagedList<MovieEntity>>>

    //    Tv Fragment
    fun getTrendingTv(): LiveData<Resource<PagedList<MovieEntity>>>

    //    Detail Activity
    fun getDetailMovie(id: Int): LiveData<Resource<MovieEntity>>
    fun getDetailTv(id: Int): LiveData<Resource<MovieEntity>>
    fun isItFavorite(id: Int, type: Int): LiveData<Boolean>
    suspend fun setFavorite(status: Boolean, id: Int, type: Int)

    //    Favorite Movie Fragment
    fun getAllFavoriteMovie(): LiveData<PagedList<MovieEntity>>

    //    Favorite Tv Show
    fun getAllFavoriteTv(): LiveData<PagedList<MovieEntity>>
}