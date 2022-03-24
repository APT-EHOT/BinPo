package com.a1218v.binpo.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.GridLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.a1218v.binpo.R
import com.a1218v.binpo.databinding.MainFragmentBinding
import com.a1218v.binpo.viewmodels.MainFragmentViewModel

class MainFragment : Fragment(R.layout.main_fragment) {

    private var bindingOrNull: MainFragmentBinding? = null // ? is for nullable variable type
    private val binding get() = bindingOrNull!! // !! is for turning off null safety

    private val viewModel by viewModels<MainFragmentViewModel>() // View model initialization with delegate property

    private fun initObservers() {
        viewModel.currentNumber.observe(viewLifecycleOwner) { value ->
            binding.tvMainPlayerInput.text = value
        }
    }

    private fun prepareKeyboard() {
        for (i in 1..12) {
            val button = Button(activity)
            button.text = when (i) {
                10 -> "C"
                11 -> "0"
                12 -> "OK"
                else -> (i).toString()
            }

            val layoutParams = GridLayout.LayoutParams(
                GridLayout.spec(GridLayout.UNDEFINED, GridLayout.FILL, 1f),
                GridLayout.spec(GridLayout.UNDEFINED, GridLayout.FILL, 1f)
            )
            layoutParams.height = 0
            layoutParams.width = 0
            button.layoutParams = layoutParams

            // button.layoutParams = ViewGroup.LayoutParams(GridLayout.LayoutParams.MATCH_PARENT, GridLayout.LayoutParams.MATCH_PARENT)
            binding.glMainKeyboard.addView(button)
            button.setOnClickListener { viewModel.onKeyboardButtonClick(i) }
        }
    }

    private fun initViews() {
        prepareKeyboard()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindingOrNull = MainFragmentBinding.bind(view)

        initObservers()
        initViews()
    }
}