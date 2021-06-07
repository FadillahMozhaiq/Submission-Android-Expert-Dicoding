package id.fadillah.jetpacksubmission.ui.fragment.favoritemovie

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import id.fadillah.jetpacksubmission.R
import id.fadillah.jetpacksubmission.data.model.MovieEntity
import id.fadillah.jetpacksubmission.databinding.FavoriteMovieFragmentBinding
import id.fadillah.jetpacksubmission.ui.activity.detail.DetailActivity
import id.fadillah.jetpacksubmission.ui.adapter.MoviesAdapter
import id.fadillah.jetpacksubmission.ui.adapter.OnMovieItemClickListener
import org.koin.android.viewmodel.ext.android.viewModel

class FavoriteMovieFragment : Fragment(), OnMovieItemClickListener {

    companion object {
        fun newInstance() = FavoriteMovieFragment()
    }

    private val viewModel: FavoriteMovieViewModel by viewModel()
    private var _binding: FavoriteMovieFragmentBinding? = null
    private val binding get() = _binding!!
    private val moviesAdapter: MoviesAdapter by lazy { MoviesAdapter(this) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FavoriteMovieFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvFavoriteMovie.apply {
            adapter = moviesAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
        }
        showLoading(true)
        viewModel.getAllFavoriteMovie().observe(viewLifecycleOwner) {
            if (it.isNullOrEmpty())
                showEmpty(true)
            else {
                showEmpty(false)
                moviesAdapter.submitList(it)
            }
            showLoading(false)
        }
    }

    private fun showLoading(show: Boolean) {
        binding.pbFavMovies.visibility = if (show)
            View.VISIBLE
        else
            View.GONE
    }

    private fun showEmpty(show: Boolean) {
        with(binding) {
            if (show) {
                nothing.visibility = View.VISIBLE
                layoutFavMovies.visibility = View.GONE
            } else {
                nothing.visibility = View.GONE
                layoutFavMovies.visibility = View.VISIBLE
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