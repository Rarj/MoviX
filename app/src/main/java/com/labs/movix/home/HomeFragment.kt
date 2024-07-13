package com.labs.movix.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.labs.data.BuildConfig
import com.labs.home.ui.HomeUI
import com.labs.home.ui.HomeViewModel
import com.labs.movix.R
import com.labs.movix.databinding.FragmentHomeBinding
import com.labs.movix.genre.FilterBottomSheet
import com.labs.uikit.PosterUiKit
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import com.labs.home.ui.R as RHome

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.composeRoot.apply {
            setContent {
                setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)

                val movieLazyPagingItems = viewModel.moviePagingDataState.collectAsLazyPagingItems()
                HomeUI(
                    selectedGenre = getString(
                        RHome.string.selected_genre_label,
                        viewModel.state.collectAsState().value.selectedGenre.orEmpty()
                    ),
                    onSearchClicked = {
                        val bundle = bundleOf(
                            "selected_genre_id" to viewModel.state.value.selectedGenreId?.toInt()
                        )
                        findNavController().navigate(R.id.search_page, bundle)
                    },
                    onFilterClicked = {
                        val filterPage = FilterBottomSheet { genre ->
                            viewModel.setSelectedGenre(
                                id = genre.id,
                                name = genre.name,
                            )
                            viewLifecycleOwner.lifecycleScope.launch {
                                viewModel.getMovies()
                            }
                            movieLazyPagingItems.refresh()
                        }
                        val bundle = bundleOf(
                            "selected_genre_id" to viewModel.state.value.selectedGenreId?.toInt()
                        )
                        filterPage.arguments = bundle
                        filterPage.show(childFragmentManager, "FILTER_PAGE")
                    },
                    contentItem = {
                        items(movieLazyPagingItems.itemCount) { index ->
                            PosterUiKit(url = buildString {
                                append(BuildConfig.IMAGE_BASE_URL)
                                append(movieLazyPagingItems[index]?.posterPath)
                            }) {
                                val movieId = movieLazyPagingItems[index]?.id
                                findNavController().navigate(
                                    R.id.detail_movie_page, bundleOf("movie_id" to movieId)
                                )
                            }
                        }
                    },
                )
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(state = Lifecycle.State.CREATED) {
                viewModel.getGenres()
            }
        }
    }
}
