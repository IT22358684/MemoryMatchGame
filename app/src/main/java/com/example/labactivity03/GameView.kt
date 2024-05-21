package com.example.labactivity03

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageButton
import android.widget.TextView

class GameView : AppCompatActivity() {

    private lateinit var images: MutableList<Int>
    private lateinit var buttons: Array<ImageButton>
    private var clicked = 0
    private var lastClicked = -1
    private var score = 100
    private var moves = 50

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_view)

        val img1 = R.drawable.img1
        val img2 = R.drawable.img2
        val img3 = R.drawable.img3
        val img4 = R.drawable.img4
        val img5 = R.drawable.img5
        val img6 = R.drawable.img6
        val back = R.drawable.card1

        images = mutableListOf(img1, img2, img3, img4, img5, img6,img1, img2, img3, img4, img5, img6)
        images.shuffle()

        buttons = arrayOf( findViewById(R.id.imageButton1), findViewById(R.id.imageButton2), findViewById(R.id.imageButton3), findViewById(R.id.imageButton4),
            findViewById(R.id.imageButton5), findViewById(R.id.imageButton6), findViewById(R.id.imageButton7), findViewById(R.id.imageButton8),
            findViewById(R.id.imageButton9), findViewById(R.id.imageButton10), findViewById(R.id.imageButton11), findViewById(R.id.imageButton12) )

        buttons.forEachIndexed { index, button ->
            button.setImageResource(back)
            button.tag = index
            button.setOnClickListener { onCardClick(it as ImageButton) }
        }

        val scoreTextView: TextView = findViewById(R.id.score)
        updateScore(scoreTextView)

        val movesTextView: TextView = findViewById(R.id.moves)
        updateMoves(movesTextView)
    }

    private fun updateScore(scoreTextView: TextView) {
        scoreTextView.text = "$score"
    }

    private fun updateMoves(movesTextView: TextView) {
        movesTextView.text = "$moves"
    }

    private fun onCardClick(clickedButton: ImageButton) {
        if (clickedButton.contentDescription != "matched" && clicked < 2) {
            moves--
            score -= 2
            updateMoves(findViewById(R.id.moves))
            updateScore(findViewById(R.id.score))
            val index = clickedButton.tag as Int
            clickedButton.setImageResource(images[index])
            clickedButton.contentDescription = images[index].toString()

            if (clicked == 0) {
                lastClicked = index
            }

            clicked++

            if (clicked == 2) {
                val lastImage = images[lastClicked]
                val currentImage = images[index]

                if (lastImage == currentImage) {
                    buttons[lastClicked].contentDescription = "matched"
                    clickedButton.contentDescription = "matched"
                    clicked = 0
                    score += 10
                    updateScore(findViewById(R.id.score))
                } else {
                    Handler().postDelayed({
                        val back = R.drawable.card1
                        buttons[lastClicked].setImageResource(back)
                        buttons[index].setImageResource(back)
                        buttons[lastClicked].contentDescription = back.toString()
                        clickedButton.contentDescription = back.toString()
                        clicked = 0
                    }, 1000)
                }

                if (moves == 0 || buttons.all { it.contentDescription == "matched" }) {

                    val gameOverIntent = Intent(this, ScoreView::class.java)
                    gameOverIntent.putExtra("SCORE", score)
                    startActivity(gameOverIntent)

                }
            }
        }
    }
}