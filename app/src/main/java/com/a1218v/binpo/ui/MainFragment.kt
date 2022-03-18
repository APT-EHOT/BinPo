package com.a1218v.binpo.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.a1218v.binpo.R
import com.a1218v.binpo.databinding.MainFragmentBinding
import com.a1218v.binpo.viewmodels.MainFragmentViewModel

class MainFragment : Fragment(R.layout.main_fragment) {

    private var bindingOrNull: MainFragmentBinding? = null // ? is for nullable variable type
    private val binding get() = bindingOrNull!! // !! is for turning off null safety

    private val viewModel by viewModels<MainFragmentViewModel>() // View model initialization with delegate property

    var a: Int = 0

    private fun initObservers() {
        viewModel.counter.observe(viewLifecycleOwner) { value ->
            binding.tvMainTest.text = value.toString()
        }
    }

    private fun initViews() {
        binding.btnMainTest.setOnClickListener { viewModel.onBtnMainTestClick() }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindingOrNull = MainFragmentBinding.bind(view)

        initObservers()
        initViews()
    }
}