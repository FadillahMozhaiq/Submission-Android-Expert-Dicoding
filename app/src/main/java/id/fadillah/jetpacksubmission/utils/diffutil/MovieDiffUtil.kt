package id.fadillah.jetpacksubmission.utils.diffutil

import androidx.recyclerview.widget.DiffUtil
import id.fadillah.jetpacksubmission.data.model.MovieEntity

class MovieDiffUtil(
    private val oldList: List<MovieEntity>,
    private val newList: List<MovieEntity>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        when {
            oldList[oldItemPosition].id != newList[newItemPosition].id -> false
            oldList[oldItemPosition].title != newList[newItemPosition].title -> false
            oldList[oldItemPosition].posterPath != newList[newItemPosition].posterPath -> false
            oldList[oldItemPosition].overview != newList[newItemPosition].overview -> false
            oldList[oldItemPosition].backgroundPath != newList[newItemPosition].backgroundPath -> false
            oldList[oldItemPosition].genres != newList[newItemPosition].genres -> false
            oldList[oldItemPosition].status != newList[newItemPosition].status -> false
            oldList[oldItemPosition].tagLine != newList[newItemPosition].tagLine -> false
            oldList[oldItemPosition].date != newList[newItemPosition].date -> false
            oldList[oldItemPosition].rating != newList[newItemPosition].rating -> false
            oldList[oldItemPosition].mediaType != newList[newItemPosition].mediaType -> false
            else -> true
        }
}