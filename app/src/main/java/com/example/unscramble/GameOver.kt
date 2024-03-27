package com.example.unscramble

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class GameOver : AppCompatActivity() {
    private lateinit var result: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_over)

        result = findViewById(R.id.result)
        val intent = intent
        val score = intent.getStringExtra("score")
        val correctWords = intent.getStringExtra("correctWords")
        val words = intent.getStringExtra("words")

        result.textAlignment = TextView.TEXT_ALIGNMENT_CENTER
        val res = "YOU GOT $correctWords / $words CORRECT\n" +
                "YOUR SCORE IS $score"
        result.text = res

    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
        finish()
    }
}