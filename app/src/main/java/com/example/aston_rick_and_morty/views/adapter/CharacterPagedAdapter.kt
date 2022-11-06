package com.example.aston_rick_and_morty.views.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.aston_rick_and_morty.databinding.CharacterLayoutBinding
import com.example.aston_rick_and_morty.models.remote_dto.Character


class CharacterPagedAdapter(private val onItemClicked: ((character: Character) -> Unit)) :
        PagingDataAdapter<Character, CharacterPagedAdapter.CharacterViewHolder>(diffCallback){

    class CharacterViewHolder(val binding: CharacterLayoutBinding):
        RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.binding.apply {
            nameTextView.text = "${currentItem?.name}"
            speciesTextView.text = "${currentItem?.species}"
            statusTextView.text = "${currentItem?.status}"
            genderTextView.text = "${currentItem?.gender}"

            val imageUrl = currentItem?.image
            Glide
                .with(imageView)
                .load(imageUrl)
                .into(imageView)


        }

        holder.itemView.setOnClickListener {
            if (currentItem != null) {
                onItemClicked(currentItem)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(
            CharacterLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }


    companion object{
        val diffCallback = object : DiffUtil.ItemCallback<Character>(){
            override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
                return oldItem == newItem
            }
        }
    }

}


