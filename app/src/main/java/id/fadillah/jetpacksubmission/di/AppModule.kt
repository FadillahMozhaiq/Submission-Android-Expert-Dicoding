package id.fadillah.jetpacksubmission.di

import id.fadillah.jetpacksubmission.core.domain.usecase.MovieInteractor
import id.fadillah.jetpacksubmission.core.domain.usecase.MovieUseCase
import id.fadillah.jetpacksubmission.ui.activity.detail.DetailViewModel
import id.fadillah.jetpacksubmission.ui.fragment.explore.ExploreViewModel
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
    viewModel { TvViewModel(get()) }
}