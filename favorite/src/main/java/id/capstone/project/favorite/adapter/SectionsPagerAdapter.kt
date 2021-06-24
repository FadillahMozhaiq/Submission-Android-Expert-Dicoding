package id.capstone.project.favorite.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import id.capstone.project.favorite.fragment.favoritemovie.FavoriteMovieFragment
import id.capstone.project.favorite.fragment.favoritetvshow.FavoriteTvShowFragment

class SectionsPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment =
        when (position) {
            0 -> FavoriteMovieFragment.newInstance()
            1 -> FavoriteTvShowFragment.newInstance()
            else -> Fragment()
        }
}