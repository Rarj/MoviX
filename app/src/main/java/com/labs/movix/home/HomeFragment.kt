package com.labs.movix.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
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
import com.labs.uikit.ToolbarUiKit
import com.labs.uikit.appearance.ColorSecondaryVariant
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import com.labs.uikit.R as RUiKit

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val viewModel: HomeViewModel by viewModels()

    private lateinit var movieLazyPagingItems: LazyPagingItems<Movie>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.composeRoot.apply {
            setContent {
                setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)

                ConstraintLayout {
                    val (toolbar, genre, movies) = createRefs()

                    ToolbarUiKit(
                        modifier = Modifier.constrainAs(toolbar) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        },
                        onSearchClicked = {
                            val bundle = bundleOf(
                                "selected_genre_id" to viewModel.getSelectedGenre()?.id
                            )
                            findNavController().navigate(R.id.search_page, bundle)
                        },
                        onFilterClicked = {
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
                        },
                    )

                    Text(
                        modifier = Modifier
                            .constrainAs(genre) {
                                top.linkTo(toolbar.bottom)
                                start.linkTo(parent.start)
                            }
                            .padding(top = 16.dp, end = 16.dp, start = 16.dp),
                        text = getString(
                            R.string.selected_genre_label,
                            viewModel.state.collectAsState().value.selectedGenre.orEmpty()
                        ),
                        color = ColorSecondaryVariant,
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(RUiKit.font.sono_medium)),
                    )

                    MoviesUI(modifier = Modifier.constrainAs(movies) {
                        top.linkTo(genre.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    })
                }
            }
        }

        return binding.root
    }

    @Composable
    private fun MoviesUI(modifier: Modifier) {
        movieLazyPagingItems = viewModel.movieFlow.collectAsLazyPagingItems()
        LazyVerticalGrid(
            columns = GridCells.Fixed(count = 2),
            modifier = modifier.padding(start = 8.dp, end = 8.dp, top = 16.dp)
        ) {
            items(movieLazyPagingItems.itemCount) { index ->
                movieLazyPagingItems[index]?.posterPath?.let { url ->
                    PosterUiKit(url = buildString {
                        append(BuildConfig.IMAGE_BASE_URL)
                        append(url)
                    }) {
                        findNavController().navigate(
                            R.id.detail_movie_page, bundleOf(
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
