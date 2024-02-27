package com.labs.movix.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.labs.data.repository.movie.Movie
import com.labs.movix.databinding.MovieItemSearchBinding
import com.labs.movix.getPosterUrl

class SearchMovieCallback : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }

}

class SearchAdapter(
    private val onCLick: (movieId: Int) -> Unit,
) : PagingDataAdapter<Movie, SearchAdapter.ViewHolder>(SearchMovieCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            MovieItemSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), onCLick)
    }

    inner class ViewHolder(private val binding: MovieItemSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie?, onCLick: (movieId: Int) -> Unit) = with(binding) {
            movie?.let { item ->
                imagePoster.load(getPosterUrl(item.posterPath))
                root.setOnClickListener { onCLick.invoke(item.id) }
            }

        }
    }
}