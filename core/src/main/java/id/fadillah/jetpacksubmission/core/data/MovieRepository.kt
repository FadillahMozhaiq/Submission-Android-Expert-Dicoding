package id.fadillah.jetpacksubmission.core.data

import android.content.Context
import id.fadillah.jetpacksubmission.core.data.model.MovieEntity
import id.fadillah.jetpacksubmission.core.data.source.local.LocalDataSource
import id.fadillah.jetpacksubmission.core.data.source.network.ApiResponse
import id.fadillah.jetpacksubmission.core.data.source.network.RemoteDataSource
import id.fadillah.jetpacksubmission.core.data.source.network.response.*
import id.fadillah.jetpacksubmission.core.domain.repository.IMovieRepository
import id.fadillah.jetpacksubmission.core.utils.helper.InternetConnectionHelper.isOnline
import id.fadillah.jetpacksubmission.core.utils.mapper.DataMapper
import id.fadillah.jetpacksubmission.core.utils.mapper.DataMapper.detailMovieToDatabaseEntity
import id.fadillah.jetpacksubmission.core.utils.mapper.DataMapper.detailTvToDatabaseEntity
import id.fadillah.jetpacksubmission.core.utils.mapper.DataMapper.mapCompanyExploreToDatabaseEntity
import id.fadillah.jetpacksubmission.core.utils.mapper.DataMapper.mapMultiExploreToDatabaseEntity
import id.fadillah.jetpacksubmission.core.utils.mapper.DataMapper.mapPersonExploreToDatabaseEntity
import id.fadillah.jetpacksubmission.core.utils.mapper.DataMapper.mapPopularToDatabaseEntity
import id.fadillah.jetpacksubmission.core.utils.mapper.DataMapper.mapTrendingMovieToDatabaseEntity
import id.fadillah.jetpacksubmission.core.utils.mapper.DataMapper.mapTrendingTvToDatabaseEntity
import id.fadillah.jetpacksubmission.core.utils.mapper.DataMapper.mapTvExploreToDatabaseEntity
import id.fadillah.jetpacksubmission.core.vo.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val context: Context
) : IMovieRepository {

    override fun getUpcoming(): Flow<Resource<List<MovieEntity>>> =
        object : NetworkBoundResource<List<MovieEntity>, List<ResultsUpcomingItem>>() {
            public override fun loadFromDB(): Flow<List<MovieEntity>> =
                localDataSource.getAllUpcomingMovies().map {
                    DataMapper.mapUpcomingToEntity(it)
                }


            override fun shouldFetch(data: List<MovieEntity>?): Boolean =
                isOnline(context) || data.isNullOrEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<ResultsUpcomingItem>>> =
                remoteDataSource.getUpcoming()

            override suspend fun saveCallResult(data: List<ResultsUpcomingItem>) =
                localDataSource.insertMovies(DataMapper.mapUpcomingToDatabaseEntity(data))
        }.asFlow()


    override fun getNowPlaying(): Flow<Resource<List<MovieEntity>>> =
        object : NetworkBoundResource<List<MovieEntity>, List<ResultsNowPlayingItem>>() {
            override fun loadFromDB(): Flow<List<MovieEntity>> =
                localDataSource.getAllNowPlayingMovies().map {
                    DataMapper.mapNowPlayingToEntity(it)
                }

            override fun shouldFetch(data: List<MovieEntity>?): Boolean =
                isOnline(context) || data.isNullOrEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<ResultsNowPlayingItem>>> =
                remoteDataSource.getNowPlaying()

            override suspend fun saveCallResult(data: List<ResultsNowPlayingItem>) =
                localDataSource.insertMovies(DataMapper.mapNowPlayingToDatabaseEntity(data))
        }.asFlow()

    override fun getPopular(): Flow<Resource<List<MovieEntity>>> =
        object : NetworkBoundResource<List<MovieEntity>, List<ResultsPopularMovieItem>>() {
            override fun loadFromDB(): Flow<List<MovieEntity>> =
                localDataSource.getAllPopularMovies().map {
                    DataMapper.mapPopularToEntity(it)
                }

            override fun shouldFetch(data: List<MovieEntity>?): Boolean =
                isOnline(context) || data.isNullOrEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<ResultsPopularMovieItem>>> =
                remoteDataSource.getPopular()

            override suspend fun saveCallResult(data: List<ResultsPopularMovieItem>) =
                localDataSource.insertMovies(mapPopularToDatabaseEntity(data))
        }.asFlow()

    override fun getTopRated(): Flow<Resource<List<MovieEntity>>> =
        object : NetworkBoundResource<List<MovieEntity>, List<ResultsTopRatedMovieItem>>() {
            override fun loadFromDB(): Flow<List<MovieEntity>> =
                localDataSource.getAllTopRatedMovies().map {
                    DataMapper.mapTopRatedToEntity(it)

                }

            override fun shouldFetch(data: List<MovieEntity>?): Boolean =
                isOnline(context) || data.isNullOrEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<ResultsTopRatedMovieItem>>> =
                remoteDataSource.getTopRated()

            override suspend fun saveCallResult(data: List<ResultsTopRatedMovieItem>) =
                localDataSource.insertMovies(DataMapper.mapTopRatedToDatabaseEntity(data))
        }.asFlow()

    override fun getMovieExplore(query: String): Flow<Resource<List<MovieEntity>>> =
        object : NetworkBoundResource<List<MovieEntity>, List<ResultsItemExplore>>() {
            override fun loadFromDB(): Flow<List<MovieEntity>> =
                localDataSource.getMovieExplore(query).map {
                    DataMapper.mapMovieExploreToEntity(it)
                }

            override fun shouldFetch(data: List<MovieEntity>?): Boolean =
                isOnline(context) || data.isNullOrEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<ResultsItemExplore>>> =
                remoteDataSource.getMovieExplore(query)

            override suspend fun saveCallResult(data: List<ResultsItemExplore>) =
                localDataSource.insertMovies(DataMapper.mapMovieExploreToDatabaseEntity(data))
        }.asFlow()

    override fun getTvExplore(query: String): Flow<Resource<List<MovieEntity>>> =
        object : NetworkBoundResource<List<MovieEntity>, List<ResultsTvExploreItem>>() {
            override fun loadFromDB(): Flow<List<MovieEntity>> =
                localDataSource.getTvExplore(query).map {
                    DataMapper.mapTvExploreToEntity(it)
                }

            override fun shouldFetch(data: List<MovieEntity>?): Boolean =
                isOnline(context) || data.isNullOrEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<ResultsTvExploreItem>>> =
                remoteDataSource.getTvExplore(query)

            override suspend fun saveCallResult(data: List<ResultsTvExploreItem>) =
                localDataSource.insertMovies(mapTvExploreToDatabaseEntity(data))
        }.asFlow()

    override fun getPersonExplore(query: String): Flow<Resource<List<MovieEntity>>> =
        object : NetworkBoundResource<List<MovieEntity>, List<ResultsPersonExploreItem>>() {
            override fun loadFromDB(): Flow<List<MovieEntity>> =
                localDataSource.getPersonExplore(query).map {
                    DataMapper.mapPersonExploreToEntity(it)
                }

            override fun shouldFetch(data: List<MovieEntity>?): Boolean =
                isOnline(context) || data.isNullOrEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<ResultsPersonExploreItem>>> =
                remoteDataSource.getPersonExplore(query)

            override suspend fun saveCallResult(data: List<ResultsPersonExploreItem>) =
                localDataSource.insertMovies(mapPersonExploreToDatabaseEntity(data))
        }.asFlow()

    override fun getCompanyExplore(query: String): Flow<Resource<List<MovieEntity>>> =
        object : NetworkBoundResource<List<MovieEntity>, List<ResultsItem>>() {
            override fun loadFromDB(): Flow<List<MovieEntity>> =
                localDataSource.getCompanyExplore(query).map {
                    DataMapper.mapCompanyExploreToEntity(it)
                }

            override fun shouldFetch(data: List<MovieEntity>?): Boolean =
                isOnline(context) || data.isNullOrEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<ResultsItem>>> =
                remoteDataSource.getCompanyExplore(query)

            override suspend fun saveCallResult(data: List<ResultsItem>) =
                localDataSource.insertMovies(mapCompanyExploreToDatabaseEntity(data))
        }.asFlow()

    override fun getMultiSearch(query: String): Flow<Resource<List<MovieEntity>>> =
        object : NetworkBoundResource<List<MovieEntity>, List<ResultsMultiSearchItem>>() {
            override fun loadFromDB(): Flow<List<MovieEntity>> =
                localDataSource.getMultiSearch(query).map {
                    DataMapper.mapMultiExploreToEntity(it)
                }

            override fun shouldFetch(data: List<MovieEntity>?): Boolean =
                isOnline(context) || data.isNullOrEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<ResultsMultiSearchItem>>> =
                remoteDataSource.getMultiSearchExplore(query)

            override suspend fun saveCallResult(data: List<ResultsMultiSearchItem>) =
                localDataSource.insertMovies(mapMultiExploreToDatabaseEntity(data))
        }.asFlow()

    override fun getTrendingMovie(): Flow<Resource<List<MovieEntity>>> =
        object : NetworkBoundResource<List<MovieEntity>, List<ResultsMovieTrendingItem>>() {
            override fun loadFromDB(): Flow<List<MovieEntity>> =
                localDataSource.getAllTrendingMovies().map {
                    DataMapper.mapMovieTrendingToEntity(it)
                }

            override fun shouldFetch(data: List<MovieEntity>?): Boolean =
                isOnline(context) || data.isNullOrEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<ResultsMovieTrendingItem>>> =
                remoteDataSource.getMovieTrending()

            override suspend fun saveCallResult(data: List<ResultsMovieTrendingItem>) =
                localDataSource.insertMovies(mapTrendingMovieToDatabaseEntity(data))
        }.asFlow()

    override fun getTrendingTv(): Flow<Resource<List<MovieEntity>>> =
        object : NetworkBoundResource<List<MovieEntity>, List<ResultsTvTrendingItem>>() {
            override fun loadFromDB(): Flow<List<MovieEntity>> =
                localDataSource.getAllTrendingTv().map {
                    DataMapper.mapTvTrendingToEntity(it)
                }

            override fun shouldFetch(data: List<MovieEntity>?): Boolean =
                isOnline(context) || data.isNullOrEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<ResultsTvTrendingItem>>> =
                remoteDataSource.getTvTrending()

            override suspend fun saveCallResult(data: List<ResultsTvTrendingItem>) =
                localDataSource.insertMovies(mapTrendingTvToDatabaseEntity(data))
        }.asFlow()

    override fun getDetailMovie(id: Int): Flow<Resource<MovieEntity>> =
        object : NetworkBoundResource<MovieEntity, DetailMovieResponse>() {
            override fun loadFromDB(): Flow<MovieEntity> =
                localDataSource.getMovieById(id).map {
                    DataMapper.detailMovieToMovie(it)
                }

            override fun shouldFetch(data: MovieEntity?): Boolean =
                isOnline(context) || data == null

            override suspend fun createCall(): Flow<ApiResponse<DetailMovieResponse>> =
                remoteDataSource.getDetailMovie(id)

            override suspend fun saveCallResult(data: DetailMovieResponse) {
                localDataSource.updateMovie(detailMovieToDatabaseEntity(data))
            }
        }.asFlow()

    override fun getDetailTv(id: Int): Flow<Resource<MovieEntity>> =
        object : NetworkBoundResource<MovieEntity, DetailTvResponse>() {
            override fun loadFromDB(): Flow<MovieEntity> =
                localDataSource.getMovieById(id).map {
                    DataMapper.detailTvToMovie(it)
                }

            override fun shouldFetch(data: MovieEntity?): Boolean =
                isOnline(context) || data == null

            override suspend fun createCall(): Flow<ApiResponse<DetailTvResponse>> =
                remoteDataSource.getDetailTv(id)

            override suspend fun saveCallResult(data: DetailTvResponse) {
                localDataSource.updateMovie(detailTvToDatabaseEntity(data))
            }
        }.asFlow()

    override fun isItFavorite(id: Int, type: Int): Flow<Boolean> =
        localDataSource.checkIsFavorite(id, type)

    override suspend fun setFavorite(status: Boolean, id: Int, type: Int) =
        localDataSource.setFavorite(status, id, type)

    override fun getAllFavoriteMovie(): Flow<List<MovieEntity>> =
        localDataSource.getAllFavoriteMovies().map {
            DataMapper.mapMovieTrendingToEntity(it)
        }

    override fun getAllFavoriteTv(): Flow<List<MovieEntity>> =
        localDataSource.getAllFavoriteTv().map {
            DataMapper.mapTvTrendingToEntity(it)
        }
}