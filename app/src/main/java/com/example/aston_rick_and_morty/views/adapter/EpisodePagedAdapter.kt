package com.example.aston_rick_and_morty.views.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.aston_rick_and_morty.databinding.EpisodeLayoutBinding
import com.example.aston_rick_and_morty.models.remote_dto.Episode


class EpisodePagedAdapter(private val onItemClicked: ((episode: Episode) -> Unit))
    : PagingDataAdapter<Episode, EpisodePagedAdapter.EpisodeViewHolder>(diffCallback) {

    class EpisodeViewHolder(val binding: EpisodeLayoutBinding):
        RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.binding.apply {
            episodeNameView.text = "${currentItem?.name}"
            episodeView.text = "${currentItem?.episode}"
            airDateView.text = "${currentItem?.air_date}"
        }

        holder.itemView.setOnClickListener {
            if (currentItem != null) {
                onItemClicked(currentItem)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        return EpisodeViewHolder(
            EpisodeLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }



    companion object diffCallback : DiffUtil.ItemCallback<Episode>() {

        override fun areItemsTheSame(oldItem: Episode, newItem: Episode): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Episode, newItem: Episode): Boolean {
            return oldItem == newItem
        }

    }
}