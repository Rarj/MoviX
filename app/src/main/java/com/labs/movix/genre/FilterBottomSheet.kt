package com.labs.movix.genre

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
import com.labs.data.Status
import com.labs.data.repository.genre.Genre
import com.labs.movix.databinding.BottomSheetFilterBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import com.labs.movix.R as RApp

@AndroidEntryPoint
class FilterBottomSheet(
    private val onSelected: (Genre) -> Unit,
) : BottomSheetDialogFragment() {

    private val viewModel: FilterViewModel by viewModels()

    private lateinit var binding: BottomSheetFilterBinding
    private lateinit var adapter: FilterAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomSheetFilterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = FilterAdapter { genre ->
            onSelected.invoke(genre)
            dismiss()
        }

        binding.recyclerFilter.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@FilterBottomSheet.adapter
        }

        viewModel.getGenre()

        initObserver()

    }

    override fun getTheme(): Int {
        return RApp.style.BottomSheetDialogRoundedEdge
    }

    private fun initObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.genre.collectLatest { state ->
                when (state?.status) {
                    Status.LOADING -> println(state.status.name)
                    Status.SUCCESS -> {
                        adapter.updateGenre(
                            state.data.orEmpty(),
                            arguments?.getInt("selected_genre_id", 0)
                        )
                        println(state.data.toString())
                    }

                    Status.ERROR -> println(state.status.name)
                    null -> println("First Initialization!")
                }
            }
        }
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
            behaviour.state = BottomSheetBehavior.STATE_EXPANDED
        }
    }

}