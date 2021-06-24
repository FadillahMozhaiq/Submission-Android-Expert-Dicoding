package id.fadillah.jetpacksubmission.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import id.fadillah.jetpacksubmission.core.data.model.MovieEntity
import id.fadillah.jetpacksubmission.core.utils.diffutil.MovieDiffUtil
import id.fadillah.jetpacksubmission.core.utils.helper.ConstantHelper.IMAGE_URL
import id.fadillah.jetpacksubmission.core.utils.helper.ImageHelper
import id.fadillah.jetpacksubmission.databinding.ItemMovieBinding

class MoviesAdapter(private val movieItemClickListener: OnMovieItemClickListener) :
    RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    private var listMovie = emptyList<MovieEntity>()

    fun setData(newData: List<MovieEntity>?) {
        newData ?: return
        val movieDiffUtil = MovieDiffUtil(listMovie, newData)
        val diffResult = DiffUtil.calculateDiff(movieDiffUtil)
        listMovie = newData
        diffResult.dispatchUpdatesTo(this)
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
        listMovie[position].let { holder.bind(it) }
    }

    override fun getItemCount(): Int = listMovie.size

}