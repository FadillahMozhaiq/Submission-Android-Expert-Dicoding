package id.fadillah.jetpacksubmission.ui.activity.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import id.fadillah.jetpacksubmission.R
import id.fadillah.jetpacksubmission.core.data.model.MovieEntity
import id.fadillah.jetpacksubmission.core.utils.helper.ConstantHelper.IMAGE_URL
import id.fadillah.jetpacksubmission.core.utils.helper.ConstantHelper.IMAGE_URL_ORIGINAL
import id.fadillah.jetpacksubmission.core.utils.helper.ImageHelper
import id.fadillah.jetpacksubmission.core.utils.mapper.DataMapper
import id.fadillah.jetpacksubmission.core.vo.Resource
import id.fadillah.jetpacksubmission.databinding.ActivityDetailBinding
import id.fadillah.jetpacksubmission.ui.adapter.GenreAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_DETAIL = "extra_detail_Data"
    }

    private lateinit var binding: ActivityDetailBinding
    private val viewModel: DetailViewModel by viewModel()
    private val genreAdapter: GenreAdapter by lazy { GenreAdapter() }
    private var isFavorite = false

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
            0 -> {
                getDataMovie(movie.id)
                showFab(true)
            }
            1 -> {
                getDataTv(movie.id)
                showFab(true)
            }
            else -> {
                showFab(false)
                setView(movie)
                showLoading(false)
            }
        }
    }

    private fun showFab(show: Boolean) {
        binding.fabFavorite.visibility = if (show)
            View.VISIBLE
        else View.GONE

    }

    private fun initView(movie: MovieEntity) {
        supportActionBar?.title = movie.title
        with(binding) {
            ImageHelper.getImage(detailMovieImgShimmer, IMAGE_URL + movie.posterPath)
            ImageHelper.getImage(detailMovieImg, IMAGE_URL + movie.posterPath)
            rvGenre.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = genreAdapter
                setHasFixedSize(true)
            }
            fabFavorite.setOnClickListener {
                isFavorite = !isFavorite
                viewModel.setFavorite(isFavorite, movie.id, movie.type)
                setFavorite(isFavorite)
                if (isFavorite) {
                    Toast.makeText(
                        this@DetailActivity,
                        "Success Add to Favorite!",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                } else
                    Toast.makeText(
                        this@DetailActivity,
                        "Success Remove From Favorite!",
                        Toast.LENGTH_SHORT
                    )
                        .show()
            }
            viewModel.checkFavorite(movie.id, movie.type)
                .observe(this@DetailActivity) { isFavorite ->
                    setFavorite(isFavorite)
                    this@DetailActivity.isFavorite = isFavorite
                }
        }
    }

    private fun getDataTv(id: Int) {
        viewModel.getDetailTv(id).observe(this) { movie ->
            if (movie != null) {
                when (movie) {
                    is Resource.Loading -> {
                        showLoading(true)
                    }
                    is Resource.Error -> {
                        Toast.makeText(this, "Error: ${movie.message}", Toast.LENGTH_SHORT).show()
                        showLoading(false)
                    }
                    is Resource.Success -> {
                        showLoading(false)
                        if (movie.data == null) {
                            Toast.makeText(
                                this,
                                "No Detail Tv Show With This ID!",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            genreAdapter.setMovies(movie.data?.genres)
                            setView(movie.data)
                        }
                        showLoading(false)
                    }
                }
            } else {
                Toast.makeText(
                    this,
                    "No Detail Tv Show With This ID!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun setFavorite(favorite: Boolean) {
        with(binding) {
            if (favorite)
                fabFavorite.setImageResource(R.drawable.ic_favorite)
            else
                fabFavorite.setImageResource(R.drawable.ic_unfavorite)
        }
    }

    private fun getDataMovie(id: Int) {
        viewModel.getDetailMovie(id).observe(this) { movie ->
            if (movie != null) {
                when (movie) {
                    is Resource.Loading -> {
                        showLoading(true)
                    }
                    is Resource.Error -> {
                        Toast.makeText(this, "Error: ${movie.message}", Toast.LENGTH_SHORT).show()
                        showLoading(false)
                    }
                    is Resource.Success -> {
                        showLoading(false)
                        if (movie.data == null) {
                            Toast.makeText(
                                this,
                                "No Detail Movie With This ID!",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            genreAdapter.setMovies(movie.data?.genres)
                            setView(movie.data)
                        }
                    }
                }
            } else {
                Toast.makeText(
                    this,
                    "No Detail Movie With This ID!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun setView(data: MovieEntity?) {
        data ?: return
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
                detailMovieDesc.text =
                    if (overview.isNotEmpty()) overview else getString(R.string.simple_text)
                detailMovieRating.text = rating.toString()
                if (backgroundPath != null) {
                    ImageHelper.getImage(detailMovieCover, IMAGE_URL_ORIGINAL + backgroundPath)
                }
                if (tagLine?.isNotEmpty() == true) {
                    detailMovieTagline.text = tagLine
                }
                if (status != null) {
                    detailMovieRelease.text = status!!.take(10)
                }
                if (date != null) {
                    detailMovieDate.text = DataMapper.convertDate(date!!)
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