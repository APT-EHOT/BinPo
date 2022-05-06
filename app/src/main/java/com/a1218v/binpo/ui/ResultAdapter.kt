package com.a1218v.binpo.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.a1218v.binpo.databinding.RvItemBinding
import com.a1218v.binpo.db.ScoreEntry

class ResultAdapter :
    ListAdapter<ScoreEntry, ResultAdapter.ResultViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultViewHolder {
        val binding =
            RvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ResultViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    inner class ResultViewHolder(private val binding: RvItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(scoreEntry: ScoreEntry) {
            binding.apply {
                place.text = adapterPosition.toString()
                name.text = scoreEntry.name
                score.text = scoreEntry.score.toString()
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
}