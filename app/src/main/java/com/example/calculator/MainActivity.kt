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

        val CalcOps = CalcOps()

        // Ask about val names convention
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

        val result = findViewById<TextView>(R.id.result)

        val numberButtons =
            listOf(btn_0, btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9)

        var number_1 = 0
        var number_2 = 0
        var operation_result = ""
        var selectedOperator = ""

        btn_clear.setOnClickListener {
            if (result.text.isEmpty()) {
                Toast.makeText(this, "Nothing to clear!", Toast.LENGTH_SHORT).show()
            } else {
                result.text = ""
            }


        }

        // Buttons for operations
        val operationButtonsText = listOf(
            btn_plus to "+",
            btn_minus to "-",
            btn_multiply to "*",
            btn_divide to "/"
        )

        numberButtons.forEachIndexed { index, button ->
            button.setOnClickListener {
                result.text = result.text.toString() + index.toString()
            }
        }


        operationButtonsText.forEach{ (button, operator) ->
            button.setOnClickListener {
                try {
                    number_1 = result.text.toString().toInt()
                    selectedOperator = operator
                    result.text = (result.text.toString() + operator)
                    Toast.makeText(this@MainActivity, "First number is $number_1", Toast.LENGTH_SHORT).show()
                } catch (e: Exception){
                    Toast.makeText(this@MainActivity, "Can't add 2 consecutive operations!", Toast.LENGTH_SHORT).show()

                }

            }
        }

        // EQUAL WITH NO NUMBERS SHOULD GIVE ERROR!
        btn_equal.setOnClickListener {

            if (result.text.isEmpty()) {
                Toast.makeText(this, "Please input a number!", Toast.LENGTH_SHORT).show()
            }
            else {
                try {
                    when (selectedOperator) {
                        "+" -> {
                            number_2 = result.text.split('+')[1].toInt()
                            operation_result = (number_1 + number_2).toString()
                        }
                        "-" -> {
                            number_2 = result.text.split('-')[1].toInt()
                            operation_result = (number_1 - number_2).toString()
                        }
                        "*" -> {
                            number_2 = result.text.split('*')[1].toInt()
                            operation_result = (number_1 * number_2).toString()
                        }
                        "/" -> {
                            number_2 = result.text.split('/')[1].toInt()
                            if (number_2 == 0) {
                                Toast.makeText(this, "Cannot divide by zero!", Toast.LENGTH_SHORT).show()
                                return@setOnClickListener // Exit early to prevent a crash
                            }
                            operation_result = (number_1 / number_2).toString()
                        }
                    }
                } catch (e: Exception){
                    Toast.makeText(this, "You need two numbers to do math!", Toast.LENGTH_SHORT).show()
                }

                if (operation_result.isNotEmpty()){
                    result.text = operation_result
                }
                Toast.makeText(this@MainActivity, "Second number is $number_2", Toast.LENGTH_SHORT).show()
            }
//            result.text = (number_1 + number_2).toString()
        }
    }

    private fun initializeViews() {

    }
}