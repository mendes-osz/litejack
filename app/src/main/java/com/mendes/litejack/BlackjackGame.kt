package com.mendes.litejack

import com.mendes.litejack.model.Dealer
import com.mendes.litejack.model.Deck
import com.mendes.litejack.model.Player
import com.mendes.litejack.model.Card

class BlackjackGame {

    private val deck = Deck()
    val player = Player()
    val dealer = Dealer()

    fun startNewHand() {
        player.resetHand()
        dealer.resetHand()
        deck.reset()

        repeat(2) { player.receiveCard(deck.drawCard()) }
        repeat(2) { dealer.receiveCard(deck.drawCard()) }
    }

    fun playerHit() {
        player.receiveCard(deck.drawCard())
    }

    fun dealerTurn() {
        dealer.playTurn(deck)
    }

    fun getPlayerHand(): List<Card> = player.getHand()
    fun getDealerHand(): List<Card> = dealer.getHand()

    fun calculatePlayerHand(): Int = player.calculateHandValue()
    fun calculateDealerHand(): Int = dealer.calculateHandValue()

    fun resetGame() {
        player.resetHand()
        dealer.resetHand()
        deck.reset()
    }

    fun checkWinner(): String {
        val playerTotal = calculatePlayerHand()
        val dealerTotal = calculateDealerHand()

        return when {
            playerTotal > 21 -> "dealer"
            dealerTotal > 21 -> "player"
            playerTotal > dealerTotal -> "player"
            dealerTotal > playerTotal -> "dealer"
            else -> "push" // tie
        }
    }
}