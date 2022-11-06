package com.example.aston_rick_and_morty.views.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.aston_rick_and_morty.databinding.LocationLayoutBinding
import com.example.aston_rick_and_morty.models.remote_dto.Location

class LocationPagedAdapter(private val onItemClicked: ((location: Location) -> Unit)):
    PagingDataAdapter<Location, LocationPagedAdapter.LocationViewHolder>(diffCallback) {


    class LocationViewHolder(val binding: LocationLayoutBinding)
        :RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.binding.apply {
            locationNameView.text = "${currentItem?.name}"
            locationTypeView.text = "${currentItem?.type}"
            locationDimensionView.text = "${currentItem?.dimension}"

        }

        holder.itemView.setOnClickListener {
            if (currentItem != null) {
                onItemClicked(currentItem)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        return LocationViewHolder(
            LocationLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }


    companion object diffCallback : DiffUtil.ItemCallback<Location>() {
        override fun areItemsTheSame(oldItem: Location, newItem: Location): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Location, newItem: Location): Boolean {
            return oldItem == newItem
        }

    }

}
