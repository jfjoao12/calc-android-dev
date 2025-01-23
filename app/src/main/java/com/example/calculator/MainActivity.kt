package com.example.calculator

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
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
        val btn_multiply = findViewById<Button>(R.id.btn_multiply)
        val btn_delete = findViewById<Button>(R.id.btn_delete)
        val btn_dot = findViewById<Button>(R.id.btn_dot)

        val numbersListShow = findViewById<TextView>(R.id.numbersList)
        val operationsListShow = findViewById<TextView>(R.id.operationsList)

        val result = findViewById<TextView>(R.id.result)

        val buttons =
            listOf(
                btn_0 to "0",
                btn_1 to "1",
                btn_2 to "2",
                btn_3 to "3",
                btn_4 to "4",
                btn_5 to "5",
                btn_6 to "6",
                btn_7 to "7",
                btn_8 to "8",
                btn_9 to "9",
                btn_plus to "+",
                btn_minus to "-",
                btn_multiply to "*",
                btn_divide to "/",
                btn_dot to "."
                )

        val operatorsList = arrayOf('+', '-', '*', '/')


        buttons.forEach { (button, value) ->
            button.setOnClickListener {
                result.text = result.text.toString() + value
            }
        }
        val operationsList = mutableListOf<Char>()
        val numbersList = mutableListOf<String>()

        btn_equal.setOnClickListener {

            var catchNumber = ""
            numbersList.clear()
            operationsList.clear()

            for (char in result.text) {
                if (char != '+' && char != '-' && char != '/' && char != '*') {
                    catchNumber += char
                } else {
                    numbersList.add(catchNumber)
                    operationsList.add(char)
                    catchNumber = ""

                }
            }
            // Store last number
            if (catchNumber.isNotEmpty()) {
                numbersList.add(catchNumber)
            }

            var n = 0
            var expression = ""
            var expressionResult = numbersList[0].toDouble()

            try {
                for (i in operationsList.indices) {
                    val num = numbersList[i + 1].toDouble()

                    when (operationsList[i]) {
                        '+' ->
                            expressionResult += num
                        '-' ->
                            expressionResult -= num
                        '/' ->
                            if (num != 0.0 || num.toInt() != 0) {
                                expressionResult /= num
                            } else {
                                Toast.makeText(this@MainActivity, "Cannot divide by zero!", Toast.LENGTH_SHORT).show()
                                operationsList.clear()
                                numbersList.clear()
                            }
                        '*' -> expressionResult *= num
                    }
                    expression += numbersList[i] + operatorsList[i] + numbersList[i + 1]
                }
            } catch (e: Exception) {
                Toast.makeText(this@MainActivity, "Invalid Expression!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (expressionResult % 1 == 0.0) {
                result.text = expressionResult.toInt().toString()
                Toast.makeText(this@MainActivity, "Caught!", Toast.LENGTH_SHORT).show()

            }
            else {
                result.text = expressionResult.toString()

            }

            numbersListShow.text = "${numbersList}"
            operationsListShow.text = "${operationsList}"
            Toast.makeText(this@MainActivity, "Expression is: $expression", Toast.LENGTH_SHORT).show()

        }

        btn_delete.setOnClickListener {
            result.text = result.text.dropLast(1)
        }

        btn_clear.setOnClickListener{
            result.text = ""
            numbersList.clear()
            operationsList.clear()
        }
    }

    private fun initializeViews() {

    }
}