package com.a1218v.binpo.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.a1218v.binpo.R
import com.a1218v.binpo.databinding.MainFragmentBinding
import com.a1218v.binpo.databinding.ResultFragmentBinding
import com.a1218v.binpo.viewmodels.ResultFragmentViewModel

class ResultFragment : Fragment(R.layout.result_fragment) {

    private var bindingOrNull: ResultFragmentBinding? = null
    private val binding get() = bindingOrNull!!

    private val viewModel by viewModels<ResultFragmentViewModel>()

    private val args by navArgs<ResultFragmentArgs>()

    private val scoresAdapter = ScoresAdapter()

    private fun initRecyclers() {
        binding.rvResultScores.apply {
            adapter = scoresAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun initObservers() {
        viewModel.scores.observe(viewLifecycleOwner) {
            scoresAdapter.submitList(it)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val currentScore = args.numberOfAttempts
        bindingOrNull = ResultFragmentBinding.bind(view)

        initRecyclers()
        initObservers()

        viewModel.onScreenStarted(currentScore, requireActivity().applicationContext)


    }
}