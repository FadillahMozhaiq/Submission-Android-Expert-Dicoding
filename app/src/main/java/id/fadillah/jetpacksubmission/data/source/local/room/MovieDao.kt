package id.fadillah.jetpacksubmission.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import id.fadillah.jetpacksubmission.data.source.local.model.MovieDatabaseEntity

@Dao
interface MovieDao {
    //    Movies
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movie: List<MovieDatabaseEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: MovieDatabaseEntity)

    @Query("SELECT * FROM movies WHERE favorite = 1 AND id = :id")
    fun checkIsFavorite(id: Int): LiveData<List<MovieDatabaseEntity>>

    @Query("UPDATE movies SET favorite = :favorite WHERE id = :id")
    suspend fun setFavorite(favorite: Boolean, id: Int)

    @Transaction
    @Query("SELECT * FROM movies WHERE id = :movieId")
    fun getMovieById(movieId: Int): LiveData<MovieDatabaseEntity>

    @Query("UPDATE movies SET genres = :genres, status = :status, tagLine = :tagLine WHERE id = :id")
    fun updateMovie(
        id: Int,
        genres: String,
        status: String?,
        tagLine: String?
    ): Int

    //    Movies Upcoming
    @Query("SELECT * FROM movies WHERE saveFor LIKE 'upcoming'")
    fun getAllUpcomingMovies(): DataSource.Factory<Int, MovieDatabaseEntity>

    //    Movies Now Playing
    @Query("SELECT * FROM movies WHERE saveFor LIKE 'nowPlaying'")
    fun getAllNowPlayingMovies(): DataSource.Factory<Int, MovieDatabaseEntity>

    //    Movies Popular
    @Query("SELECT * FROM movies WHERE saveFor LIKE 'popular'")
    fun getAllPopularMovies(): DataSource.Factory<Int, MovieDatabaseEntity>

    //    Movies Top Rated
    @Query("SELECT * FROM movies WHERE saveFor LIKE 'topRated'")
    fun getAllTopRatedMovies(): DataSource.Factory<Int, MovieDatabaseEntity>

    //    Movies Explore
    @Query("SELECT * FROM movies WHERE title LIKE :query AND type = 0 ")
    fun getMovieExplore(query: String): DataSource.Factory<Int, MovieDatabaseEntity>

    //    Tv Explore
    @Query("SELECT * FROM movies WHERE title LIKE :query AND type = 1")
    fun getTvExplore(query: String): DataSource.Factory<Int, MovieDatabaseEntity>

    //    Person Explore
    @Query("SELECT * FROM movies WHERE title LIKE :query AND type = 2")
    fun getPersonExplore(query: String): DataSource.Factory<Int, MovieDatabaseEntity>

    //    Company Explore
    @Query("SELECT * FROM movies WHERE title LIKE :query AND type = 3")
    fun getCompanyExplore(query: String): DataSource.Factory<Int, MovieDatabaseEntity>

    //    Multi Explore
    @Query("SELECT * FROM movies WHERE title LIKE :query")
    fun getMultiSearch(query: String): DataSource.Factory<Int, MovieDatabaseEntity>

    //    Movies Trending
    @Query("SELECT * FROM movies WHERE saveFor LIKE 'trendingMovie'")
    fun getAllTrendingMovies(): DataSource.Factory<Int, MovieDatabaseEntity>

    //    Movies Top Rated
    @Query("SELECT * FROM movies WHERE saveFor LIKE 'trendingTv'")
    fun getAllTrendingTv(): DataSource.Factory<Int, MovieDatabaseEntity>

    //  Favorite Movies
    @Query("SELECT * FROM movies WHERE favorite = 1 AND type = 0")
    fun getAllFavoriteMovies(): DataSource.Factory<Int, MovieDatabaseEntity>

    //  Favorite TV
    @Query("SELECT * FROM movies WHERE favorite = 1 AND type = 1")
    fun getAllFavoriteTv(): DataSource.Factory<Int, MovieDatabaseEntity>
}