package id.fadillah.jetpacksubmission.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import com.smarteist.autoimageslider.SliderViewAdapter
import id.fadillah.jetpacksubmission.core.data.model.MovieEntity
import id.fadillah.jetpacksubmission.core.utils.helper.ConstantHelper.IMAGE_URL
import id.fadillah.jetpacksubmission.core.utils.helper.ImageHelper
import id.fadillah.jetpacksubmission.databinding.ImageSliderItemBinding

class SliderAdapter(private val movieItemClickListener: OnMovieItemClickListener) :
    SliderViewAdapter<SliderAdapter.SliderViewHolder>() {
    private val listMovie = ArrayList<MovieEntity>()

    fun setSliderData(movies: List<MovieEntity>?) {
        movies ?: return
        listMovie.clear()
        listMovie.addAll(movies)
        notifyDataSetChanged()
    }

    inner class SliderViewHolder(private val binding: ImageSliderItemBinding) :
        SliderViewAdapter.ViewHolder(binding.root) {
        fun bind(movie: MovieEntity) {
            with(binding) {
                tvAutoImageSlider.apply {
                    text = movie.title.take(100)
                    textSize = 16f
                }
                ImageHelper.getImage(ivAutoImageSlider, IMAGE_URL + movie.posterPath)
                sliderContainer.setOnClickListener {
                    Toast.makeText(itemView.context, movie.title, Toast.LENGTH_SHORT).show()
                }
                ivAutoImageSlider.setOnClickListener {
                    movieItemClickListener.onMovieClicked(
                        movie,
                        ivAutoImageSlider
                    )
                }
            }
        }

    }

    override fun getCount(): Int = listMovie.size

    override fun onCreateViewHolder(parent: ViewGroup): SliderViewHolder {
        val view =
            ImageSliderItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SliderViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: SliderViewHolder, position: Int) {
        viewHolder.bind(listMovie[position])
    }
}