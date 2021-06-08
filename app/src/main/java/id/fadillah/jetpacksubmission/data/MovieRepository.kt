package id.fadillah.jetpacksubmission.data

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import id.fadillah.jetpacksubmission.data.model.MovieEntity
import id.fadillah.jetpacksubmission.data.source.local.LocalDataSource
import id.fadillah.jetpacksubmission.data.source.network.ApiResponse
import id.fadillah.jetpacksubmission.data.source.network.RemoteDataSource
import id.fadillah.jetpacksubmission.data.source.network.response.*
import id.fadillah.jetpacksubmission.domain.repository.IMovieRepository
import id.fadillah.jetpacksubmission.utils.helper.InternetConnectionHelper.isOnline
import id.fadillah.jetpacksubmission.utils.mapper.DataMapper
import id.fadillah.jetpacksubmission.utils.mapper.DataMapper.detailMovieToDatabaseEntity
import id.fadillah.jetpacksubmission.utils.mapper.DataMapper.detailTvToDatabaseEntity
import id.fadillah.jetpacksubmission.utils.mapper.DataMapper.mapCompanyExploreToDatabaseEntity
import id.fadillah.jetpacksubmission.utils.mapper.DataMapper.mapMultiExploreToDatabaseEntity
import id.fadillah.jetpacksubmission.utils.mapper.DataMapper.mapPersonExploreToDatabaseEntity
import id.fadillah.jetpacksubmission.utils.mapper.DataMapper.mapPopularToDatabaseEntity
import id.fadillah.jetpacksubmission.utils.mapper.DataMapper.mapTrendingMovieToDatabaseEntity
import id.fadillah.jetpacksubmission.utils.mapper.DataMapper.mapTrendingTvToDatabaseEntity
import id.fadillah.jetpacksubmission.utils.mapper.DataMapper.mapTvExploreToDatabaseEntity
import id.fadillah.jetpacksubmission.vo.Resource

