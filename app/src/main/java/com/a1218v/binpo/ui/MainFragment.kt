package com.a1218v.binpo.ui

import android.os.Bundle
import android.view.ContextThemeWrapper
import android.view.View
import android.widget.Button
import android.widget.GridLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.a1218v.binpo.R
import com.a1218v.binpo.databinding.MainFragmentBinding
import com.a1218v.binpo.viewmodels.MainFragmentViewModel
import com.a1218v.binpo.viewmodels.ResultState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.main_fragment) {

    private var bindingOrNull: MainFragmentBinding? = null // ? is for nullable variable type
    private val binding get() = bindingOrNull!! // !! is for turning off null safety

    private val viewModel by viewModels<MainFragmentViewModel>() // View model initialization with delegate property

    private fun initObservers() {
        viewModel.currentNumber.observe(viewLifecycleOwner) { value ->
            binding.tvMainPlayerInput.text = value
        }
        viewModel.state.observe(viewLifecycleOwner) { value ->
            binding.tvMainHint.text = when (value) {
                ResultState.BEGIN -> getString(R.string.begin_message)
                ResultState.MORE -> getString(R.string.more_message)
                ResultState.LESS -> getString(R.string.less_message)
                ResultState.FINISH -> {
                    val action = MainFragmentDirections.actionMainFragmentToResultFragment(viewModel.numberOfAttempts)
                    findNavController().navigate(action)
                    ""
                }
                else -> getString(R.string.begin_message)
            }
        }
    }

    private fun prepareKeyboard() {
        for (i in 1..12) {
            val button = Button(ContextThemeWrapper(activity, R.style.keyboardStyle), null, 0)
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