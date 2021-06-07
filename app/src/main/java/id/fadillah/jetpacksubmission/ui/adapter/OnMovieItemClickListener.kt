package id.fadillah.jetpacksubmission.ui.adapter

import android.widget.ImageView
import id.fadillah.jetpacksubmission.data.model.MovieEntity

interface OnMovieItemClickListener {
    fun onMovieClicked(movie: MovieEntity, imageView: ImageView)
}