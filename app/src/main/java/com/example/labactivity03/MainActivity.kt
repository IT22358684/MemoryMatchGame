package com.example.labactivity03

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startBtn: Button = findViewById<Button>(R.id.startBtn)
        startBtn.setOnClickListener {
        val intent = Intent(this, GameView::class.java)
        startActivity(intent)
        }
    }
}