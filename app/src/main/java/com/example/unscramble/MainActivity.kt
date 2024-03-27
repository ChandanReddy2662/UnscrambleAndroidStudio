package com.example.unscramble

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private lateinit var randomLevel: Button
    private lateinit var easyLevel: Button
    private lateinit var mediumLevel: Button
    private lateinit var hardLevel: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        randomLevel = findViewById(R.id.random)
        easyLevel = findViewById(R.id.easy)
        mediumLevel = findViewById(R.id.medium)
        hardLevel = findViewById(R.id.hard)
        selectLevel(randomLevel)
        selectLevel(easyLevel)
        selectLevel(mediumLevel)
        selectLevel(hardLevel)
    }

    private fun selectLevel(level: Button){
        level.setOnClickListener{
            val intent = Intent(this, Game::class.java)
            intent.putExtra("level", level.text.toString())
            startActivity(intent)
        }
    }
}