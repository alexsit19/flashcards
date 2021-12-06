package com.example.flashcards.presentation.ui.recyclers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.flashcards.data.room.Card
import com.example.flashcards.databinding.CardReviewItemBinding

class CardAdapter(
    private val listener: CardReviewItemClickListener
) : ListAdapter<Card, CardViewHolder>(CardComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CardReviewItemBinding.inflate(layoutInflater, parent, false)
        return CardViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val card = getItem(position)
        holder.bind(card)
    }
}

class CardComparator : DiffUtil.ItemCallback<Card>() {
    override fun areItemsTheSame(oldItem: Card, newItem: Card): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Card, newItem: Card): Boolean {
        return oldItem == newItem
    }
}