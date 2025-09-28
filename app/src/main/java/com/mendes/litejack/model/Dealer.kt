package com.mendes.litejack.model

class Dealer : Player() {

    fun playTurn(deck: Deck) {
        while (calculateHandValue() < 17) {
            receiveCard(deck.drawCard())
        }
    }
}