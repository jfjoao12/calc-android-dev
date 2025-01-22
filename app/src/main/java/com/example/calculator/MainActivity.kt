package com.example.calculator

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.calculator.CalcOps

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btn_0 = findViewById<Button>(R.id.btn_0)
        val btn_1 = findViewById<Button>(R.id.btn_1)
        val btn_2 = findViewById<Button>(R.id.btn_2)
        val btn_3 = findViewById<Button>(R.id.btn_3)
        val btn_4 = findViewById<Button>(R.id.btn_4)
        val btn_5 = findViewById<Button>(R.id.btn_5)
        val btn_6 = findViewById<Button>(R.id.btn_6)
        val btn_7 = findViewById<Button>(R.id.btn_7)
        val btn_8 = findViewById<Button>(R.id.btn_8)
        val btn_9 = findViewById<Button>(R.id.btn_9)

        val btn_plus = findViewById<Button>(R.id.btn_plus)
        val btn_minus = findViewById<Button>(R.id.btn_minus)
        val btn_equal = findViewById<Button>(R.id.btn_equal)
        val btn_divide = findViewById<Button>(R.id.btn_divide)
        val btn_clear = findViewById<Button>(R.id.btn_clear)
        val btn_multiply = findViewById<Button>(R.id.btn_clear)

        val result = findViewById<TextView>(R.id.result)

        // Keep track of the expression as a string
        var currentExpression = ""

        // Buttons for numbers
        val numberButtons =
            listOf(btn_0, btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9)

        numberButtons.forEachIndexed { index, button ->
            button.setOnClickListener {
                // Append the number to the expression string
                currentExpression += index.toString()
                result.text = currentExpression
                println("Current: $currentExpression")

            }
        }

        // Buttons for operations
        val operationButtons = listOf(
            btn_plus to "+",
            btn_minus to "-",
            btn_multiply to "*",
            btn_divide to "/"
        )

        operationButtons.forEach { (button, operator) ->
            button.setOnClickListener {
                // Append the operator to the expression string
                currentExpression += operator
                result.text = currentExpression
                println("Current: $currentExpression")

            }
        }


        println("CurrentExpression: $currentExpression")
    }

    private fun initializeViews() {

    }
}