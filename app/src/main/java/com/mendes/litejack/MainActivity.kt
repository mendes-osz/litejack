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

    private fun setupUI() {
        binding.btnDeal.setOnClickListener {
            dealCard()
        }

        binding.btnReset.setOnClickListener {
            resetGame()
        }
    }

    private fun dealCard() {
        val card = game.dealCard()
        val hand = game.getHand().joinToString(", ")
        val total = game.calculateHand()

        binding.tvCards.text = "Your hand: $hand\nTotal: $total"

        if (total > 21) {
            binding.tvCards.append("Busted!")
        }
    }

    private fun resetGame() {
        game.reset()
        binding.tvCards.text = "Game reset. Press Deal to start!"
    }
}