package com.mendes.litejack.model

open class Player {

    private val hand = mutableListOf<Card>()

    fun receiveCard(card: Card) {
        hand.add(card)
    }

    fun getHand(): List<Card> = hand.toList()

    fun resetHand() {
        hand.clear()
    }

    fun calculateHandValue(): Int {
        var total = 0
        var aces = 0

        for (card in hand) {
            val value = when (card.rank) {
                "A" -> { aces++; 11 }
                "K", "Q", "J" -> 10
                else -> card.rank.toIntOrNull() ?: 0
            }
            total += value
        }

        // convert aces from 11 -> 1 while needed
        while (total > 21 && aces > 0) {
            total -= 10
            aces--
        }

        return total
    }
}