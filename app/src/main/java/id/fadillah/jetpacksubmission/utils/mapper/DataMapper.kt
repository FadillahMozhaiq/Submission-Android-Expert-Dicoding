package id.fadillah.jetpacksubmission.utils.mapper

import android.annotation.SuppressLint
import id.fadillah.jetpacksubmission.data.model.MovieEntity
import id.fadillah.jetpacksubmission.data.source.local.model.MovieDatabaseEntity
import id.fadillah.jetpacksubmission.data.source.network.response.*
import java.text.DateFormat
import java.text.SimpleDateFormat

object DataMapper {
    private const val unknown = "Unknown"

    @SuppressLint("SimpleDateFormat")
    fun convertDate(data: String): String {
        val inputFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd")
        val outputFormat: DateFormat = SimpleDateFormat("dd MMM yyyy")
        val date = inputFormat.parse(data)
        return outputFormat.format(date)
    }

    fun mapUpcomingToEntity(data: List<MovieDatabaseEntity>): List<MovieEntity> =
        data.map {
            MovieEntity(
                it.id,
                it.title,
                it.posterPath,
                it.overview,
                it.posterPath,
                type = 0,
                date = it.date,
                rating = it.rating,
            )
        }


    fun mapUpcomingToDatabaseEntity(data: List<ResultsUpcomingItem>): List<MovieDatabaseEntity> =
        data.map {
            MovieDatabaseEntity(
                it.id ?: 0,
                it.title ?: unknown,
                it.posterPath ?: unknown,
                it.overview ?: "",
                it.backdropPath,
                type = 0,
                date = it.releaseDate,
                rating = it.voteAverage,
                saveFor = "upcoming"
            )
        }

    fun mapNowPlayingToEntity(data: List<MovieDatabaseEntity>): List<MovieEntity> =
        data.map {
            with(it) {
                MovieEntity(
                    id,
                    title,
                    posterPath,
                    overview,
                    posterPath,
                    type = 0,
                    date = it.date,
                    rating = it.rating
                )
            }

        }

    fun mapNowPlayingToDatabaseEntity(data: List<ResultsNowPlayingItem>): List<MovieDatabaseEntity> =
        data.map {
            with(it) {
                MovieDatabaseEntity(
                    id ?: 0,
                    title ?: unknown,
                    posterPath ?: unknown,
                    overview ?: unknown,
                    backdropPath,
                    type = 0,
                    date = it.releaseDate,
                    rating = it.voteAverage,
                    saveFor = "nowPlaying"
                )
            }

        }

    fun mapPopularToEntity(data: List<MovieDatabaseEntity>): List<MovieEntity> =
        data.map {
            with(it) {
                MovieEntity(
                    id,
                    title,
                    posterPath,
                    overview,
                    posterPath,
                    type = 0,
                    date = it.date,
                    rating = it.rating
                )
            }
        }

    fun mapPopularToDatabaseEntity(data: List<ResultsPopularMovieItem>): List<MovieDatabaseEntity> =
        data.map {
            with(it) {
                MovieDatabaseEntity(
                    id,
                    title,
                    posterPath,
                    overview,
                    backdropPath,
                    type = 0,
                    date = it.releaseDate,
                    rating = it.voteAverage,
                    saveFor = "popular"
                )
            }

        }

    fun mapTopRatedToEntity(data: List<MovieDatabaseEntity>): List<MovieEntity> =
        data.map {
            with(it) {
                MovieEntity(
                    id,
                    title,
                    posterPath,
                    overview,
                    posterPath,
                    type = 0,
                    date = it.date,
                    rating = it.rating
                )
            }
        }

    fun mapTopRatedToDatabaseEntity(data: List<ResultsTopRatedMovieItem>): List<MovieDatabaseEntity> =
        data.map {
            with(it) {
                MovieDatabaseEntity(
                    id,
                    title,
                    posterPath,
                    overview,
                    backdropPath,
                    type = 0,
                    date = it.releaseDate,
                    rating = it.voteAverage,
                    saveFor = "topRated"
                )
            }
        }

    fun mapMovieExploreToEntity(data: List<MovieDatabaseEntity>): List<MovieEntity> =
        data.map {
            MovieEntity(
                it.id,
                it.title,
                it.posterPath,
                it.overview,
                it.posterPath,
                type = 0,
                date = it.date,
                rating = it.rating
            )
        }

    fun mapMovieExploreToDatabaseEntity(data: List<ResultsItemExplore>): List<MovieDatabaseEntity> =
        data.map {
            with(it) {
                MovieDatabaseEntity(
                    id ?: 0,
                    title ?: unknown,
                    posterPath ?: unknown,
                    overview,
                    backdropPath,
                    type = 0,
                    date = it.releaseDate,
                    rating = it.voteAverage,
                    saveFor = "movieExplore"
                )
            }
        }

    fun mapTvExploreToEntity(data: List<MovieDatabaseEntity>): List<MovieEntity> =
        data.map {
            MovieEntity(
                it.id,
                it.title,
                it.posterPath,
                it.overview,
                it.posterPath,
                type = 1,
                date = it.date,
                rating = it.rating
            )
        }

    fun mapTvExploreToDatabaseEntity(data: List<ResultsTvExploreItem>): List<MovieDatabaseEntity> =
        data.map {
            with(it) {
                MovieDatabaseEntity(
                    id ?: 0,
                    name ?: unknown,
                    posterPath ?: unknown,
                    overview,
                    backdropPath,
                    type = 1,
                    date = firstAirDate,
                    rating = voteAverage,
                    saveFor = "tvExplore"
                )
            }
        }

