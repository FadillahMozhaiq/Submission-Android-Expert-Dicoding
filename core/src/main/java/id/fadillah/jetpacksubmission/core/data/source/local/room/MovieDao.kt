package id.fadillah.jetpacksubmission.core.data.source.local.room

import androidx.room.*
import id.fadillah.jetpacksubmission.core.data.source.local.model.FavoriteMovieEntity
import id.fadillah.jetpacksubmission.core.data.source.local.model.FavoriteTvEntity
import id.fadillah.jetpacksubmission.core.data.source.local.model.MovieDatabaseEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    //    Favorite
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: MovieDatabaseEntity)

    @Query("SELECT * FROM favorite_movie WHERE idMovie = :id")
    fun checkIsFavoriteMovie(id: Int): Flow<List<FavoriteMovieEntity>>

    @Query("SELECT * FROM favorite_tv WHERE idTvShow = :id")
    fun checkIsFavoriteTv(id: Int): Flow<List<FavoriteTvEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteMovie(movieFavorite: FavoriteMovieEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteTv(tvFavorite: FavoriteTvEntity)

    @Delete
    suspend fun deleteFavoriteMovie(movieFavorite: FavoriteMovieEntity)

    @Delete
    suspend fun deleteFavoriteTv(tvFavorite: FavoriteTvEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movie: List<MovieDatabaseEntity>)

    @Transaction
    @Query("SELECT * FROM movies WHERE id = :movieId")
    fun getMovieById(movieId: Int): Flow<MovieDatabaseEntity>

    @Query("UPDATE movies SET genres = :genres, status = :status, tagLine = :tagLine WHERE id = :id")
    suspend fun updateMovie(
        id: Int,
        genres: String,
        status: String?,
        tagLine: String?
    ): Int

    //    Movies Upcoming
    @Query("SELECT * FROM movies WHERE saveFor LIKE 'upcoming'")
    fun getAllUpcomingMovies(): Flow<List<MovieDatabaseEntity>>

    //    Movies Now Playing
    @Query("SELECT * FROM movies WHERE saveFor LIKE 'nowPlaying'")
    fun getAllNowPlayingMovies(): Flow<List<MovieDatabaseEntity>>

    //    Movies Popular
    @Query("SELECT * FROM movies WHERE saveFor LIKE 'popular'")
    fun getAllPopularMovies(): Flow<List<MovieDatabaseEntity>>

    //    Movies Top Rated
    @Query("SELECT * FROM movies WHERE saveFor LIKE 'topRated'")
    fun getAllTopRatedMovies(): Flow<List<MovieDatabaseEntity>>

    //    Movies Explore
    @Query("SELECT * FROM movies WHERE title LIKE :query AND type = 0 ")
    fun getMovieExplore(query: String): Flow<List<MovieDatabaseEntity>>

    //    Tv Explore
    @Query("SELECT * FROM movies WHERE title LIKE :query AND type = 1")
    fun getTvExplore(query: String): Flow<List<MovieDatabaseEntity>>

    //    Person Explore
    @Query("SELECT * FROM movies WHERE title LIKE :query AND type = 2")
    fun getPersonExplore(query: String): Flow<List<MovieDatabaseEntity>>

    //    Company Explore
    @Query("SELECT * FROM movies WHERE title LIKE :query AND type = 3")
    fun getCompanyExplore(query: String): Flow<List<MovieDatabaseEntity>>

    //    Multi Explore
    @Query("SELECT * FROM movies WHERE title LIKE :query")
    fun getMultiSearch(query: String): Flow<List<MovieDatabaseEntity>>

    //    Movies Trending
    @Query("SELECT * FROM movies WHERE saveFor LIKE 'trendingMovie'")
    fun getAllTrendingMovies(): Flow<List<MovieDatabaseEntity>>

    //    Movies Top Rated
    @Query("SELECT * FROM movies WHERE saveFor LIKE 'trendingTv'")
    fun getAllTrendingTv(): Flow<List<MovieDatabaseEntity>>

    //  Favorite Movies
    @Query("SELECT * FROM movies WHERE id IN (SELECT * FROM favorite_movie)")
    fun getAllFavoriteMovies(): Flow<List<MovieDatabaseEntity>>

    //  Favorite TV
    @Query("SELECT * FROM movies WHERE id IN (SELECT * FROM favorite_tv)")
    fun getAllFavoriteTv(): Flow<List<MovieDatabaseEntity>>
}