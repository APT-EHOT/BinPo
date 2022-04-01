package com.a1218v.binpo.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.a1218v.binpo.databinding.ScoreItemBinding
import com.a1218v.binpo.db.ScoreEntry

class ScoresAdapter : ListAdapter<ScoreEntry, ScoresAdapter.ScoreViewHolder>(DiffCallback()) {


    inner class ScoreViewHolder(private val binding: ScoreItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(scoreEntry: ScoreEntry) {
            binding.apply {
                tvScoresPlace.text = "${adapterPosition + 1}"
                tvScoresName.text = scoreEntry.name
                tvScoresScore.text = scoreEntry.score.toString()
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<ScoreEntry>() {

        override fun areItemsTheSame(
            oldItem: ScoreEntry,
            newItem: ScoreEntry
        ) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: ScoreEntry,
            newItem: ScoreEntry
        ) =
            oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScoreViewHolder {
        val binding = ScoreItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ScoreViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ScoreViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }
}