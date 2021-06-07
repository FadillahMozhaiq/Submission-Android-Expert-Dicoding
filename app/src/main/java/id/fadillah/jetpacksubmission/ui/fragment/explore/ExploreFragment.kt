package id.fadillah.jetpacksubmission.ui.fragment.explore

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import id.fadillah.jetpacksubmission.R
import id.fadillah.jetpacksubmission.data.model.MovieEntity
import id.fadillah.jetpacksubmission.databinding.FragmentExploreBinding
import id.fadillah.jetpacksubmission.ui.activity.detail.DetailActivity
import id.fadillah.jetpacksubmission.ui.adapter.MoviesAdapter
import id.fadillah.jetpacksubmission.ui.adapter.OnMovieItemClickListener
import id.fadillah.jetpacksubmission.vo.Status
import org.koin.android.viewmodel.ext.android.viewModel

class ExploreFragment : Fragment(), OnMovieItemClickListener,
    SearchView.OnQueryTextListener {

    private val exploreViewModel: ExploreViewModel by viewModel()
    private var _binding: FragmentExploreBinding? = null
    private val binding get() = _binding!!
    private var searchType: Int = 0
    private val moviesAdapter: MoviesAdapter by lazy { MoviesAdapter(this) }
    private var lastQuery: String = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExploreBinding.inflate(inflater, container, false)

        binding.svExplore.setOnQueryTextListener(this@ExploreFragment)
        setSearchVisibility(false, "")
        changedMode(true)
        showEmptyIndicator(false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        val explore = resources.getStringArray(R.array.explore)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, explore)
        binding.autoCompleteTextView.setAdapter(arrayAdapter)

        binding.autoCompleteTextView.setOnItemClickListener { _, _, position, _ ->
            searchType = position
            val hint = when (position) {
                0 -> getString(R.string.search_movie)
                1 -> getString(R.string.search_tv_show)
                2 -> getString(R.string.search_people)
                3 -> getString(R.string.search_company)
                4 -> getString(R.string.search_any)
                else -> "Unknown item"
            }
            setSearchVisibility(true, hint)
            changedMode(true)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvExplore.apply {
            layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
            adapter = moviesAdapter
            setHasFixedSize(true)
        }
    }

    private fun setSearchVisibility(show: Boolean, hint: String) {
        with(binding) {
            if (show) {
                cvSearch.visibility = View.VISIBLE
                svExplore.queryHint = hint

            } else {
                cvSearch.visibility = View.GONE
                svExplore.queryHint = hint
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        setSearchVisibility(false, "")
        changedMode(true)
        _binding = null
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        binding.svExplore.clearFocus()
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText != null) {
            if (newText.isEmpty()) {
                if (lastQuery.isNotEmpty())
                    search(lastQuery)
            } else {
                changedMode(false)
                lastQuery = newText
                search(newText)
            }
        }
        return true
    }

    private fun search(query: String) {
        when (searchType) {
            0 -> {
                exploreViewModel.getMovieExplore(query).observe(viewLifecycleOwner) { movies ->
                    when(movies.status) {
                        Status.LOADING -> {
                            showLoading(true)
                        }
                        Status.ERROR -> {
                            Toast.makeText(context, "Error: ${movies.message}", Toast.LENGTH_SHORT).show()
                            showLoading(false)
                            showEmptyIndicator(true)
                        }
                        Status.SUCCESS -> {
                            showLoading(false)
                            moviesAdapter.setMovies(movies.data)
                            if (movies.data.isNullOrEmpty())
                                showEmptyIndicator(true)
                            else
                                showEmptyIndicator(false)
                        }
                    }
                }
            }
            1 -> {
                exploreViewModel.getTvExplore(query).observe(viewLifecycleOwner) { movies ->
                    when(movies.status) {
                        Status.LOADING -> {
                            showLoading(true)
                        }
                        Status.ERROR -> {
                            Toast.makeText(context, "Error: ${movies.message}", Toast.LENGTH_SHORT).show()
                            showLoading(false)
                            showEmptyIndicator(true)
                        }
                        Status.SUCCESS -> {
                            showLoading(false)
                            moviesAdapter.setMovies(movies.data)
                            if (movies.data.isNullOrEmpty())
                                showEmptyIndicator(true)
                            else
                                showEmptyIndicator(false)
                        }
                    }
                }
            }
            2 -> {
                showLoading(true)
                exploreViewModel.getPersonExplore(query).observe(viewLifecycleOwner) { movies ->
                    when(movies.status) {
                        Status.LOADING -> {
                            showLoading(true)
                        }
                        Status.ERROR -> {
                            Toast.makeText(context, "Error: ${movies.message}", Toast.LENGTH_SHORT).show()
                            showLoading(false)
                            showEmptyIndicator(true)
                        }
                        Status.SUCCESS -> {
                            showLoading(false)
                            moviesAdapter.setMovies(movies.data)
                            if (movies.data.isNullOrEmpty())
                                showEmptyIndicator(true)
                            else
                                showEmptyIndicator(false)
                        }
                    }
                }
            }
            3 -> {
                showLoading(true)
                exploreViewModel.getCompanyExplore(query).observe(viewLifecycleOwner) { movies ->
                    when(movies.status) {
                        Status.LOADING -> {
                            showLoading(true)
                        }
                        Status.ERROR -> {
                            Toast.makeText(context, "Error: ${movies.message}", Toast.LENGTH_SHORT).show()
                            showLoading(false)
                            showEmptyIndicator(true)
                        }
                        Status.SUCCESS -> {
                            showLoading(false)
                            moviesAdapter.setMovies(movies.data)
                            if (movies.data.isNullOrEmpty())
                                showEmptyIndicator(true)
                            else
                                showEmptyIndicator(false)
                        }
                    }
                }
            }
            4 -> {
                showLoading(true)
                exploreViewModel.getMultiSearch(query).observe(viewLifecycleOwner) { movies ->
                    when(movies.status) {
                        Status.LOADING -> {
                            showLoading(true)
                        }
                        Status.ERROR -> {
                            Toast.makeText(context, "Error: ${movies.message}", Toast.LENGTH_SHORT).show()
                            showLoading(false)
                            showEmptyIndicator(true)
                        }
                        Status.SUCCESS -> {
                            showLoading(false)
                            moviesAdapter.setMovies(movies.data)
                            if (movies.data.isNullOrEmpty())
                                showEmptyIndicator(true)
                            else
                                showEmptyIndicator(false)
                        }
                    }
                }
            }
        }
    }

    private fun showEmptyIndicator(show: Boolean) {
        with(binding) {
            if (show) {
                emptyLottie.visibility = View.VISIBLE
            } else {
                emptyLottie.visibility = View.GONE
            }
        }
    }

    private fun showLoading(show: Boolean) {
        with(binding) {
            if (show) {
                loadingLottie.visibility = View.VISIBLE
                rvExplore.visibility = View.GONE
            } else {
                loadingLottie.visibility = View.GONE
                rvExplore.visibility = View.VISIBLE
            }
        }
    }

    private fun changedMode(changed: Boolean) {
        with(binding) {
            if (changed) {
                svExplore.setQuery("", false)
                svExplore.clearFocus()
                loadingLottie.visibility = View.GONE
                rvExplore.visibility = View.GONE
            } else {
                loadingLottie.visibility = View.VISIBLE
                rvExplore.visibility = View.VISIBLE
            }
        }
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