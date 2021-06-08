package id.fadillah.jetpacksubmission.data

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import id.fadillah.jetpacksubmission.data.source.local.LocalDataSource
import id.fadillah.jetpacksubmission.data.source.local.model.MovieDatabaseEntity
import id.fadillah.jetpacksubmission.data.source.network.ApiResponse
import id.fadillah.jetpacksubmission.data.source.network.RemoteDataSource
import id.fadillah.jetpacksubmission.data.source.network.response.*
import id.fadillah.jetpacksubmission.domain.repository.IFakeMovieRepository
import id.fadillah.jetpacksubmission.utils.mapper.DataMapper
import id.fadillah.jetpacksubmission.vo.Resource

class FakeMovieRepository constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : IFakeMovieRepository {

    companion object {
        private val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
    }

    override fun getUpcoming(): LiveData<Resource<PagedList<MovieDatabaseEntity>>> =
        object : NetworkBoundResource<PagedList<MovieDatabaseEntity>, List<ResultsUpcomingItem>>() {
            public override fun loadFromDB(): LiveData<PagedList<MovieDatabaseEntity>> =
                LivePagedListBuilder(localDataSource.getAllUpcomingMovies(), config).build()

            override fun shouldFetch(data: PagedList<MovieDatabaseEntity>?): Boolean =
                true

            public override fun createCall(): LiveData<ApiResponse<List<ResultsUpcomingItem>>> =
                remoteDataSource.getUpcoming()

            override suspend fun saveCallResult(data: List<ResultsUpcomingItem>) =
                localDataSource.insertMovies(DataMapper.mapUpcomingToDatabaseEntity(data))
        }.asLiveData()


    override fun getNowPlaying(): LiveData<Resource<PagedList<MovieDatabaseEntity>>> =
        object :
            NetworkBoundResource<PagedList<MovieDatabaseEntity>, List<ResultsNowPlayingItem>>() {
            override fun loadFromDB(): LiveData<PagedList<MovieDatabaseEntity>> =
                LivePagedListBuilder(localDataSource.getAllNowPlayingMovies(), config).build()

            override fun shouldFetch(data: PagedList<MovieDatabaseEntity>?): Boolean =
                true

            override fun createCall(): LiveData<ApiResponse<List<ResultsNowPlayingItem>>> =
                remoteDataSource.getNowPlaying()

            override suspend fun saveCallResult(data: List<ResultsNowPlayingItem>) =
                localDataSource.insertMovies(DataMapper.mapNowPlayingToDatabaseEntity(data))
        }.asLiveData()

    override fun getPopular(): LiveData<Resource<PagedList<MovieDatabaseEntity>>> =
        object :
            NetworkBoundResource<PagedList<MovieDatabaseEntity>, List<ResultsPopularMovieItem>>() {
            override fun loadFromDB(): LiveData<PagedList<MovieDatabaseEntity>> =
                LivePagedListBuilder(localDataSource.getAllPopularMovies(), config).build()

            override fun shouldFetch(data: PagedList<MovieDatabaseEntity>?): Boolean =
                true

            override fun createCall(): LiveData<ApiResponse<List<ResultsPopularMovieItem>>> =
                remoteDataSource.getPopular()

            override suspend fun saveCallResult(data: List<ResultsPopularMovieItem>) =
                localDataSource.insertMovies(DataMapper.mapPopularToDatabaseEntity(data))
        }.asLiveData()

    override fun getTopRated(): LiveData<Resource<PagedList<MovieDatabaseEntity>>> =
        object :
            NetworkBoundResource<PagedList<MovieDatabaseEntity>, List<ResultsTopRatedMovieItem>>() {
            override fun loadFromDB(): LiveData<PagedList<MovieDatabaseEntity>> =
                LivePagedListBuilder(localDataSource.getAllTopRatedMovies(), config).build()

            override fun shouldFetch(data: PagedList<MovieDatabaseEntity>?): Boolean =
                true

            override fun createCall(): LiveData<ApiResponse<List<ResultsTopRatedMovieItem>>> =
                remoteDataSource.getTopRated()

            override suspend fun saveCallResult(data: List<ResultsTopRatedMovieItem>) =
                localDataSource.insertMovies(DataMapper.mapTopRatedToDatabaseEntity(data))
        }.asLiveData()