    fun mapPersonExploreToEntity(data: List<MovieDatabaseEntity>): List<MovieEntity> =
        data.map {
            MovieEntity(
                it.id,
                it.title,
                it.posterPath,
                "",
                it.backgroundPath,
                type = 2
            )
        }

    fun mapPersonExploreToDatabaseEntity(data: List<ResultsPersonExploreItem>): List<MovieDatabaseEntity> =
        data.map {
            with(it) {
                MovieDatabaseEntity(
                    id ?: 0,
                    name ?: unknown,
                    profilePath ?: unknown,
                    "",
                    profilePath,
                    type = 2,
                    saveFor = "personExplore"
                )
            }
        }

    fun mapCompanyExploreToEntity(data: List<MovieDatabaseEntity>): List<MovieEntity> =
        data.map {
            MovieEntity(
                it.id,
                it.title,
                it.posterPath,
                "",
                it.backgroundPath,
                type = 3
            )
        }

    fun mapCompanyExploreToDatabaseEntity(data: List<ResultsItem>): List<MovieDatabaseEntity> =
        data.map {
            with(it) {
                MovieDatabaseEntity(
                    id ?: 0,
                    name ?: unknown,
                    logoPath ?: unknown,
                    "",
                    logoPath,
                    type = 3,
                    saveFor = "companyExplore"
                )
            }
        }

    fun mapMultiExploreToEntity(data: List<MovieDatabaseEntity>): List<MovieEntity> =
        data.map {
            MovieEntity(
                it.id,
                it.title,
                it.posterPath,
                it.overview,
                it.backgroundPath,
                type = 4,
                date = it.date,
                rating = it.rating ?: 0.0,
                mediaType = it.mediaType
            )
        }

    fun mapMultiExploreToDatabaseEntity(data: List<ResultsMultiSearchItem>): List<MovieDatabaseEntity> =
        data.map {
            with(it) {
                MovieDatabaseEntity(
                    id ?: 0,
                    name ?: unknown,
                    posterPath ?: unknown,
                    overview ?: unknown,
                    backdropPath,
                    type = 4,
                    date = firstAirDate,
                    rating = voteAverage,
                    mediaType = mediaType,
                    saveFor = "multiExplore"
                )
            }
        }

    fun mapMovieTrendingToEntity(data: List<MovieDatabaseEntity>): List<MovieEntity> =
        data.map {
            MovieEntity(
                it.id,
                it.title,
                it.posterPath,
                it.overview,
                it.backgroundPath,
                type = 0,
                date = it.date,
                rating = it.rating
            )
        }

    fun mapTrendingMovieToDatabaseEntity(data: List<ResultsMovieTrendingItem>): List<MovieDatabaseEntity> =
        data.map {
            with(it) {
                MovieDatabaseEntity(
                    id ?: 0,
                    title ?: unknown,
                    posterPath ?: unknown,
                    overview,
                    backdropPath,
                    type = 0,
                    date = releaseDate,
                    rating = voteAverage ?: 0.0,
                    saveFor = "trendingMovie"
                )
            }
        }

    fun mapTvTrendingToEntity(data: List<MovieDatabaseEntity>): List<MovieEntity> =
        data.map {
            MovieEntity(
                it.id,
                it.title,
                it.posterPath,
                it.overview,
                it.backgroundPath,
                type = 1,
                date = it.date,
                rating = it.rating
            )
        }

    fun mapTrendingTvToDatabaseEntity(data: List<ResultsTvTrendingItem>): List<MovieDatabaseEntity> =
        data.map {
            with(it) {
                MovieDatabaseEntity(
                    id ?: 0,
                    name ?: unknown,
                    posterPath ?: unknown,
                    overview,
                    backdropPath,
                    type = 1,
                    date = firstAirDate,
                    rating = voteAverage ?: 0.0,
                    saveFor = "trendingTv"
                )
            }
        }

    fun detailMovieToMovie(data: MovieDatabaseEntity): MovieEntity =
        with(data) {
            MovieEntity(
                id,
                title,
                posterPath,
                overview,
                backgroundPath,
                genres,
                0,
                status,
                tagLine,
                date,
                rating,
                mediaType
            )
        }

    fun detailMovieToDatabaseEntity(data: DetailMovieResponse): MovieDatabaseEntity =
        with(data) {
            MovieDatabaseEntity(
                id,
                title,
                posterPath,
                overview,
                backdropPath,
                genres?.map { it?.name ?: unknown },
                0,
                status,
                tagline,
                releaseDate,
                voteAverage,
                mediaType = "Movie"
            )
        }

    fun detailTvToMovie(data: MovieDatabaseEntity): MovieEntity =
        with(data) {
            MovieEntity(
                id,
                title,
                posterPath,
                overview,
                backgroundPath,
                genres,
                1,
                status,
                tagLine,
                date,
                rating,
                mediaType = mediaType
            )
        }

    fun detailTvToDatabaseEntity(data: DetailTvResponse): MovieDatabaseEntity =
        with(data) {
            MovieDatabaseEntity(
                id,
                name,
                posterPath,
                overview,
                backdropPath,
                genres?.map { it?.name ?: unknown },
                1,
                status,
                tagline,
                firstAirDate,
                voteAverage,
                mediaType = "TV"
            )
        }
}