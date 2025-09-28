package com.mendes.litejack.model

data class Card(val rank: String, val suit: String) {

    override fun toString(): String {
        return "$rank of $suit"
    }
}