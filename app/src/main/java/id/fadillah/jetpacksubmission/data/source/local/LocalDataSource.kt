package id.fadillah.jetpacksubmission.data.source.local

import androidx.paging.DataSource
import id.fadillah.jetpacksubmission.data.source.local.model.MovieDatabaseEntity
import id.fadillah.jetpacksubmission.data.source.local.room.MovieDao

class LocalDataSource(private val movieDao: MovieDao) {

    //    Movies
    suspend fun insertMovies(movies: List<MovieDatabaseEntity>) = movieDao.insertMovies(movies)
    suspend fun insertMovie(movie: MovieDatabaseEntity) = movieDao.insertMovie(movie)
    fun getAllFavoriteMovies(): DataSource.Factory<Int, MovieDatabaseEntity> =
        movieDao.getAllFavoriteMovies()

    fun getMovieById(movieId: Int): DataSource.Factory<Int, MovieDatabaseEntity> =
        movieDao.getMovieById(movieId)

    fun updateMovie(movie: MovieDatabaseEntity): Int = with(movie) {
        movieDao.updateMovie(
            id,
            genres?.joinToString(", ") { it } ?: "Unknown",
            status,
            tagLine,
            favorite)
    }
//    fun deleteMovie(movie: MovieDatabaseEntity): Int = movieDao.deleteMovie(movie)

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
}