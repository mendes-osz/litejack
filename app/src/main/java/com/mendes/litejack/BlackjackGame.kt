package com.mendes.litejack

import kotlin.random.Random

class BlackjackGame {

    private val deck = mutableListOf<String>()
    private val hand = mutableListOf<String>()

    init {
        reset()
    }

    fun reset() {
        deck.clear()
        hand.clear()

        val suits = listOf("♠", "♥", "♦", "♣")
        val ranks = listOf("A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K")

        for (suit in suits) {
            for (rank in ranks) {
                deck.add("$rank$suit")
            }
        }

        deck.shuffle()
    }

    fun startNewHand() {
        reset()
        dealCard()
        dealCard()
    }

    fun dealCard(): String {
        if (deck.isEmpty()) reset()
        val card = deck.removeAt(Random.nextInt(deck.size))
        hand.add(card)
        return card
    }

    fun getHand(): List<String> = hand.toList()

    fun calculateHand(): Int {
        var total = 0
        var aces = 0

        for (card in hand) {
            val rank = card.dropLast(1) // remove suit
            total += when (rank) {
                "A" -> { aces++; 11 }
                "K", "Q", "J" -> 10
                else -> rank.toInt()
            }
        }

        while (total > 21 && aces > 0) {
            total -= 10
            aces--
        }

        return total
    }
}