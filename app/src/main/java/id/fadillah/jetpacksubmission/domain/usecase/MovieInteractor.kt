package id.fadillah.jetpacksubmission.domain.usecase

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import id.fadillah.jetpacksubmission.data.model.MovieEntity
import id.fadillah.jetpacksubmission.domain.repository.IMovieRepository
import id.fadillah.jetpacksubmission.vo.Resource

class MovieInteractor(private val movieRepository: IMovieRepository) :
    MovieUseCase {
    override fun getUpcoming(): LiveData<Resource<PagedList<MovieEntity>>> =
        movieRepository.getUpcoming()

    override fun getNowPlaying(): LiveData<Resource<PagedList<MovieEntity>>> =
        movieRepository.getNowPlaying()

    override fun getPopular(): LiveData<Resource<PagedList<MovieEntity>>> =
        movieRepository.getPopular()

    override fun getTopRated(): LiveData<Resource<PagedList<MovieEntity>>> =
        movieRepository.getTopRated()

    override fun getMovieExplore(query: String): LiveData<Resource<PagedList<MovieEntity>>> =
        movieRepository.getMovieExplore(query)

    override fun getTvExplore(query: String): LiveData<Resource<PagedList<MovieEntity>>> =
        movieRepository.getTvExplore(query)

    override fun getPersonExplore(query: String): LiveData<Resource<PagedList<MovieEntity>>> =
        movieRepository.getPersonExplore(query)

    override fun getCompanyExplore(query: String): LiveData<Resource<PagedList<MovieEntity>>> =
        movieRepository.getCompanyExplore(query)

    override fun getMultiSearch(query: String): LiveData<Resource<PagedList<MovieEntity>>> =
        movieRepository.getMultiSearch(query)

    override fun getTrendingMovie(): LiveData<Resource<PagedList<MovieEntity>>> =
        movieRepository.getTrendingMovie()

    override fun getTrendingTv(): LiveData<Resource<PagedList<MovieEntity>>> =
        movieRepository.getTrendingTv()

    override fun getDetailMovie(id: Int): LiveData<Resource<MovieEntity>> =
        movieRepository.getDetailMovie(id)

    override fun getDetailTv(id: Int): LiveData<Resource<MovieEntity>> =
        movieRepository.getDetailTv(id)

    override fun isItFavorite(id: Int, type: Int): LiveData<Boolean> =
        movieRepository.isItFavorite(id, type)

    override suspend fun setFavorite(status: Boolean, id: Int, type: Int) =
        movieRepository.setFavorite(status, id, type)

    override fun getAllFavoriteMovie(): LiveData<PagedList<MovieEntity>> =
        movieRepository.getAllFavoriteMovie()

    override fun getAllFavoriteTv(): LiveData<PagedList<MovieEntity>> =
        movieRepository.getAllFavoriteTv()
}