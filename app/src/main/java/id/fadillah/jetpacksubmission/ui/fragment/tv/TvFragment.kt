package id.fadillah.jetpacksubmission.ui.fragment.tv

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import id.fadillah.jetpacksubmission.R
import id.fadillah.jetpacksubmission.core.data.model.MovieEntity
import id.fadillah.jetpacksubmission.core.vo.Resource
import id.fadillah.jetpacksubmission.databinding.FragmentTvBinding
import id.fadillah.jetpacksubmission.ui.activity.detail.DetailActivity
import id.fadillah.jetpacksubmission.ui.adapter.MoviesAdapter
import id.fadillah.jetpacksubmission.ui.adapter.OnMovieItemClickListener
import org.koin.android.viewmodel.ext.android.viewModel

class TvFragment : Fragment(), OnMovieItemClickListener {

    private var _binding: FragmentTvBinding? = null
    private val binding get() = _binding!!
    private val viewModel: TvViewModel by viewModel()
    private val moviesAdapter: MoviesAdapter by lazy { MoviesAdapter(this) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentTvBinding.inflate(inflater, container, false)

        binding.rvTvShow.apply {
            layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
            adapter = moviesAdapter
            setHasFixedSize(true)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getTrendingTv().observe(viewLifecycleOwner)  { movies ->
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
                        showLoading(false)
                        if (movies.data.isNullOrEmpty()) {
                            Toast.makeText(
                                context,
                                "No Trending Tv Show!",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            moviesAdapter.setData(movies.data)
                        }
                        showLoading(false)
                    }
                }
            } else {
                Toast.makeText(
                    context,
                    "No Trending Tv Show!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun showLoading(show: Boolean) {
        with(binding) {
            if (show) {
                shimmerLayout.visibility = View.VISIBLE
                rvTvShow.visibility = View.GONE
            } else {
                rvTvShow.visibility = View.VISIBLE
                shimmerLayout.visibility = View.GONE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onMovieClicked(movie: MovieEntity, imageView: ImageView) {
        val intent = Intent(context, DetailActivity::class.java).apply {
            putExtra(DetailActivity.EXTRA_DETAIL, movie)
        }
        val options = ActivityOptions.makeSceneTransitionAnimation(
            requireActivity(),
            imageView,
            getString(R.string.transition_name)
        )
        startActivity(intent, options.toBundle())
    }
}