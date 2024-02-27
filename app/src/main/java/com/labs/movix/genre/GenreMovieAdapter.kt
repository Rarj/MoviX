package com.labs.movix.genre

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingData
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.request.ImageRequest
import com.labs.data.BuildConfig
import com.labs.data.repository.movie.Movie
import com.labs.movix.databinding.GenreItemBinding

class MovieCallback: DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }

}

class GenreMovieAdapter(
    private val onClick: (movieId: Int) -> Unit,
) : PagingDataAdapter<Movie, GenreMovieAdapter.ViewHolder>(MovieCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = GenreItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), onClick = onClick)
    }



    inner class ViewHolder(private val binding: GenreItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie?, onClick: (movieId: Int) -> Unit) {
            binding.imagePoster.load(
                buildString {
                    append(BuildConfig.IMAGE_BASE_URL)
                    append(movie?.posterPath)
                }
            )

            binding.root.setOnClickListener {
                movie?.id?.let { it1 -> onClick.invoke(it1) }
            }
        }
    }
}