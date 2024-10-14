package com.example.caculator_2

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var display: TextView
    private var expression: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_2)

        // Ánh xạ các button và màn hình hiển thị
        display = findViewById(R.id.display)

        val buttonCe: Button = findViewById(R.id.button_ce)
        val buttonC: Button = findViewById(R.id.button_c)
        val buttonBs: Button = findViewById(R.id.button_bs)
        val buttonDivide: Button = findViewById(R.id.button_divide)
        val buttonMultiply: Button = findViewById(R.id.button_multiply)
        val buttonSubtract: Button = findViewById(R.id.button_subtract)
        val buttonAdd: Button = findViewById(R.id.button_add)
        val buttonEquals: Button = findViewById(R.id.button_equals)

        // Các nút số
        val numberButtons = listOf(
            findViewById<Button>(R.id.button_0),
            findViewById<Button>(R.id.button_1),
            findViewById<Button>(R.id.button_2),
            findViewById<Button>(R.id.button_3),
            findViewById<Button>(R.id.button_4),
            findViewById<Button>(R.id.button_5),
            findViewById<Button>(R.id.button_6),
            findViewById<Button>(R.id.button_7),
            findViewById<Button>(R.id.button_8),
            findViewById<Button>(R.id.button_9)
        )

        // Gán sự kiện cho các nút số
        numberButtons.forEachIndexed { index, button ->
            button.setOnClickListener {
                onNumberClick(index)
            }
        }

        // Gán sự kiện cho các nút chức năng
        buttonCe.setOnClickListener { clearEntry() }
        buttonC.setOnClickListener { clearAll() }
        buttonBs.setOnClickListener { backspace() }
        buttonDivide.setOnClickListener { onOperatorClick("/") }
        buttonMultiply.setOnClickListener { onOperatorClick("x") }
        buttonSubtract.setOnClickListener { onOperatorClick("-") }
        buttonAdd.setOnClickListener { onOperatorClick("+") }
        buttonEquals.setOnClickListener { calculateResult() }
    }

    // Xử lý khi nhấn nút số
    private fun onNumberClick(number: Int) {
        expression += number.toString()
        display.text = expression
    }

    // Xử lý khi nhấn toán tử
    private fun onOperatorClick(op: String) {
        if (expression.isNotEmpty() && !isLastCharOperator()) {
            expression += " $op "
            display.text = expression
        }
    }

    // Kiểm tra xem ký tự cuối có phải là toán tử hay không
    private fun isLastCharOperator(): Boolean {
        return expression.last().toString().matches(Regex("[+\\-*/]"))
    }

    // Tính toán kết quả khi nhấn "="
    private fun calculateResult() {
        try {
            // Loại bỏ khoảng trắng và tính toán biểu thức
            val result = eval(expression.replace("\\s".toRegex(), ""))
            display.text = result.toString()
            expression = result.toString() // Lưu lại kết quả để tiếp tục tính toán
        } catch (e: Exception) {
            display.text = "Error"
            expression = ""
        }
    }

    // Hàm eval tính toán biểu thức
    private fun eval(expr: String): Double {
        return object : Any() {
            var pos = -1
            var ch = 0

            fun nextChar() {
                ch = if (++pos < expr.length) expr[pos].toInt() else -1
            }

            fun eat(charToEat: Int): Boolean {
                while (ch == ' '.toInt()) nextChar()
                if (ch == charToEat) {
                    nextChar()
                    return true
                }
                return false
            }

            fun parse(): Double {
                nextChar()
                val x = parseExpression()
                if (pos < expr.length) throw RuntimeException("Unexpected: " + ch.toChar())
                return x
            }

            // Grammar:
            // expression = term | expression `+` term | expression `-` term
            // term = factor | term `*` factor | term `/` factor
            // factor = `+` factor | `-` factor | `(` expression `)` | number
            //        | functionName factor | factor `^` factor

            fun parseExpression(): Double {
                var x = parseTerm()
                while (true) {
                    when {
                        eat('+'.toInt()) -> x += parseTerm() // addition
                        eat('-'.toInt()) -> x -= parseTerm() // subtraction
                        else -> return x
                    }
                }
            }

            fun parseTerm(): Double {
                var x = parseFactor()
                while (true) {
                    when {
                        eat('x'.toInt()) -> x *= parseFactor() // multiplication
                        eat('/'.toInt()) -> x /= parseFactor() // division
                        else -> return x
                    }
                }
            }

            fun parseFactor(): Double {
                if (eat('+'.toInt())) return parseFactor() // unary plus
                if (eat('-'.toInt())) return -parseFactor() // unary minus

                var x: Double
                val startPos = pos
                if (eat('('.toInt())) { // parentheses
                    x = parseExpression()
                    eat(')'.toInt())
                } else if (ch >= '0'.toInt() && ch <= '9'.toInt() || ch == '.'.toInt()) { // numbers
                    while (ch >= '0'.toInt() && ch <= '9'.toInt() || ch == '.'.toInt()) nextChar()
                    x = expr.substring(startPos, pos).toDouble()
                } else {
                    throw RuntimeException("Unexpected: " + ch.toChar())
                }

                return x
            }
        }.parse()
    }

    // Xóa kết quả hiện tại (CE)
    private fun clearEntry() {
        display.text = "0"
        expression = ""
    }

    // Xóa tất cả (C)
    private fun clearAll() {
        expression = ""
        display.text = "0"
    }

    // Xóa ký tự cuối (Backspace)
    private fun backspace() {
        if (expression.isNotEmpty()) {
            expression = expression.dropLast(1)
            display.text = if (expression.isEmpty()) "0" else expression
        }
    }
}

