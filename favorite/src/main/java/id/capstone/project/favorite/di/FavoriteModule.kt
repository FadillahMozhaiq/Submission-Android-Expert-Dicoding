package id.capstone.project.favorite.di

import id.capstone.project.favorite.fragment.favoritemovie.FavoriteMovieViewModel
import id.capstone.project.favorite.fragment.favoritetvshow.FavoriteTvShowViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module {
    viewModel { FavoriteMovieViewModel(get()) }
    viewModel { FavoriteTvShowViewModel(get()) }
}