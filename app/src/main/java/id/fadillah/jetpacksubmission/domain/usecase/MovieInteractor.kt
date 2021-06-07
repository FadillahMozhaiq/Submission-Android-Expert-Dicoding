package id.fadillah.jetpacksubmission.domain.usecase

import androidx.lifecycle.LiveData
import id.fadillah.jetpacksubmission.data.model.MovieEntity
import id.fadillah.jetpacksubmission.domain.repository.IMovieRepository
import id.fadillah.jetpacksubmission.vo.Resource

class MovieInteractor (private val movieRepository: IMovieRepository) :
    MovieUseCase {
    override fun getUpcoming(): LiveData<Resource<List<MovieEntity>>> =
        movieRepository.getUpcoming()

    override fun getNowPlaying(): LiveData<Resource<List<MovieEntity>>> =
        movieRepository.getNowPlaying()

    override fun getPopular(): LiveData<Resource<List<MovieEntity>>> =
        movieRepository.getPopular()

    override fun getTopRated(): LiveData<Resource<List<MovieEntity>>> =
        movieRepository.getTopRated()

    override fun getMovieExplore(query: String): LiveData<Resource<List<MovieEntity>>> =
        movieRepository.getMovieExplore(query)

    override fun getTvExplore(query: String): LiveData<Resource<List<MovieEntity>>> =
        movieRepository.getTvExplore(query)

    override fun getPersonExplore(query: String): LiveData<Resource<List<MovieEntity>>> =
        movieRepository.getPersonExplore(query)

    override fun getCompanyExplore(query: String): LiveData<Resource<List<MovieEntity>>> =
        movieRepository.getCompanyExplore(query)

    override fun getMultiSearch(query: String): LiveData<Resource<List<MovieEntity>>> =
        movieRepository.getMultiSearch(query)

    override fun getTrendingMovie(): LiveData<Resource<List<MovieEntity>>> =
        movieRepository.getTrendingMovie()

    override fun getTrendingTv(): LiveData<Resource<List<MovieEntity>>> =
        movieRepository.getTrendingTv()

    override fun getDetailMovie(id: Int): LiveData<Resource<MovieEntity>> =
        movieRepository.getDetailMovie(id)

    override fun getDetailTv(id: Int): LiveData<Resource<MovieEntity>> =
        movieRepository.getDetailTv(id)
}