package dehnavi.sajjad.topmoview.ui.home.adaters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import dehnavi.sajjad.topmoview.databinding.ItemHomeGenreBinding
import dehnavi.sajjad.topmoview.model.home.ResponseGenreList
import javax.inject.Inject

class GenreAdapter @Inject constructor() : RecyclerView.Adapter<GenreAdapter.ViewHolder>() {

    private lateinit var binding: ItemHomeGenreBinding

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        binding = ItemHomeGenreBinding.inflate(LayoutInflater.from(p0.context), p0, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, possition: Int) {
        holder.onBind(differ.currentList[possition])
        holder.setIsRecyclable(false)
    }

    override fun getItemCount(): Int = differ.currentList.size

    inner class ViewHolder constructor(binding: ItemHomeGenreBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(item: ResponseGenreList.ResponseGenreListItem) {
            binding.apply {
                genre.text = item.name
            }
        }
    }

    private val differCallBack =
        object : DiffUtil.ItemCallback<ResponseGenreList.ResponseGenreListItem>() {
            override fun areItemsTheSame(
                p0: ResponseGenreList.ResponseGenreListItem,
                p1: ResponseGenreList.ResponseGenreListItem
            ): Boolean {
                return p0.id == p1.id
            }

            override fun areContentsTheSame(
                p0: ResponseGenreList.ResponseGenreListItem,
                p1: ResponseGenreList.ResponseGenreListItem
            ): Boolean {
                return p0 == p1
            }

        }

    val differ = AsyncListDiffer(this, differCallBack)

}