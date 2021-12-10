package com.example.flashcards.presentation.ui.recyclers

import androidx.recyclerview.widget.RecyclerView
import com.example.flashcards.data.room.Card
import com.example.flashcards.databinding.CardReviewItemBinding

class CardViewHolder(
    private val binding: CardReviewItemBinding,
    private val listener: CardReviewItemClickListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(card: Card) {
        binding.frontSideText.text = card.frontSide
        binding.backSideText.text = card.backSide

        binding.deleteButton.setOnClickListener {
            listener.deleteCard(card)
        }

        binding.editButton.setOnClickListener {
            listener.editCard(card)
        }
    }
}
