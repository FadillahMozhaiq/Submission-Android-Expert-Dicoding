package id.fadillah.jetpacksubmission.core.data.source.local

import id.fadillah.jetpacksubmission.core.data.source.local.model.FavoriteMovieEntity
import id.fadillah.jetpacksubmission.core.data.source.local.model.FavoriteTvEntity
import id.fadillah.jetpacksubmission.core.data.source.local.model.MovieDatabaseEntity
import id.fadillah.jetpacksubmission.core.data.source.local.room.MovieDao
import id.fadillah.jetpacksubmission.core.utils.helper.EspressoIdlingResource.decrement
import id.fadillah.jetpacksubmission.core.utils.helper.EspressoIdlingResource.increment
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class LocalDataSource(private val movieDao: MovieDao) {
    //    Movies
    suspend fun insertMovies(movies: List<MovieDatabaseEntity>) {
        increment()
        movieDao.insertMovies(movies)
        decrement()
    }


    fun checkIsFavorite(id: Int, type: Int): Flow<Boolean> =
        flow {
            emitAll(
                when (type) {
                    0 -> movieDao.checkIsFavoriteMovie(id).map {
                        it.isNotEmpty()
                    }
                    1 -> movieDao.checkIsFavoriteTv(id).map {
                        it.isNotEmpty()
                    }
                    else -> movieDao.checkIsFavoriteTv(id).map {
                        it.isNotEmpty()
                    }
                })
        }

    fun getMovieById(movieId: Int): Flow<MovieDatabaseEntity> =
        movieDao.getMovieById(movieId)

    suspend fun setFavorite(status: Boolean, id: Int, type: Int) {
        increment()
        if (type == 0) {
//            Movies
            if (status) {
//                Insert
                movieDao.insertFavoriteMovie(FavoriteMovieEntity(id))
                decrement()
            } else {
//                Delete
                movieDao.deleteFavoriteMovie(FavoriteMovieEntity(id))
                decrement()
            }
        } else if (type == 1) {
//            Tv Show
            if (status) {
//                Insert
                movieDao.insertFavoriteTv(FavoriteTvEntity(id))
                decrement()
            } else {
//                Delete
                movieDao.deleteFavoriteTv(FavoriteTvEntity(id))
                decrement()
            }
        }
    }

    suspend fun updateMovie(movie: MovieDatabaseEntity): Int = with(movie) {
        movieDao.updateMovie(
            id,
            genres?.joinToString(", ") { it } ?: "Unknown",
            status,
            tagLine)
    }

    //    Movies Upcoming
    fun getAllUpcomingMovies(): Flow<List<MovieDatabaseEntity>> =
        movieDao.getAllUpcomingMovies()

    //    Movies Now Playing
    fun getAllNowPlayingMovies(): Flow<List<MovieDatabaseEntity>> =
        movieDao.getAllNowPlayingMovies()

    //    Movies Popular
    fun getAllPopularMovies(): Flow<List<MovieDatabaseEntity>> =
        movieDao.getAllPopularMovies()

    //    Movies Top Rated
    fun getAllTopRatedMovies(): Flow<List<MovieDatabaseEntity>> =
        movieDao.getAllTopRatedMovies()

    //    Movies Explore
    fun getMovieExplore(query: String): Flow<List<MovieDatabaseEntity>> =
        movieDao.getMovieExplore("%$query%")

    //    Tv Explore
    fun getTvExplore(query: String): Flow<List<MovieDatabaseEntity>> =
        movieDao.getTvExplore("%$query%")

    //    Person Explore
    fun getPersonExplore(query: String): Flow<List<MovieDatabaseEntity>> =
        movieDao.getPersonExplore("%$query%")

    //    Company Explore
    fun getCompanyExplore(query: String): Flow<List<MovieDatabaseEntity>> =
        movieDao.getCompanyExplore("%$query%")

    //    Multi Explore
    fun getMultiSearch(query: String): Flow<List<MovieDatabaseEntity>> =
        movieDao.getMultiSearch("%$query%")

    //    Movies Trending
    fun getAllTrendingMovies(): Flow<List<MovieDatabaseEntity>> =
        movieDao.getAllTrendingMovies()

    //    Tv Trending
    fun getAllTrendingTv(): Flow<List<MovieDatabaseEntity>> =
        movieDao.getAllTrendingTv()

    //    Movies Trending
    fun getAllFavoriteMovies(): Flow<List<MovieDatabaseEntity>> =
        movieDao.getAllFavoriteMovies()

    //    Tv Trending
    fun getAllFavoriteTv(): Flow<List<MovieDatabaseEntity>> =
        movieDao.getAllFavoriteTv()
}