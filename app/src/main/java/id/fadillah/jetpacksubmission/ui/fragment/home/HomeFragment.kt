package id.fadillah.jetpacksubmission.ui.fragment.home

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import id.fadillah.jetpacksubmission.R
import id.fadillah.jetpacksubmission.core.data.model.MovieEntity
import id.fadillah.jetpacksubmission.core.utils.helper.PreferenceHelper
import id.fadillah.jetpacksubmission.core.vo.Resource
import id.fadillah.jetpacksubmission.databinding.FragmentHomeBinding
import id.fadillah.jetpacksubmission.ui.activity.detail.DetailActivity
import id.fadillah.jetpacksubmission.ui.activity.detail.DetailActivity.Companion.EXTRA_DETAIL
import id.fadillah.jetpacksubmission.ui.adapter.*
import id.fadillah.jetpacksubmission.ui.fragment.customdialog.CustomDialog
import org.koin.android.viewmodel.ext.android.viewModel


class HomeFragment : Fragment(), OnMovieItemClickListener {

    private val homeViewModel: HomeViewModel by viewModel()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val imageSliderAdapter: SliderAdapter by lazy { SliderAdapter(this) }
    private val movieAdapter: MoviesAdapter by lazy { MoviesAdapter(this) }
    private val popularMovieAdapter: PopularMovieAdapter by lazy { PopularMovieAdapter(this) }
    private val topRatedMovieAdapter: TopRatedMovieAdapter by lazy { TopRatedMovieAdapter(this) }
    private val preferenceHelper: PreferenceHelper by lazy { PreferenceHelper(requireContext()) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        Inflate view
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        showDialog()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imageSlider.apply {
            setSliderAdapter(imageSliderAdapter)
            setIndicatorAnimation(IndicatorAnimationType.WORM)
            autoCycleDirection = 2
            indicatorSelectedColor = -1
            indicatorSelectedColor = -7829368
            scrollTimeInSec = 3
            isAutoCycle = true
            startAutoCycle()
        }

        binding.rvNowPlaying.apply {
            adapter = movieAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
        }

        binding.rvPopular.apply {
            adapter = popularMovieAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
        }

        binding.rvTopRated.apply {
            adapter = topRatedMovieAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
        }

        with(homeViewModel) {
            getUpcomingMovie().observe(viewLifecycleOwner) { movies ->
                if (movies != null) {
                    when (movies) {
                        is Resource.Loading -> {
                            showLoading(true)
                        }
                        is Resource.Error -> {
                            Toast.makeText(context, "Error: ${movies.message}", Toast.LENGTH_SHORT)
                                .show()
                            showLoading(false)
                        }
                        is Resource.Success -> {
                            if (movies.data.isNullOrEmpty()) {
                                Toast.makeText(
                                    context,
                                    "No Upcoming Movie!",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                imageSliderAdapter.setSliderData(movies.data)
                            }
                            showLoading(false)
                        }
                    }
                } else {
                    Toast.makeText(
                        context,
                        "No Upcoming Movie!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            getNowPlaying().observe(viewLifecycleOwner) { movies ->
                if (movies != null) {
                    when (movies) {
                        is Resource.Loading -> {
                            showLoading(true)
                        }
                        is Resource.Error -> {
                            Toast.makeText(context, "Error: ${movies.message}", Toast.LENGTH_SHORT)
                                .show()
                            showLoading(false)
                        }
                        is Resource.Success -> {
                            if (movies.data.isNullOrEmpty()) {
                                Toast.makeText(
                                    context,
                                    "No Playing Movie Right Now!",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                movieAdapter.setData(movies.data)
                            }
                            showLoading(false)
                        }
                    }
                } else {
                    Toast.makeText(
                        context,
                        "No Playing Movie Right Now!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            getPopular().observe(viewLifecycleOwner) { movies ->
                if (movies != null) {
                    when (movies) {
                        is Resource.Loading -> {
                            showLoading(true)
                        }
                        is Resource.Error -> {
                            Toast.makeText(context, "Error: ${movies.message}", Toast.LENGTH_SHORT)
                                .show()
                            showLoading(false)
                        }
                        is Resource.Success -> {
                            if (movies.data.isNullOrEmpty()) {
                                Toast.makeText(
                                    context,
                                    "No Popular Movie!",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                popularMovieAdapter.setData(movies.data)
                            }
                            showLoading(false)
                        }
                    }
                } else {
                    Toast.makeText(
                        context,
                        "No Popular Movie!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            getTopRated().observe(viewLifecycleOwner) { movies ->
                if (movies != null) {
                    when (movies) {
                        is Resource.Loading -> {
                            showLoading(true)
                        }
                        is Resource.Error -> {
                            Toast.makeText(context, "Error: ${movies.message}", Toast.LENGTH_SHORT)
                                .show()
                            showLoading(false)
                        }
                        is Resource.Success -> {
                            if (movies.data.isNullOrEmpty()) {
                                Toast.makeText(
                                    context,
                                    "No Top Rated Movie!",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                topRatedMovieAdapter.setData(movies.data)
                            }
                            showLoading(false)
                        }
                    }
                } else {
                    Toast.makeText(
                        context,
                        "No Top Rated Movie!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

    }

    private fun showDialog() {
        if (!preferenceHelper.isShowed()) {
            CustomDialog().show(childFragmentManager, "CustomDialog")
            preferenceHelper.setShowed()
        }
    }

    private fun showLoading(show: Boolean) {
        with(binding) {
            if (show) {
                layoutHome.visibility = View.GONE
                layoutShimmer.visibility = View.VISIBLE
            } else {
                layoutShimmer.visibility = View.GONE
                layoutHome.visibility = View.VISIBLE
            }
        }

    }

    override fun onMovieClicked(movie: MovieEntity, imageView: ImageView) {
        val intent = Intent(context, DetailActivity::class.java).apply {
            putExtra(EXTRA_DETAIL, movie)
        }
        val options = ActivityOptions.makeSceneTransitionAnimation(
            requireActivity(),
            imageView,
            getString(R.string.transition_name)
        )
        startActivity(intent, options.toBundle())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}