    override fun getMovieExplore(query: String): LiveData<Resource<PagedList<MovieDatabaseEntity>>> =
        object : NetworkBoundResource<PagedList<MovieDatabaseEntity>, List<ResultsItemExplore>>() {
            override fun loadFromDB(): LiveData<PagedList<MovieDatabaseEntity>> =
                LivePagedListBuilder(localDataSource.getMovieExplore(query), config).build()

            override fun shouldFetch(data: PagedList<MovieDatabaseEntity>?): Boolean =
                true

            override fun createCall(): LiveData<ApiResponse<List<ResultsItemExplore>>> =
                remoteDataSource.getMovieExplore(query)

            override suspend fun saveCallResult(data: List<ResultsItemExplore>) =
                localDataSource.insertMovies(DataMapper.mapMovieExploreToDatabaseEntity(data))
        }.asLiveData()

    override fun getTvExplore(query: String): LiveData<Resource<PagedList<MovieDatabaseEntity>>> =
        object :
            NetworkBoundResource<PagedList<MovieDatabaseEntity>, List<ResultsTvExploreItem>>() {
            override fun loadFromDB(): LiveData<PagedList<MovieDatabaseEntity>> =
                LivePagedListBuilder(localDataSource.getTvExplore(query), config).build()

            override fun shouldFetch(data: PagedList<MovieDatabaseEntity>?): Boolean =
                true

            override fun createCall(): LiveData<ApiResponse<List<ResultsTvExploreItem>>> =
                remoteDataSource.getTvExplore(query)

            override suspend fun saveCallResult(data: List<ResultsTvExploreItem>) =
                localDataSource.insertMovies(DataMapper.mapTvExploreToDatabaseEntity(data))
        }.asLiveData()

    override fun getPersonExplore(query: String): LiveData<Resource<PagedList<MovieDatabaseEntity>>> =
        object :
            NetworkBoundResource<PagedList<MovieDatabaseEntity>, List<ResultsPersonExploreItem>>() {
            override fun loadFromDB(): LiveData<PagedList<MovieDatabaseEntity>> =
                LivePagedListBuilder(localDataSource.getPersonExplore(query), config).build()

            override fun shouldFetch(data: PagedList<MovieDatabaseEntity>?): Boolean =
                true

            override fun createCall(): LiveData<ApiResponse<List<ResultsPersonExploreItem>>> =
                remoteDataSource.getPersonExplore(query)

            override suspend fun saveCallResult(data: List<ResultsPersonExploreItem>) =
                localDataSource.insertMovies(DataMapper.mapPersonExploreToDatabaseEntity(data))
        }.asLiveData()

    override fun getCompanyExplore(query: String): LiveData<Resource<PagedList<MovieDatabaseEntity>>> =
        object : NetworkBoundResource<PagedList<MovieDatabaseEntity>, List<ResultsItem>>() {
            override fun loadFromDB(): LiveData<PagedList<MovieDatabaseEntity>> =
                LivePagedListBuilder(localDataSource.getCompanyExplore(query), config).build()

            override fun shouldFetch(data: PagedList<MovieDatabaseEntity>?): Boolean =
                true

            override fun createCall(): LiveData<ApiResponse<List<ResultsItem>>> =
                remoteDataSource.getCompanyExplore(query)

            override suspend fun saveCallResult(data: List<ResultsItem>) =
                localDataSource.insertMovies(DataMapper.mapCompanyExploreToDatabaseEntity(data))
        }.asLiveData()

    override fun getMultiSearch(query: String): LiveData<Resource<PagedList<MovieDatabaseEntity>>> =
        object :
            NetworkBoundResource<PagedList<MovieDatabaseEntity>, List<ResultsMultiSearchItem>>() {
            override fun loadFromDB(): LiveData<PagedList<MovieDatabaseEntity>> =
                LivePagedListBuilder(localDataSource.getMultiSearch(query), config).build()

            override fun shouldFetch(data: PagedList<MovieDatabaseEntity>?): Boolean =
                true

            override fun createCall(): LiveData<ApiResponse<List<ResultsMultiSearchItem>>> =
                remoteDataSource.getMultiSearchExplore(query)

            override suspend fun saveCallResult(data: List<ResultsMultiSearchItem>) =
                localDataSource.insertMovies(DataMapper.mapMultiExploreToDatabaseEntity(data))
        }.asLiveData()

