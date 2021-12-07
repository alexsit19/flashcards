package com.example.flashcards.presentation.ui.recyclers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.flashcards.R
import com.example.flashcards.data.room.Card
import com.example.flashcards.databinding.ItemViewpagerBinding

class CardPageAdapter (
    private val cardList: List<Card>
    ) : RecyclerView.Adapter<CardPageAdapter.CardPagerViewHolder>()  {

    inner class CardPagerViewHolder(
        private val binding: ItemViewpagerBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(card: Card) {
            val frontText = card.frontSide
            val backText = card.backSide
            binding.cardText.text = card.frontSide

            binding.cardView.setOnClickListener {
                when (binding.cardText.text) {
                    frontText -> binding.cardText.text = backText
                    backText -> binding.cardText.text = frontText
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardPagerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemViewpagerBinding.inflate(layoutInflater, parent, false)
        return CardPagerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CardPagerViewHolder, position: Int) {
        val card = cardList[position]
        holder.bind(card)
    }

    override fun getItemCount(): Int {
        return cardList.size
    }


}