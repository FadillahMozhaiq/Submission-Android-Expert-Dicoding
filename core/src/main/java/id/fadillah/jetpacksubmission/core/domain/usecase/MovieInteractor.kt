package id.fadillah.jetpacksubmission.core.domain.usecase

import id.fadillah.jetpacksubmission.core.data.model.MovieEntity
import id.fadillah.jetpacksubmission.core.domain.repository.IMovieRepository
import id.fadillah.jetpacksubmission.core.vo.Resource
import kotlinx.coroutines.flow.Flow

class MovieInteractor(private val movieRepository: IMovieRepository) :
    MovieUseCase {
    override fun getUpcoming(): Flow<Resource<List<MovieEntity>>> =
        movieRepository.getUpcoming()

    override fun getNowPlaying(): Flow<Resource<List<MovieEntity>>> =
        movieRepository.getNowPlaying()

    override fun getPopular(): Flow<Resource<List<MovieEntity>>> =
        movieRepository.getPopular()

    override fun getTopRated(): Flow<Resource<List<MovieEntity>>> =
        movieRepository.getTopRated()

    override fun getMovieExplore(query: String): Flow<Resource<List<MovieEntity>>> =
        movieRepository.getMovieExplore(query)

    override fun getTvExplore(query: String): Flow<Resource<List<MovieEntity>>> =
        movieRepository.getTvExplore(query)

    override fun getPersonExplore(query: String): Flow<Resource<List<MovieEntity>>> =
        movieRepository.getPersonExplore(query)

    override fun getCompanyExplore(query: String): Flow<Resource<List<MovieEntity>>> =
        movieRepository.getCompanyExplore(query)

    override fun getMultiSearch(query: String): Flow<Resource<List<MovieEntity>>> =
        movieRepository.getMultiSearch(query)

    override fun getTrendingMovie(): Flow<Resource<List<MovieEntity>>> =
        movieRepository.getTrendingMovie()

    override fun getTrendingTv(): Flow<Resource<List<MovieEntity>>> =
        movieRepository.getTrendingTv()

    override fun getDetailMovie(id: Int): Flow<Resource<MovieEntity>> =
        movieRepository.getDetailMovie(id)

    override fun getDetailTv(id: Int): Flow<Resource<MovieEntity>> =
        movieRepository.getDetailTv(id)

    override fun isItFavorite(id: Int, type: Int): Flow<Boolean> =
        movieRepository.isItFavorite(id, type)

    override suspend fun setFavorite(status: Boolean, id: Int, type: Int) =
        movieRepository.setFavorite(status, id, type)

    override fun getAllFavoriteMovie(): Flow<List<MovieEntity>> =
        movieRepository.getAllFavoriteMovie()

    override fun getAllFavoriteTv(): Flow<List<MovieEntity>> =
        movieRepository.getAllFavoriteTv()
}