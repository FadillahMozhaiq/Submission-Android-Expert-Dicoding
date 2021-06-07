package id.fadillah.jetpacksubmission.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.fadillah.jetpacksubmission.databinding.GenreItemBinding

class GenreAdapter: RecyclerView.Adapter<GenreAdapter.GenreViewHolder>() {
    private val listGenre = ArrayList<String>()

    fun setMovies(movies: List<String>?) {
        movies ?: return
        listGenre.apply {
            clear()
            addAll(movies)
        }
        notifyDataSetChanged()
    }

    inner class GenreViewHolder(private val binding: GenreItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(genre: String) {
            with(binding) {
                genreName.text = genre
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        val view =
            GenreItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GenreViewHolder(view)
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.bind(listGenre[position])
    }

    override fun getItemCount(): Int = listGenre.size
}