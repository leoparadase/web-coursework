package com.example.simplecounter

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var counterText: TextView
    private lateinit var sharedPreferences: SharedPreferences
    private var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        counterText = findViewById(R.id.textView)
        val plusButton = findViewById<Button>(R.id.btnPlus)
        val minusButton = findViewById<Button>(R.id.btnMinus)
        val resetButton = findViewById<Button>(R.id.btnReset)
        val plus10Button = findViewById<Button>(R.id.btnPlus10)
        val minus10Button = findViewById<Button>(R.id.btnMinus10)
        val plus100Button = findViewById<Button>(R.id.btnPlus100)
        val minus100Button = findViewById<Button>(R.id.btnMinus100)

        sharedPreferences = getSharedPreferences("CounterPrefs", Context.MODE_PRIVATE)
        counter = sharedPreferences.getInt("counter", 0)

        plusButton.setOnClickListener {
            // Увеличить число на 1
            counter++
            updateCounterText()
        }

        minusButton.setOnClickListener {
            // Уменьшить число на 1, если оно больше 0
            if (counter > 0) {
                counter--
                updateCounterText()
            }
            else
            {
                counter = 0
                updateCounterText()
            }
        }

        plus10Button.setOnClickListener {
            // Увеличить число на 10
            counter = counter + 10
            updateCounterText()
        }

        minus10Button.setOnClickListener {
            // Уменьшить число на 10, если оно больше 9
            if (counter > 9) {
                counter = counter - 10
                updateCounterText()
            }
            else
            {
                counter = 0
                updateCounterText()
            }
        }

        plus100Button.setOnClickListener {
            // Увеличить число на 100
            counter = counter + 100
            updateCounterText()
        }

        minus100Button.setOnClickListener {
            // Уменьшить число на 100, если оно больше 99
            if (counter > 99) {
                counter = counter - 100
                updateCounterText()
            }
            else
            {
                counter = 0
                updateCounterText()
            }
        }

        resetButton.setOnClickListener {
            // Сбросить число к нулю
            counter = 0
            updateCounterText()
        }

        updateCounterText()
    }

    override fun onPause() {
        super.onPause()
        // Сохранить значение счетчика в SharedPreferences при приостановке активности
        val editor = sharedPreferences.edit()
        editor.putInt("counter", counter)
        editor.apply()
    }

    private fun updateCounterText() {
        counterText.text = counter.toString()
    }
}
