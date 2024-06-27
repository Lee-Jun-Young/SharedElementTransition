package com.example.sharedelementtransition.xml

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sharedelementtransition.ListItem
import com.example.sharedelementtransition.databinding.RvItemBinding

class ItemAdapter(private val onClickListener: (View, ListItem) -> Unit) :
    ListAdapter<ListItem, ItemAdapter.ItemViewHolder>(
        SearchDiffCallback
    ) {
    inner class ItemViewHolder(private val binding: RvItemBinding) :
        RecyclerView.ViewHolder(
            binding.root
        ) {
        fun bind(data: ListItem) = with(binding) {
            Glide.with(itemView)
                .load(data.image)
                .into(ivListImg)

            tvListTitle.text = data.name
            tvListDescription.text = data.description

            itemView.setOnClickListener {
                onClickListener(it, data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = RvItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    object SearchDiffCallback : DiffUtil.ItemCallback<ListItem>() {
        override fun areItemsTheSame(
            oldItem: ListItem,
            newItem: ListItem
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: ListItem,
            newItem: ListItem
        ): Boolean {
            return oldItem.id == newItem.id
        }
    }
}