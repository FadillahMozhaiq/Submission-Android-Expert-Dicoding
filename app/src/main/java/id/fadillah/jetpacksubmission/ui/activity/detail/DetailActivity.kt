package id.fadillah.jetpacksubmission.ui.activity.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import id.fadillah.jetpacksubmission.R
import id.fadillah.jetpacksubmission.data.model.MovieEntity
import id.fadillah.jetpacksubmission.databinding.ActivityDetailBinding
import id.fadillah.jetpacksubmission.ui.adapter.GenreAdapter
import id.fadillah.jetpacksubmission.utils.helper.ConstantHelper.IMAGE_URL
import id.fadillah.jetpacksubmission.utils.helper.ConstantHelper.IMAGE_URL_ORIGINAL
import id.fadillah.jetpacksubmission.utils.helper.ImageHelper
import id.fadillah.jetpacksubmission.utils.mapper.DataMapper
import id.fadillah.jetpacksubmission.vo.Status
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_DETAIL = "extra_detail_Data"
    }

    private lateinit var binding: ActivityDetailBinding
    private val viewModel: DetailViewModel by viewModel()
    private val genreAdapter: GenreAdapter by lazy { GenreAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = "Detail Movie"
        }
        setContentView(binding.root)

        val movie = intent.getParcelableExtra<MovieEntity>(EXTRA_DETAIL) as MovieEntity

        initView(movie)

        when (movie.type) {
            0 -> getDataMovie(movie.id)
            1 -> getDataTv(movie.id)
            else -> {
                setView(movie)
                showLoading(false)
            }
        }
    }

    private fun initView(movie: MovieEntity) {
        with(binding) {
            ImageHelper.getImage(detailMovieImgShimmer, IMAGE_URL + movie.posterPath)
            ImageHelper.getImage(detailMovieImg, IMAGE_URL + movie.posterPath)
            rvGenre.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = genreAdapter
                setHasFixedSize(true)
            }
        }
    }

    private fun getDataTv(id: Int) {
        viewModel.getDetailTv(id).observe(this) { movie ->
            when(movie.status) {
                Status.LOADING -> {
                    showLoading(true)
                }
                Status.ERROR -> {
                    Toast.makeText(this, "Error: ${movie.message}", Toast.LENGTH_SHORT).show()
                    showLoading(false)
                }
                Status.SUCCESS -> {
                    showLoading(false)
                    if (movie.data == null) {
                        Toast.makeText(
                            this,
                            "No Detail Tv Show With This ID!",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        genreAdapter.setMovies(movie.data?.genres)
                        setView(movie.data as MovieEntity)
                    }
                }
            }
        }
    }

    private fun getDataMovie(id: Int) {
        viewModel.getDetailMovie(id).observe(this) { movie ->
            when(movie.status) {
                Status.LOADING -> {
                    showLoading(true)
                }
                Status.ERROR -> {
                    Toast.makeText(this, "Error: ${movie.message}", Toast.LENGTH_SHORT).show()
                    showLoading(false)
                }
                Status.SUCCESS -> {
                    showLoading(false)
                    if (movie.data == null) {
                        Toast.makeText(
                            this,
                            "No Detail Movie With This ID!",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        genreAdapter.setMovies(movie.data?.genres)
                        setView(movie.data as MovieEntity)
                    }
                }
            }
        }
    }

    private fun setView(data: MovieEntity) {
        with(binding) {
            with(data) {
                ImageHelper.getImage(detailMovieImg, IMAGE_URL + posterPath)
                detailMovieTitle.text = title
                detailMovieType.text = when (type) {
                    0 -> "Movie"
                    1 -> "TV"
                    2 -> "People"
                    3 -> "Company"
                    4 -> mediaType ?: "Any"
                    else -> mediaType ?: "Unknown"
                }
                detailMovieDesc.text = if (overview.isNotEmpty()) overview else getString(R.string.simple_text)
                detailMovieRating.text = rating.toString()
                if (backgroundPath != null) {
                    ImageHelper.getImage(detailMovieCover, IMAGE_URL_ORIGINAL + backgroundPath)
                }
                if (tagLine?.isNotEmpty() == true) {
                    detailMovieTagline.text = tagLine
                }
                if (status != null) {
                    detailMovieRelease.text = status.take(10)
                }
                if (date != null) {
                    detailMovieDate.text = DataMapper.convertDate(date)
                }
            }
        }
    }

    private fun showLoading(show: Boolean) {
        with(binding) {
            if (show) {
                layoutDetail.visibility = View.GONE
                layoutShimmerDetail.visibility = View.VISIBLE
            } else {
                layoutDetail.visibility = View.VISIBLE
                layoutShimmerDetail.visibility = View.GONE
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}