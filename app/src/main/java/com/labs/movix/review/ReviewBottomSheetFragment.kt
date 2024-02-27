package com.labs.movix.review

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.labs.movix.databinding.BottomSheetSeeReviewBinding
import com.labs.movix.detail.DetailMovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import com.labs.movix.R as RApp

@AndroidEntryPoint
class ReviewBottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var binding: BottomSheetSeeReviewBinding

    private val viewModel: DetailMovieViewModel by viewModels()
    private lateinit var adapter: ReviewAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomSheetSeeReviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListener()

        arguments?.getInt("movie_id")?.let {
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.getReviews(it.toString())
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.reviews.collectLatest { state ->
                state?.let { adapter.submitData(it) }
            }
        }
    }

    private fun initListener() = with(binding) {
        toolbar.apply {
            setNavigationOnClickListener { dismiss() }
            subtitle = arguments?.getString("movie_title")
        }

        adapter = ReviewAdapter()
        recyclerViewGenre.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@ReviewBottomSheetFragment.adapter
        }
    }

    override fun getTheme(): Int {
        return RApp.style.BottomSheetDialogRoundedEdge
    }

    override fun onStart() {
        super.onStart()

        setFullScreen()
    }

    private fun setFullScreen() {
        val parentLayout = dialog?.findViewById<View>(R.id.design_bottom_sheet)
        parentLayout?.let { bottomSheet ->
            val behaviour = BottomSheetBehavior.from(bottomSheet)
            val layoutParams = bottomSheet.layoutParams
            layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT
            bottomSheet.layoutParams = layoutParams
            behaviour.state = BottomSheetBehavior.STATE_HALF_EXPANDED
        }
    }

}