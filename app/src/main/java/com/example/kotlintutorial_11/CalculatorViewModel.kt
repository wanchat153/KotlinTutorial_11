package com.example.kotlintutorial_11

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.android.synthetic.main.activity_main.*

class CalculatorViewModel : ViewModel(){

    private var operand1: Double? = null
    private var pendingOperation = "="

    val result = MutableLiveData<String>()
    val newNumber = MutableLiveData<String>()

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