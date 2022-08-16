package dehnavi.sajjad.topmoview.ui.favorite.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import dehnavi.sajjad.topmoview.database.entity.MovieEntity
import dehnavi.sajjad.topmoview.databinding.ItemHomTopMoviesBinding
import dehnavi.sajjad.topmoview.databinding.ItemMovieBinding
import dehnavi.sajjad.topmoview.model.home.ResponseMoviesList
import javax.inject.Inject

class FavoriteAdapter @Inject constructor() : RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    private lateinit var binding: ItemMovieBinding

    private var moviesList = emptyList<MovieEntity>()
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        binding = ItemMovieBinding.inflate(LayoutInflater.from(p0.context), p0, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, possition: Int) {
        holder.onBind(moviesList[possition])
        holder.setIsRecyclable(false)
    }

    override fun getItemCount(): Int = moviesList.size

    inner class ViewHolder constructor(binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(item: MovieEntity) {
            binding.apply {
                txtName.text = item.title
                txtRate.text = item.imdbRating.toString()
                txtCountryMovie.text = item.country
                txtYear.text = item.year
                imgPoster.load(item.poster) {
                    crossfade(true)
                    crossfade(800)
                }

                root.setOnClickListener {
                    onItemClickListener?.let {
                        it(item)
                    }
                }
            }
        }
    }


    private var onItemClickListener: ((MovieEntity) -> Unit)? = null

    fun setOnItemClickListener(listener: ((MovieEntity) -> Unit)) {
        onItemClickListener = listener
    }

    fun setData(newItem: List<MovieEntity>) {
        val movieDiffUtil = MoviesDiffUtils(moviesList, newItem)
        val diffUtils = DiffUtil.calculateDiff(movieDiffUtil)
        moviesList = newItem
        diffUtils.dispatchUpdatesTo(this)
    }

    class MoviesDiffUtils(
        private val oldItems: List<MovieEntity>,
        private val newItem: List<MovieEntity>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldItems.size

        override fun getNewListSize(): Int = newItem.size

        override fun areItemsTheSame(p0: Int, p1: Int): Boolean {
            return oldItems[p0] === newItem[p1]
        }

        override fun areContentsTheSame(p0: Int, p1: Int): Boolean {
            return oldItems[p0] === newItem[p1]
        }

    }

}