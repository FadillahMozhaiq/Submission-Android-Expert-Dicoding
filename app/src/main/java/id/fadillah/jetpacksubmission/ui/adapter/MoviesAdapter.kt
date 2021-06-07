package id.fadillah.jetpacksubmission.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.fadillah.jetpacksubmission.data.model.MovieEntity
import id.fadillah.jetpacksubmission.databinding.ItemMovieBinding
import id.fadillah.jetpacksubmission.utils.helper.ConstantHelper.IMAGE_URL
import id.fadillah.jetpacksubmission.utils.helper.ImageHelper

class MoviesAdapter(private val movieItemClickListener: OnMovieItemClickListener) :
    RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {
    private val listMovie = ArrayList<MovieEntity>()

    fun setMovies(movies: List<MovieEntity>?) {
        movies ?: return
        listMovie.apply {
            clear()
            addAll(movies)
        }
        notifyDataSetChanged()
    }

    inner class MovieViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieEntity) {
            with(binding) {
                itemMovieTitle.text = movie.title
                ImageHelper.getImage(itemMovieImg, IMAGE_URL + movie.posterPath)
                root.setOnClickListener {
                    movieItemClickListener.onMovieClicked(
                        movie,
                        itemMovieImg
                    )
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view =
            ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(listMovie[position])
    }

    override fun getItemCount(): Int = listMovie.size
}