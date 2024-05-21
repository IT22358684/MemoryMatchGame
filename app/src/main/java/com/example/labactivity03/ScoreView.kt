package com.example.labactivity03

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView

class ScoreView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score_view)

        val score = intent.getIntExtra("SCORE", 0)
        val scoreTextView: TextView = findViewById(R.id.finalScore)
        scoreTextView.text = "$score"

        saveHighScore(score)
        displayHighScore()

        val replayBtn: ImageButton = findViewById<ImageButton>(R.id.replay)
        replayBtn.setOnClickListener {
        val intent = Intent(this, GameView::class.java)
        startActivity(intent)
        }
    }

    private fun saveHighScore(score: Int) {
        val prefs = getSharedPreferences("MY_PREFS_NAME", MODE_PRIVATE)
        val highScore = prefs.getInt("HIGH_SCORE", 0)
        if (score > highScore) {
            val editor = prefs.edit()
            editor.putInt("HIGH_SCORE", score)
            editor.apply()
        }
    }

    private fun displayHighScore() {
        val prefs = getSharedPreferences("MY_PREFS_NAME", MODE_PRIVATE)
        val highScore = prefs.getInt("HIGH_SCORE", 0)
        val highScoreTextView: TextView = findViewById(R.id.highScore)
        highScoreTextView.text = "$highScore"
    }
}