class  MovieRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val context: Context
) : IMovieRepository {

    companion object {
        private val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
    }

    override fun getUpcoming(): LiveData<Resource<PagedList<MovieEntity>>> =
        object : NetworkBoundResource<PagedList<MovieEntity>, List<ResultsUpcomingItem>>() {
            public override fun loadFromDB(): LiveData<PagedList<MovieEntity>> =
                LivePagedListBuilder(localDataSource.getAllUpcomingMovies().mapByPage {
                    DataMapper.mapUpcomingToEntity(it)
                }, config).build()

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean =
                isOnline(context) || data.isNullOrEmpty()

            public override fun createCall(): LiveData<ApiResponse<List<ResultsUpcomingItem>>> =
                remoteDataSource.getUpcoming()

            override suspend fun saveCallResult(data: List<ResultsUpcomingItem>) =
                localDataSource.insertMovies(DataMapper.mapUpcomingToDatabaseEntity(data))
        }.asLiveData()


    override fun getNowPlaying(): LiveData<Resource<PagedList<MovieEntity>>> =
        object : NetworkBoundResource<PagedList<MovieEntity>, List<ResultsNowPlayingItem>>() {
            override fun loadFromDB(): LiveData<PagedList<MovieEntity>> =
                LivePagedListBuilder(localDataSource.getAllNowPlayingMovies().mapByPage {
                    DataMapper.mapNowPlayingToEntity(it)
                }, config).build()

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean =
                isOnline(context) || data.isNullOrEmpty()

            override fun createCall(): LiveData<ApiResponse<List<ResultsNowPlayingItem>>> =
                remoteDataSource.getNowPlaying()

            override suspend fun saveCallResult(data: List<ResultsNowPlayingItem>) =
                localDataSource.insertMovies(DataMapper.mapNowPlayingToDatabaseEntity(data))
        }.asLiveData()

    override fun getPopular(): LiveData<Resource<PagedList<MovieEntity>>> =
        object : NetworkBoundResource<PagedList<MovieEntity>, List<ResultsPopularMovieItem>>() {
            override fun loadFromDB(): LiveData<PagedList<MovieEntity>> =
                LivePagedListBuilder(localDataSource.getAllPopularMovies().mapByPage {
                    DataMapper.mapPopularToEntity(it)
                }, config).build()

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean =
                isOnline(context) || data.isNullOrEmpty()

            override fun createCall(): LiveData<ApiResponse<List<ResultsPopularMovieItem>>> =
                remoteDataSource.getPopular()

            override suspend fun saveCallResult(data: List<ResultsPopularMovieItem>) =
                localDataSource.insertMovies(mapPopularToDatabaseEntity(data))
        }.asLiveData()

    override fun getTopRated(): LiveData<Resource<PagedList<MovieEntity>>> =
        object : NetworkBoundResource<PagedList<MovieEntity>, List<ResultsTopRatedMovieItem>>() {
            override fun loadFromDB(): LiveData<PagedList<MovieEntity>> =
                LivePagedListBuilder(localDataSource.getAllTopRatedMovies().mapByPage {
                    DataMapper.mapTopRatedToEntity(it)
                }, config).build()

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean =
                isOnline(context) || data.isNullOrEmpty()

            override fun createCall(): LiveData<ApiResponse<List<ResultsTopRatedMovieItem>>> =
                remoteDataSource.getTopRated()

            override suspend fun saveCallResult(data: List<ResultsTopRatedMovieItem>) =
                localDataSource.insertMovies(DataMapper.mapTopRatedToDatabaseEntity(data))
        }.asLiveData()

    override fun getMovieExplore(query: String): LiveData<Resource<PagedList<MovieEntity>>> =
        object : NetworkBoundResource<PagedList<MovieEntity>, List<ResultsItemExplore>>() {
            override fun loadFromDB(): LiveData<PagedList<MovieEntity>> =
                LivePagedListBuilder(localDataSource.getMovieExplore(query).mapByPage {
                    DataMapper.mapMovieExploreToEntity(it)
                }, config).build()

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean =
                isOnline(context) || data.isNullOrEmpty()

            override fun createCall(): LiveData<ApiResponse<List<ResultsItemExplore>>> =
                remoteDataSource.getMovieExplore(query)

            override suspend fun saveCallResult(data: List<ResultsItemExplore>) =
                localDataSource.insertMovies(DataMapper.mapMovieExploreToDatabaseEntity(data))
        }.asLiveData()

    override fun getTvExplore(query: String): LiveData<Resource<PagedList<MovieEntity>>> =
        object : NetworkBoundResource<PagedList<MovieEntity>, List<ResultsTvExploreItem>>() {
            override fun loadFromDB(): LiveData<PagedList<MovieEntity>> =
                LivePagedListBuilder(localDataSource.getTvExplore(query).mapByPage {
                    DataMapper.mapTvExploreToEntity(it)
                }, config).build()

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean =
                isOnline(context) || data.isNullOrEmpty()

            override fun createCall(): LiveData<ApiResponse<List<ResultsTvExploreItem>>> =
                remoteDataSource.getTvExplore(query)

            override suspend fun saveCallResult(data: List<ResultsTvExploreItem>) =
                localDataSource.insertMovies(mapTvExploreToDatabaseEntity(data))
        }.asLiveData()

    override fun getPersonExplore(query: String): LiveData<Resource<PagedList<MovieEntity>>> =
        object : NetworkBoundResource<PagedList<MovieEntity>, List<ResultsPersonExploreItem>>() {
            override fun loadFromDB(): LiveData<PagedList<MovieEntity>> =
                LivePagedListBuilder(localDataSource.getPersonExplore(query).mapByPage {
                    DataMapper.mapPersonExploreToEntity(it)
                }, config).build()

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean =
                isOnline(context) || data.isNullOrEmpty()

            override fun createCall(): LiveData<ApiResponse<List<ResultsPersonExploreItem>>> =
                remoteDataSource.getPersonExplore(query)

            override suspend fun saveCallResult(data: List<ResultsPersonExploreItem>) =
                localDataSource.insertMovies(mapPersonExploreToDatabaseEntity(data))
        }.asLiveData()

    override fun getCompanyExplore(query: String): LiveData<Resource<PagedList<MovieEntity>>> =
        object : NetworkBoundResource<PagedList<MovieEntity>, List<ResultsItem>>() {
            override fun loadFromDB(): LiveData<PagedList<MovieEntity>> =
                LivePagedListBuilder(localDataSource.getCompanyExplore(query).mapByPage {
                    DataMapper.mapCompanyExploreToEntity(it)
                }, config).build()

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean =
                isOnline(context) || data.isNullOrEmpty()

            override fun createCall(): LiveData<ApiResponse<List<ResultsItem>>> =
                remoteDataSource.getCompanyExplore(query)

            override suspend fun saveCallResult(data: List<ResultsItem>) =
                localDataSource.insertMovies(mapCompanyExploreToDatabaseEntity(data))
        }.asLiveData()

    override fun getMultiSearch(query: String): LiveData<Resource<PagedList<MovieEntity>>> =
        object : NetworkBoundResource<PagedList<MovieEntity>, List<ResultsMultiSearchItem>>() {
            override fun loadFromDB(): LiveData<PagedList<MovieEntity>> =
                LivePagedListBuilder(localDataSource.getMultiSearch(query).mapByPage {
                    DataMapper.mapMultiExploreToEntity(it)
                }, config).build()

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean =
                isOnline(context) || data.isNullOrEmpty()

            override fun createCall(): LiveData<ApiResponse<List<ResultsMultiSearchItem>>> =
                remoteDataSource.getMultiSearchExplore(query)

            override suspend fun saveCallResult(data: List<ResultsMultiSearchItem>) =
                localDataSource.insertMovies(mapMultiExploreToDatabaseEntity(data))
        }.asLiveData()

    override fun getTrendingMovie(): LiveData<Resource<PagedList<MovieEntity>>> =
        object : NetworkBoundResource<PagedList<MovieEntity>, List<ResultsMovieTrendingItem>>() {
            override fun loadFromDB(): LiveData<PagedList<MovieEntity>> =
                LivePagedListBuilder(localDataSource.getAllTrendingMovies().mapByPage {
                    DataMapper.mapMovieTrendingToEntity(it)
                }, config).build()

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean =
                isOnline(context) || data.isNullOrEmpty()

            override fun createCall(): LiveData<ApiResponse<List<ResultsMovieTrendingItem>>> =
                remoteDataSource.getMovieTrending()

            override suspend fun saveCallResult(data: List<ResultsMovieTrendingItem>) =
                localDataSource.insertMovies(mapTrendingMovieToDatabaseEntity(data))
        }.asLiveData()

    override fun getTrendingTv(): LiveData<Resource<PagedList<MovieEntity>>> =
        object : NetworkBoundResource<PagedList<MovieEntity>, List<ResultsTvTrendingItem>>() {
            override fun loadFromDB(): LiveData<PagedList<MovieEntity>> =
                LivePagedListBuilder(localDataSource.getAllTrendingTv().mapByPage {
                    DataMapper.mapTvTrendingToEntity(it)
                }, config).build()

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean =
                isOnline(context) || data.isNullOrEmpty()

            override fun createCall(): LiveData<ApiResponse<List<ResultsTvTrendingItem>>> =
                remoteDataSource.getTvTrending()

            override suspend fun saveCallResult(data: List<ResultsTvTrendingItem>) =
                localDataSource.insertMovies(mapTrendingTvToDatabaseEntity(data))
        }.asLiveData()

    override fun getDetailMovie(id: Int): LiveData<Resource<MovieEntity>> =
        object : NetworkBoundResource<MovieEntity, DetailMovieResponse>() {
            override fun loadFromDB(): LiveData<MovieEntity> =
                Transformations.map(localDataSource.getMovieById(id)) {
                    DataMapper.detailMovieToMovie(it)
                }

            override fun shouldFetch(data: MovieEntity?): Boolean =
                isOnline(context) || data == null

            override fun createCall(): LiveData<ApiResponse<DetailMovieResponse>> =
                remoteDataSource.getDetailMovie(id)

            override suspend fun saveCallResult(data: DetailMovieResponse) {
                localDataSource.updateMovie(detailMovieToDatabaseEntity(data))
            }
        }.asLiveData()

    override fun getDetailTv(id: Int): LiveData<Resource<MovieEntity>> =
        object : NetworkBoundResource<MovieEntity, DetailTvResponse>() {
            override fun loadFromDB(): LiveData<MovieEntity> =
                Transformations.map(localDataSource.getMovieById(id)) {
                    DataMapper.detailTvToMovie(it)
                }

            override fun shouldFetch(data: MovieEntity?): Boolean =
                isOnline(context) || data == null

            override fun createCall(): LiveData<ApiResponse<DetailTvResponse>> =
                remoteDataSource.getDetailTv(id)

            override suspend fun saveCallResult(data: DetailTvResponse) {
                localDataSource.updateMovie(detailTvToDatabaseEntity(data))
            }
        }.asLiveData()

    override fun isItFavorite(id: Int, type: Int): LiveData<Boolean> = localDataSource.checkIsFavorite(id, type)

    override suspend fun setFavorite(status: Boolean, id: Int, type: Int) =
        localDataSource.setFavorite(status, id, type)

    override fun getAllFavoriteMovie(): LiveData<PagedList<MovieEntity>> =
        LivePagedListBuilder(localDataSource.getAllFavoriteMovies().mapByPage {
            DataMapper.mapMovieTrendingToEntity(it)
        }, config).build()

    override fun getAllFavoriteTv(): LiveData<PagedList<MovieEntity>> =
        LivePagedListBuilder(localDataSource.getAllFavoriteTv().mapByPage {
            DataMapper.mapTvTrendingToEntity(it)
        }, config).build()
}