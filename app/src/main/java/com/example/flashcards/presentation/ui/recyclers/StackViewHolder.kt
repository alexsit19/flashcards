package com.example.flashcards.presentation.ui.recyclers

import androidx.recyclerview.widget.RecyclerView
import com.example.flashcards.data.room.Stack
import com.example.flashcards.databinding.StackItemBinding

class StackViewHolder(
    private val binding: StackItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(stack: Stack) {
        binding.stackName.text = stack.name
    }
}