package com.labs.movix.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import coil.load
import com.labs.data.Status
import com.labs.data.repository.movie.Movie
import com.labs.movix.databinding.FragmentDetailMovieBinding
import com.labs.movix.getPosterUrl
import com.labs.movix.getRating
import com.labs.movix.review.ReviewBottomSheetFragment
import com.labs.movix.setUnderline
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailMovieFragment : Fragment() {

    private val viewModel: DetailMovieViewModel by viewModels()
    private lateinit var binding: FragmentDetailMovieBinding
    private val movieId by lazy { arguments?.getInt("movie_id", 0) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObserver()
        initListener()
    }

    private fun initListener() = with(binding) {
        toolbar.setNavigationOnClickListener { findNavController().navigateUp() }

        textSeeReviews.setOnClickListener {
            val reviewPage = ReviewBottomSheetFragment()
            val bundle = bundleOf(
                "movie_id" to movieId,
                "movie_title" to binding.toolbar.title.toString(),
            )

            reviewPage.arguments = bundle
            reviewPage.show(childFragmentManager, "REVIEW_PAGE")
        }
    }

    private fun initObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getDetailMovie(movieId.toString())
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.movies.collectLatest { state ->
                when (state?.status) {
                    Status.LOADING -> println(state.status.name)
                    Status.SUCCESS -> state.data?.let { setMovieUI(it) }
                    Status.ERROR -> println(state.status.name)
                    null -> println("First Initialization!")
                }
            }
        }
    }

    private fun setMovieUI(movie: Movie) = with(binding) {
        toolbar.title = movie.title
        imagePoster.load(getPosterUrl(path = movie.posterPath))
        textRating.text = getRating(rating = movie.rating)
        textOverview.text = movie.overview
        textSeeReviews.setUnderline(text = "See Reviews")
    }

}