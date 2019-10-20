package com.example.kotlintutorial_11

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.android.synthetic.main.activity_main.*

class CalculatorViewModel : ViewModel(){

    private var operand1: Double? = null
    private var pendingOperation = "="

    val result = MutableLiveData<String>()
    val newNumber = MutableLiveData<String>()
    val operation = MutableLiveData<String>()

    fun digitPressed(caption: String){
        if(newNumber.value != null) {
            newNumber.value = newNumber.value + caption
        }else{
            newNumber.value = caption
        }
    }

    fun operandPressed(op: String){
        try {
            val value = newNumber.value?.toDouble()
            if (value != null){
                performOperation(value, op)
            }
        } catch (e: NumberFormatException){
            newNumber.value = ""
        }
        pendingOperation = op
        operation.value = pendingOperation
    }

    fun negPressed(){
        val value = newNumber.value
        if (value == null || value.isEmpty()) {
            newNumber.value = "-"
        } else {
            try {
                var doubleValue = value.toDouble()
                doubleValue *= -1
                newNumber.value = doubleValue.toString()
            } catch (e: NumberFormatException) {
                newNumber.value = ""
            }
        }
    }

    private fun performOperation(value: Double, operation: String){
        if (operand1 == null){
            operand1 = value
        }else{
            if (pendingOperation == "="){
                pendingOperation = operation
            }

            when (pendingOperation){
                "=" -> operand1 = value
                "/" -> if (value == 0.0){
                    operand1 = Double.NaN
                }else{
                    operand1 = operand1!! / value
                }
                "*" -> operand1 = operand1!! * value
                "-" -> operand1 = operand1!! - value
                "+" -> operand1 = operand1!! + value
            }
        }

        result.value = operand1.toString()
        newNumber.value = ""
    }
}