package id.fadillah.jetpacksubmission.di

import id.fadillah.jetpacksubmission.domain.usecase.MovieInteractor
import id.fadillah.jetpacksubmission.domain.usecase.MovieUseCase
import id.fadillah.jetpacksubmission.ui.activity.detail.DetailViewModel
import id.fadillah.jetpacksubmission.ui.fragment.explore.ExploreViewModel
import id.fadillah.jetpacksubmission.ui.fragment.favoritemovie.FavoriteMovieViewModel
import id.fadillah.jetpacksubmission.ui.fragment.favoritetvshow.FavoriteTvShowViewModel
import id.fadillah.jetpacksubmission.ui.fragment.home.HomeViewModel
import id.fadillah.jetpacksubmission.ui.fragment.movies.MoviesViewModel
import id.fadillah.jetpacksubmission.ui.fragment.tv.TvViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase> { MovieInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { MoviesViewModel(get()) }
    viewModel { DetailViewModel(get()) }
    viewModel { ExploreViewModel(get()) }
    viewModel { FavoriteMovieViewModel(get()) }
    viewModel { FavoriteTvShowViewModel(get()) }
    viewModel { TvViewModel(get()) }
}