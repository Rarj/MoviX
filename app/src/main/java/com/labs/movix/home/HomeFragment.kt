package com.labs.movix.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.labs.data.Status.ERROR
import com.labs.data.Status.LOADING
import com.labs.data.Status.SUCCESS
import com.labs.data.repository.genre.Genre
import com.labs.movix.R
import com.labs.movix.databinding.FragmentHomeBinding
import com.labs.movix.genre.FilterBottomSheet
import com.labs.movix.genre.GenreMovieAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val viewModel: HomeViewModel by viewModels()

    private lateinit var adapter: GenreMovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = GenreMovieAdapter { movieId ->
            findNavController().navigate(
                R.id.detail_movie_page,
                bundleOf(
                    "movie_id" to movieId
                )
            )
        }
        binding.recyclerViewGenre.apply {
            adapter = this@HomeFragment.adapter
            layoutManager = GridLayoutManager(context, 2)
        }

        loadMovie()

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.genre.collectLatest { state ->
                when (state?.status) {
                    LOADING -> println(state.status.name)
                    SUCCESS -> {
                        setGenreTitle(viewModel.getSelectedGenre().name)
                        println(state.data.toString())
                    }

                    ERROR -> println(state.status.name)
                    null -> println("First Initialization!")
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.movies.collectLatest { state ->
                state?.let {
                    adapter.submitData(it)
                    binding.recyclerViewGenre.smoothScrollToPosition(0)
                }
            }
        }

        initUIListener()
    }

    private fun initUIListener() {
        binding.buttonFilter.setOnClickListener {
            val filterPage = FilterBottomSheet { genre ->
                setGenreTitle(genre.name)
                loadMovie(genre)
            }
            val bundle = bundleOf(
                "selected_genre_id" to viewModel.getSelectedGenre().id
            )
            filterPage.arguments = bundle
            filterPage.show(childFragmentManager, "FILTER_PAGE")
        }

        binding.buttonSearch.setOnClickListener {
            val bundle = bundleOf(
                "selected_genre_id" to viewModel.getSelectedGenre().id
            )
            findNavController().navigate(R.id.search_page, bundle)
        }
    }

    private fun loadMovie(genre: Genre? = null) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getMovie(genre)
        }
    }

    private fun setGenreTitle(genreName: String) {
        val genreTitle = buildString {
            append("Movie by ")
            append(genreName)
            append(" Genre")
        }
        binding.textGenre.text = genreTitle
    }
}
