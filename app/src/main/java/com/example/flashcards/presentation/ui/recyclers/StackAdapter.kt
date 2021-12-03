package com.example.flashcards.presentation.ui.recyclers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.flashcards.data.room.Stack
import com.example.flashcards.databinding.StackItemBinding

class StackAdapter(
    private val listener: RecyclerViewItemClickListener
    ) : ListAdapter<Stack, StackViewHolder>(StackComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StackViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = StackItemBinding.inflate(layoutInflater, parent, false)
        return StackViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: StackViewHolder, position: Int) {
        val stack = getItem(position)
        holder.bind(stack)
    }
}

class StackComparator : DiffUtil.ItemCallback<Stack>() {
    override fun areItemsTheSame(oldItem: Stack, newItem: Stack): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Stack, newItem: Stack): Boolean {
        return oldItem == newItem
    }
}