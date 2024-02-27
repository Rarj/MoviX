package com.labs.movix.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.filter
import androidx.recyclerview.widget.GridLayoutManager
import com.labs.movix.R
import com.labs.movix.databinding.FragmentSearchBinding
import com.labs.movix.textChanges
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
@AndroidEntryPoint
class SearchMovieFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding

    private val viewModel: SearchViewModel by viewModels()
    private lateinit var adapter: SearchAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListener()
        initObserver()
        arguments?.getInt("selected_genre_id", 0)?.let { viewModel.selectedGenre(it) }
    }

    private fun initObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            binding.inputSearch.textChanges()
                .debounce(1000)
                .onEach { viewModel.searchMovie(it.toString()) }
                .launchIn(this)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.movie.collectLatest { state ->
                state?.let {
                    adapter.submitData(it)
                }
            }
        }
    }

    private fun initListener() = with(binding) {
        toolbar.setNavigationOnClickListener { findNavController().navigateUp() }

        adapter = SearchAdapter { movieId ->
            val bundle = bundleOf(
                "movie_id" to movieId,
            )
            findNavController().navigate(R.id.detail_movie_page, bundle)
        }

        recyclerViewGenre.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = this@SearchMovieFragment.adapter
        }
    }

}