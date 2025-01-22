package com.example.calculator

class CalcOps {
    fun adding (number_one: Int, number_two: Int) =  number_one + number_two

    fun subtracting (number_one: Int, number_two: Int) = number_one - number_two

    fun dividing (number_one: Int, number_two: Int) = number_one / number_two

    fun multiplying (number_one: Int, number_two: Int) = number_one * number_two

    fun String.isNumeric(): Boolean {
        return this.toDoubleOrNull() != null
    }
}
