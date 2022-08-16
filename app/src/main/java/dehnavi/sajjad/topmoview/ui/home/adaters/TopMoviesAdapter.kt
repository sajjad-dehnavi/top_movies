package dehnavi.sajjad.topmoview.ui.home.adaters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import dehnavi.sajjad.topmoview.databinding.ItemHomTopMoviesBinding
import dehnavi.sajjad.topmoview.model.home.ResponseMoviesList
import javax.inject.Inject

class TopMoviesAdapter @Inject constructor() : RecyclerView.Adapter<TopMoviesAdapter.ViewHolder>() {

    private lateinit var binding: ItemHomTopMoviesBinding

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        binding = ItemHomTopMoviesBinding.inflate(LayoutInflater.from(p0.context), p0, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, possition: Int) {
        holder.onBind(differ.currentList[possition])
        holder.setIsRecyclable(false)
    }

    override fun getItemCount(): Int =
        if (differ.currentList.size > 5) 5 else differ.currentList.size

    inner class ViewHolder constructor(binding: ItemHomTopMoviesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(item: ResponseMoviesList.Data) {
            binding.apply {
                txtMovie.text = item.title
                txtInfo.text = "${item.imdbRating} | ${item.country} | ${item.year}"
                imgPoster.load(item.poster) {
                    crossfade(true)
                    crossfade(800)
                }
            }
        }
    }

    private val differCallBack = object : DiffUtil.ItemCallback<ResponseMoviesList.Data>() {
        override fun areItemsTheSame(
            p0: ResponseMoviesList.Data,
            p1: ResponseMoviesList.Data
        ): Boolean {
            return p0.id == p1.id
        }

        override fun areContentsTheSame(
            p0: ResponseMoviesList.Data,
            p1: ResponseMoviesList.Data
        ): Boolean {
            return p0 == p1
        }

    }

    val differ = AsyncListDiffer(this, differCallBack)

}