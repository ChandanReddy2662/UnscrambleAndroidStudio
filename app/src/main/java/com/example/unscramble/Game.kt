package com.example.unscramble

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.progressindicator.CircularProgressIndicator
import java.security.ProtectionDomain


class Game : AppCompatActivity() {
    private lateinit var gameRepository: WordRepository
    private lateinit var repository: List<String>
    private lateinit var scrambled: TextView
    private lateinit var unScrambled: EditText
    private lateinit var scoreTV: TextView
    private lateinit var wordCount: TextView
    private lateinit var skip: Button
    private lateinit var submit: Button
    private lateinit var gameWords: List<String>
    private lateinit var progressBar: CircularProgressIndicator
    private lateinit var timer: TextView
    private lateinit var countDownTimer: CountDownTimer

    private var timeRemaining = 0L
    private var score = 0
    private var level: String = ""
    private var words = 0
    private var correctWords = 0

    private val totalTime = 30000L
    private val timeInterval = 1000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        scrambled= findViewById(R.id.scrambled)
        unScrambled = findViewById(R.id.unscrambled)
        skip = findViewById(R.id.skip)
        submit = findViewById(R.id.submit)
        scoreTV = findViewById(R.id.score)
        wordCount = findViewById(R.id.word_count)
        progressBar = findViewById(R.id.timer_progress)
        timer = findViewById(R.id.timer)
        level = intent.getStringExtra("level").toString().lowercase()
        gameRepository = WordRepository(level)

        gameWords = loadWord()
        submit.setOnClickListener{
            if(unScrambled.text.toString() == gameWords[0]) {
                correctWords++
                words++
                wordCount.text = "$words"
                updateScore()
                val s = scoreTV.text.toString().toInt() +  score
                scoreTV.text = "$s"
                if(words + 1 == 11)
                    gameOver()
                print(words);
                toast("CORRECT ANSWER")
                countDownTimer.cancel()
                gameWords = loadWord()
            }
            else
                toast("OOPS...WRONG ANSWER")
        }

        skip.setOnClickListener{
            words++
            wordCount.text = words.toString()
            if(words + 1 == 11)
                gameOver()
            countDownTimer.cancel()
            gameWords = loadWord()
            toast("YOU SKIPPED...")
        }
    }

    private fun loadWord(): List<String>{
        repository = gameRepository.getRepository()
        setWord(scrambled, repository[1], unScrambled)
        timer.text = (totalTime).toString()
        startTimer()
        updateTimerProgress()
        return repository
    }

    private fun gameOver(){
            val intent = Intent(this, GameOver::class.java)
            intent.putExtra("score", scoreTV.text.toString())
            intent.putExtra("correctWords", "$correctWords")
            intent.putExtra("words", "10")
            words = 0
            startActivity(intent)
    }

    private fun toast(message: String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun setWord(scrambled: TextView, scrambledWord: String, unScrambled: EditText){
        scrambled.text = scrambledWord
        unScrambled.text.clear()
    }

    private fun startTimer(){
        countDownTimer = object: CountDownTimer(totalTime, timeInterval){
            override fun onFinish(){
                words++
                wordCount.text = words.toString()
                if(words + 1 == 1)
                    gameOver()
                gameWords = loadWord()
                toast("TIME UP...")
            }

            override fun onTick(millisUntilFinished: Long) {
                timeRemaining = millisUntilFinished / timeInterval
                updateTimerProgress()
            }
        }.start()
    }

    private fun updateScore(){
        score = when{
            timeRemaining >= 15 -> 20
            timeRemaining in 10..14 -> 10
            else -> 5
        }

    }

    private fun updateTimerProgress(){
        progressBar.progress = (timeRemaining).toInt()
        timer.text = timeRemaining.toString()
    }
}