    override fun getTrendingMovie(): LiveData<Resource<PagedList<MovieDatabaseEntity>>> =
        object :
            NetworkBoundResource<PagedList<MovieDatabaseEntity>, List<ResultsMovieTrendingItem>>() {
            override fun loadFromDB(): LiveData<PagedList<MovieDatabaseEntity>> =
                LivePagedListBuilder(localDataSource.getAllTrendingMovies(), config).build()

            override fun shouldFetch(data: PagedList<MovieDatabaseEntity>?): Boolean =
                true

            override fun createCall(): LiveData<ApiResponse<List<ResultsMovieTrendingItem>>> =
                remoteDataSource.getMovieTrending()

            override suspend fun saveCallResult(data: List<ResultsMovieTrendingItem>) =
                localDataSource.insertMovies(DataMapper.mapTrendingMovieToDatabaseEntity(data))
        }.asLiveData()

    override fun getTrendingTv(): LiveData<Resource<PagedList<MovieDatabaseEntity>>> =
        object :
            NetworkBoundResource<PagedList<MovieDatabaseEntity>, List<ResultsTvTrendingItem>>() {
            override fun loadFromDB(): LiveData<PagedList<MovieDatabaseEntity>> =
                LivePagedListBuilder(localDataSource.getAllTrendingTv(), config).build()

            override fun shouldFetch(data: PagedList<MovieDatabaseEntity>?): Boolean =
                true

            override fun createCall(): LiveData<ApiResponse<List<ResultsTvTrendingItem>>> =
                remoteDataSource.getTvTrending()

            override suspend fun saveCallResult(data: List<ResultsTvTrendingItem>) =
                localDataSource.insertMovies(DataMapper.mapTrendingTvToDatabaseEntity(data))
        }.asLiveData()

    override fun getDetailMovie(id: Int): LiveData<Resource<MovieDatabaseEntity>> =
        object : NetworkBoundResource<MovieDatabaseEntity, DetailMovieResponse>() {
            override fun loadFromDB(): LiveData<MovieDatabaseEntity> =
                localDataSource.getMovieById(id)

            override fun shouldFetch(data: MovieDatabaseEntity?): Boolean =
                false

            override fun createCall(): LiveData<ApiResponse<DetailMovieResponse>> =
                remoteDataSource.getDetailMovie(id)

            override suspend fun saveCallResult(data: DetailMovieResponse) {
                localDataSource.updateMovie(DataMapper.detailMovieToDatabaseEntity(data))
            }
        }.asLiveData()

    override fun getDetailTv(id: Int): LiveData<Resource<MovieDatabaseEntity>> =
        object : NetworkBoundResource<MovieDatabaseEntity, DetailTvResponse>() {
            override fun loadFromDB(): LiveData<MovieDatabaseEntity> =
                localDataSource.getMovieById(id)

            override fun shouldFetch(data: MovieDatabaseEntity?): Boolean =
                false

            override fun createCall(): LiveData<ApiResponse<DetailTvResponse>> =
                remoteDataSource.getDetailTv(id)

            override suspend fun saveCallResult(data: DetailTvResponse) {
                localDataSource.updateMovie(DataMapper.detailTvToDatabaseEntity(data))
            }
        }.asLiveData()

    override fun isItFavorite(id: Int, type: Int): LiveData<Boolean> =
        localDataSource.checkIsFavorite(id, type)

    override suspend fun setFavorite(status: Boolean, id: Int, type: Int) =
        localDataSource.setFavorite(status, id, type)

    override fun getAllFavoriteMovie(): LiveData<PagedList<MovieDatabaseEntity>> =
        LivePagedListBuilder(localDataSource.getAllFavoriteMovies(), config).build()

    override fun getAllFavoriteTv(): LiveData<PagedList<MovieDatabaseEntity>> =
        LivePagedListBuilder(localDataSource.getAllFavoriteTv(), config).build()
}