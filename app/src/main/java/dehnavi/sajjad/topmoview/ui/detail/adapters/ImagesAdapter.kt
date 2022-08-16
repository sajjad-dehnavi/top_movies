package dehnavi.sajjad.topmoview.ui.detail.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import dehnavi.sajjad.topmoview.databinding.ItemDetailImagesBinding
import dehnavi.sajjad.topmoview.databinding.ItemHomeGenreBinding
import dehnavi.sajjad.topmoview.model.home.ResponseGenreList
import javax.inject.Inject

class ImagesAdapter @Inject constructor() : RecyclerView.Adapter<ImagesAdapter.ViewHolder>() {

    private lateinit var binding: ItemDetailImagesBinding

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        binding = ItemDetailImagesBinding.inflate(LayoutInflater.from(p0.context), p0, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, possition: Int) {
        holder.onBind(differ.currentList[possition] )
        holder.setIsRecyclable(false)
    }

    override fun getItemCount(): Int = differ.currentList.size

    inner class ViewHolder constructor(binding: ItemDetailImagesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(item: String) {
            binding.apply {
                img.load(item) {
                    crossfade(true)
                }
            }
        }
    }

    private val differCallBack =
        object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(
                p0: String,
                p1: String
            ): Boolean {
                return p0 == p1
            }

            override fun areContentsTheSame(
                p0: String,
                p1: String
            ): Boolean {
                return p0 == p1
            }

        }

    val differ = AsyncListDiffer(this, differCallBack)

}