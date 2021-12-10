package com.example.flashcards.presentation.ui.recyclers

import androidx.recyclerview.widget.RecyclerView
import com.example.flashcards.data.room.Stack
import com.example.flashcards.databinding.StackItemBinding

class StackViewHolder(
    private val binding: StackItemBinding,
    private val listener: StackItemClickListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(stack: Stack) {
        binding.stackName.text = stack.name
        binding.itemLayout.setOnClickListener {
            listener.openCardList(stack.id)
            }
        binding.deleteButton.setOnClickListener {
            listener.deleteStack(stack)
        }
        binding.editStackButton.setOnClickListener {
            listener.editStack(stack)
        }
        binding.listCardReviewButton.setOnClickListener {
            listener.openCardListReview(stack.id)
        }
    }
}
