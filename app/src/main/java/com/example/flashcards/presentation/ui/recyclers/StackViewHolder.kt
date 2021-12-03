package com.example.flashcards.presentation.ui.recyclers

import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.flashcards.data.room.Stack
import com.example.flashcards.databinding.StackItemBinding

class StackViewHolder(
    private val binding: StackItemBinding,
    private val listener: RecyclerViewItemClickListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(stack: Stack) {
        binding.stackName.text = stack.name
        binding.itemLayout.setOnClickListener{
            binding.stackName.text = "just click"
            listener.onItemClickListener()
        }
        binding.itemLayout.setOnLongClickListener {
            binding.stackName.text = "Long click"
            listener.onLongItemClickListener()
            true
        }
    }
}

