package com.mendes.litejack

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mendes.litejack.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val game = BlackjackGame()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
    }

    private fun updatePlayerUI() {
        val hand = game.getPlayerHand().joinToString(", ") { it.toString() }
        val total = game.calculatePlayerHand()

        binding.tvCards.text = "Your hand: $hand\nTotal: $total"

        if (total > 21) {
            binding.tvCards.append("\nBusted!")
        }
    }

    private fun updateDealerUI(showAll: Boolean = false) {
        val dealerHand = game.getDealerHand()
        val display = if (showAll) {
            dealerHand.joinToString(", ") { it.toString() }
        } else {
            "${dealerHand.first()} + [Hidden]"
        }

        val total = if (showAll) game.calculateDealerHand() else "?"
        binding.tvDealer.text = "Dealer's hand: $display\nTotal: $total"
    }

    private fun setupUI() {
        binding.btnDeal.setOnClickListener {
            game.startNewHand()
            updatePlayerUI()
            updateDealerUI()
        }

        binding.btnHit.setOnClickListener {
            game.playerHit()
            updatePlayerUI()

            if (game.calculatePlayerHand() > 21) {
                game.dealerTurn()
                updateDealerUI(showAll = true)
            }
        }

        binding.btnStand.setOnClickListener {
            game.dealerTurn()
            updateDealerUI(showAll = true)

            val winner = game.checkWinner()
            binding.tvResult.text = when (winner) {
                "player" -> "You win!"
                "dealer" -> "Dealer wins!"
                else -> "Push (tie)"
            }
        }

        binding.btnReset.setOnClickListener {
            game.resetGame()
            binding.tvCards.text = "Game reset. Press Deal to start!"
            binding.tvDealer.text = ""
            binding.tvResult.text = ""
        }
    }
}