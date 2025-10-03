package com.arnab.calculator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var resultTextView: TextView
    private lateinit var previousCalculationTextView: TextView

    private var firstNumber = 0.0
    private var operation = ""
    private var isNewOperation = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        resultTextView = findViewById<TextView>(R.id.resultTextView)
        previousCalculationTextView = findViewById<TextView>(R.id.previousCalculationTextView)

        val button0 = findViewById<Button>(R.id.btn0)
        val button1 = findViewById<Button>(R.id.btn1)
        val button2 = findViewById<Button>(R.id.btn2)
        val button3 = findViewById<Button>(R.id.btn3)
        val button4 = findViewById<Button>(R.id.btn4)
        val button5 = findViewById<Button>(R.id.btn5)
        val button6 = findViewById<Button>(R.id.btn6)
        val button7 = findViewById<Button>(R.id.btn7)
        val button8 = findViewById<Button>(R.id.btn8)
        val button9 = findViewById<Button>(R.id.btn9)

        val add: Button = findViewById(R.id.btnAdd)
        val subtract: Button = findViewById(R.id.btnSubtract)
        val multiply: Button = findViewById(R.id.btnMultiply)
        val divide: Button = findViewById(R.id.btnDevide)

        val percent: Button = findViewById(R.id.btnPercent)
        val backSpace: Button = findViewById(R.id.btnBackspace)
        val equal: Button = findViewById(R.id.btnEqual)
        val clear: Button = findViewById(R.id.btnClear)

        button0.setOnClickListener { appendNumber("0") }
        button1.setOnClickListener { appendNumber("1") }
        button2.setOnClickListener { appendNumber("2") }
        button3.setOnClickListener { appendNumber("3") }
        button4.setOnClickListener { appendNumber("4") }
        button5.setOnClickListener { appendNumber("5") }
        button6.setOnClickListener { appendNumber("6") }
        button7.setOnClickListener { appendNumber("7") }
        button8.setOnClickListener { appendNumber("8") }
        button9.setOnClickListener { appendNumber("9") }

        add.setOnClickListener { setOperation("+") }
        subtract.setOnClickListener { setOperation("-") }
        multiply.setOnClickListener { setOperation("×") }
        divide.setOnClickListener { setOperation("÷") }
        percent.setOnClickListener { setOperation("%") }

        equal.setOnClickListener { calculateResult() }
        clear.setOnClickListener { clearCalculator() }
        backSpace.setOnClickListener { deleteNum() }

    }

    private fun deleteNum() {
        if (resultTextView.text.isNotEmpty() && resultTextView.text != "0.0" && resultTextView.text != "Error") {
            resultTextView.text = resultTextView.text.dropLast(1)
        } else {
            Toast.makeText(this, "Invalid Operation", Toast.LENGTH_SHORT).show()
        }
    }

    private fun clearCalculator() {
        firstNumber = 0.0
        operation = ""
        isNewOperation = true
        resultTextView.text = "0.0"
        previousCalculationTextView.text = ""
    }

    private fun calculateResult() {
        try{
            val secondNumber = resultTextView.text.toString().toDouble()
            val result = when (operation) {
                "+" -> firstNumber + secondNumber
                "-" -> firstNumber - secondNumber
                "×" -> firstNumber * secondNumber
                "÷" -> firstNumber / secondNumber
                else -> secondNumber
            }
            previousCalculationTextView.text = "$firstNumber $operation $secondNumber"
            resultTextView.text = result.toString()
            isNewOperation = true
        } catch (e: Exception) {
            resultTextView.text = "Error"
        }
    }

    private fun appendNumber( number: String ){
        if (isNewOperation) {
            resultTextView.text = number
            isNewOperation = false
        } else {
            resultTextView.text = "${resultTextView.text}$number"
        }
//        resultTextView.text = "5"
    }

    private fun setOperation( op: String ){
        firstNumber = resultTextView.text.toString().toDouble()
        operation = op
        isNewOperation = true
        previousCalculationTextView.text = "$firstNumber $operation"
        resultTextView.text = "0.0"
    }
}