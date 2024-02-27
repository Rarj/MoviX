package com.labs.movix.genre

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.labs.data.repository.genre.Genre
import com.labs.movix.R
import com.labs.movix.databinding.FilterItemBinding

class FilterAdapter(
    private val onSelected: (Genre) -> Unit,
) : RecyclerView.Adapter<FilterAdapter.ViewHolder>() {

    private var selectedGenreId: Int? = null
    private val diffUtil = object : DiffUtil.ItemCallback<Genre>() {
        override fun areItemsTheSame(oldItem: Genre, newItem: Genre): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Genre, newItem: Genre): Boolean {
            return oldItem == newItem
        }

    }

    private val asyncListDiffer = AsyncListDiffer(this, diffUtil)

    fun updateGenre(dataResponse: List<Genre>, selectedGenreId: Int?) {
        this.selectedGenreId = selectedGenreId
        asyncListDiffer.submitList(dataResponse)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = FilterItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return asyncListDiffer.currentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(asyncListDiffer.currentList[position], onSelected = onSelected)
    }


    inner class ViewHolder(private val binding: FilterItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(genre: Genre, onSelected: (Genre) -> Unit) {
            binding.textFilter.text = genre.name

            this@FilterAdapter.selectedGenreId?.let {
                if (it == genre.id) {
                    binding.textFilter.setTextColor(
                        ContextCompat.getColorStateList(
                            binding.root.context,
                            R.color.white
                        )
                    )
                }
            }

            binding.root.setOnClickListener {
                onSelected.invoke(genre)
            }
        }
    }
}