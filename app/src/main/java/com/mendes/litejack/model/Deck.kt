package com.mendes.litejack.model

class Deck {

    private val cards: MutableList<Card> = mutableListOf()

    init {
        reset()
    }

    fun reset() {
        cards.clear()

        val suits = listOf("♠", "♥", "♦", "♣")
        val ranks = listOf("A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K")

        for (suit in suits) {
            for (rank in ranks) {
                cards.add(Card(rank, suit))
            }
        }

        cards.shuffle()
    }

    fun drawCard(): Card {
        if (cards.isEmpty()) {
            reset()
        }
        return cards.removeAt(0)
    }
}