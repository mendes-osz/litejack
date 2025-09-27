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

        game.startNewHand()
        updateHandUI()
    }

    private fun updateHandUI() {
        val hand = game.getHand().joinToString(", ")
        val total = game.calculateHand()

        val displayText = if (total > 21) {
            "Your hand: $hand\nTotal: $total\nBusted!"
        } else {
            "Your hand: $hand\nTotal: $total"
        }

        binding.tvCards.text = displayText
    }

    private fun setupUI() {
        binding.btnDeal.setOnClickListener {
            if (game.getHand().isEmpty()) {
                game.startNewHand()
            } else {
                game.dealCard()
            }
            updateHandUI()
        }

        binding.btnReset.setOnClickListener {
            game.startNewHand()
            updateHandUI()
        }
    }
}