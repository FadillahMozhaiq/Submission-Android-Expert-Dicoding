package id.fadillah.jetpacksubmission.data.source.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.DataSource
import id.fadillah.jetpacksubmission.data.source.local.model.FavoriteMovieEntity
import id.fadillah.jetpacksubmission.data.source.local.model.FavoriteTvEntity
import id.fadillah.jetpacksubmission.data.source.local.model.MovieDatabaseEntity
import id.fadillah.jetpacksubmission.data.source.local.room.MovieDao

class LocalDataSource(private val movieDao: MovieDao) {
    //    Movies
    suspend fun insertMovies(movies: List<MovieDatabaseEntity>) =
        movieDao.insertMovies(movies)

    fun checkIsFavorite(id: Int, type: Int): LiveData<Boolean> =
        when (type) {
            0 -> Transformations.map(
                movieDao.checkIsFavoriteMovie(id)
            ) {
                it.isNotEmpty()
            }
            1 -> Transformations.map(
                movieDao.checkIsFavoriteTv(id)
            ) {
                it.isNotEmpty()
            }
            else -> Transformations.map(
                movieDao.checkIsFavoriteMovie(id)
            ) {
                it.isNotEmpty()
            }
        }

    fun getMovieById(movieId: Int): LiveData<MovieDatabaseEntity> =
        movieDao.getMovieById(movieId)

    suspend fun setFavorite(status: Boolean, id: Int, type: Int) {
        if (type == 0) {
//            Movies
            if (status) {
//                Insert
                movieDao.insertFavoriteMovie(FavoriteMovieEntity(id))
            } else {
//                Delete
                movieDao.deleteFavoriteMovie(FavoriteMovieEntity(id))
            }
        } else if (type == 1) {
//            Tv Show
            if (status) {
//                Insert
                movieDao.insertFavoriteTv(FavoriteTvEntity(id))
            } else {
//                Delete
                movieDao.deleteFavoriteTv(FavoriteTvEntity(id))
            }
        }
    }

    fun updateMovie(movie: MovieDatabaseEntity): Int = with(movie) {
        movieDao.updateMovie(
            id,
            genres?.joinToString(", ") { it } ?: "Unknown",
            status,
            tagLine)
    }

    //    Movies Upcoming
    fun getAllUpcomingMovies(): DataSource.Factory<Int, MovieDatabaseEntity> =
        movieDao.getAllUpcomingMovies()

    //    Movies Now Playing
    fun getAllNowPlayingMovies(): DataSource.Factory<Int, MovieDatabaseEntity> =
        movieDao.getAllNowPlayingMovies()

    //    Movies Popular
    fun getAllPopularMovies(): DataSource.Factory<Int, MovieDatabaseEntity> =
        movieDao.getAllPopularMovies()

    //    Movies Top Rated
    fun getAllTopRatedMovies(): DataSource.Factory<Int, MovieDatabaseEntity> =
        movieDao.getAllTopRatedMovies()

    //    Movies Explore
    fun getMovieExplore(query: String): DataSource.Factory<Int, MovieDatabaseEntity> =
        movieDao.getMovieExplore("%$query%")

    //    Tv Explore
    fun getTvExplore(query: String): DataSource.Factory<Int, MovieDatabaseEntity> =
        movieDao.getTvExplore("%$query%")

    //    Person Explore
    fun getPersonExplore(query: String): DataSource.Factory<Int, MovieDatabaseEntity> =
        movieDao.getPersonExplore("%$query%")

    //    Company Explore
    fun getCompanyExplore(query: String): DataSource.Factory<Int, MovieDatabaseEntity> =
        movieDao.getCompanyExplore("%$query%")

    //    Multi Explore
    fun getMultiSearch(query: String): DataSource.Factory<Int, MovieDatabaseEntity> =
        movieDao.getMultiSearch("%$query%")

    //    Movies Trending
    fun getAllTrendingMovies(): DataSource.Factory<Int, MovieDatabaseEntity> =
        movieDao.getAllTrendingMovies()

    //    Tv Trending
    fun getAllTrendingTv(): DataSource.Factory<Int, MovieDatabaseEntity> =
        movieDao.getAllTrendingTv()

    //    Movies Trending
    fun getAllFavoriteMovies(): DataSource.Factory<Int, MovieDatabaseEntity> =
        movieDao.getAllFavoriteMovies()

    //    Tv Trending
    fun getAllFavoriteTv(): DataSource.Factory<Int, MovieDatabaseEntity> =
        movieDao.getAllFavoriteTv()
}