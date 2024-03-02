package com.labs.movix.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.unit.dp
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.labs.data.BuildConfig
import com.labs.data.Status.ERROR
import com.labs.data.Status.LOADING
import com.labs.data.Status.SUCCESS
import com.labs.data.repository.movie.Movie
import com.labs.movix.R
import com.labs.movix.databinding.FragmentHomeBinding
import com.labs.movix.genre.FilterBottomSheet
import com.labs.uikit.PosterUiKit
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val viewModel: HomeViewModel by viewModels()

    private lateinit var movieLazyPagingItems: LazyPagingItems<Movie>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.composePreview.apply {
            setContent {
                setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
                setContent {
                    MoviesUI()
                }
            }
        }

        return binding.root
    }

    @Composable
    private fun MoviesUI() {
        movieLazyPagingItems = viewModel.movieFlow.collectAsLazyPagingItems()
        LazyVerticalGrid(
            columns = GridCells.Fixed(count = 2),
            modifier = Modifier.padding(start = 16.dp, end = 16.dp)
        ) {
            items(movieLazyPagingItems.itemCount) { index ->
                movieLazyPagingItems[index]?.posterPath?.let { url ->
                    PosterUiKit(url = buildString {
                        append(BuildConfig.IMAGE_BASE_URL)
                        append(url)
                    }) {
                        findNavController().navigate(
                            R.id.detail_movie_page,
                            bundleOf(
                                "movie_id" to movieLazyPagingItems[index]?.id
                            )
                        )
                    }
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(state = Lifecycle.State.CREATED) {
                viewModel.getGenres()
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.genre.collectLatest { state ->
                when (state?.status) {
                    LOADING -> println(state.status.name)
                    SUCCESS -> {
                        setGenreTitle(viewModel.getSelectedGenre()?.name.toString())
                        println(state.data.toString())
                    }

                    ERROR -> println(state.status.name)
                    null -> println("First Initialization!")
                }
            }
        }

        initUIListener()
    }

    private fun initUIListener() {
        binding.buttonFilter.setOnClickListener {
            val filterPage = FilterBottomSheet { genre ->
                setGenreTitle(genre.name)
                viewModel.setSelectedGenre(genre)
                movieLazyPagingItems.refresh()
            }
            val bundle = bundleOf(
                "selected_genre_id" to viewModel.getSelectedGenre()?.id
            )
            filterPage.arguments = bundle
            filterPage.show(childFragmentManager, "FILTER_PAGE")
        }

        binding.buttonSearch.setOnClickListener {
            val bundle = bundleOf(
                "selected_genre_id" to viewModel.getSelectedGenre()?.id
            )
            findNavController().navigate(R.id.search_page, bundle)
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
