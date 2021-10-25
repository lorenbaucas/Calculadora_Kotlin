package com.example.calculadoracheta

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    var operation = true //Concatena
    var previousNum = "" //Resetea
    var operator = "+"


    //Deshabilita el igual al principio
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bEqual.isClickable = false
    }

    fun number(view: View) {
        if (operation) {
            numText.text = "" //Donde van los numeros
            equalText.text = "" //Resultado
        }
        operation = false
        var btClickValue = numText.text.toString()
        var btPressed = view as Button
        when (btPressed.id) {
            b0.id -> {
                btClickValue += "0"
            }
            b1.id -> {
                btClickValue += "1"
            }
            b2.id -> {
                btClickValue += "2"
            }
            b3.id -> {
                btClickValue += "3"
            }
            b4.id -> {
                btClickValue += "4"
            }
            b5.id -> {
                btClickValue += "5"
            }
            b6.id -> {
                btClickValue += "6"
            }
            b7.id -> {
                btClickValue += "7"
            }
            b8.id -> {
                btClickValue += "8"
            }
            b9.id -> {
                btClickValue += "9"
            }

            bDot.id -> {
                if (btClickValue.isNotEmpty()) {
                    btClickValue += "."
                } else {
                    btClickValue += "0."
                }
                bDot.isClickable = false
            }
        }
        numText.setText(btClickValue)
    }

    fun operator(view: View) {
        bDot.isClickable = true
        var bPressed = view as Button
        when (bPressed.id) {
            bAdd.id -> {
                operator = "+"
            }
            bSub.id -> {
                operator = "-"
            }
            bMul.id -> {
                operator = "x"
            }
            bDiv.id -> {
                operator = "/"
            }
        }
        previousNum = numText.text.toString()
        operation = true
        bEqual.isClickable = true
    }

    fun equal(view: View) {
        var newNum = numText.text.toString()
        var result = 0.0
        when (operator) {
            "+" -> {
                result = previousNum.toDouble() + newNum.toDouble()
            }
            "-" -> {
                result = previousNum.toDouble() - newNum.toDouble()
            }
            "x" -> {
                result = previousNum.toDouble() * newNum.toDouble()
                if (result.isInfinite() || result.isNaN()) {
                    result = 0.0;
                }
            }
            "/" -> {
                result = previousNum.toDouble() / newNum.toDouble()
                if (result.isInfinite() || result.isNaN()) {
                    result = 0.0
                    Toast.makeText(applicationContext, "No puedes dividir entre 0", Toast.LENGTH_LONG).show()
                }
            }
        }

        equalText.setText(previousNum + operator + newNum)
        numText.text = result.toString()
        operation = true
    }

    /*fun deleteLast(view: View) {
        val chain = numText.text.toString()
        if (chain.isNotEmpty()) {
            numText.text = chain.substring(0, chain.length - 1)
        }
    }
    */

    fun resetAll(view: View) {
        previousNum = 0.toString()
        previousNumLand = 0.toString()
        operator = ""
        operatorLand = ""
        equalText.text = ""
        numText.text = ""
        operation = true
        bDot.isClickable = true
        bEqual.isClickable = true
    }

    var operationLand = true
    var previousNumLand = ""
    var operatorLand = "+"

    //Funciones on click para el layout landscape

    fun landNum(view: View) {
        if (operationLand) {
            numText.text = ""
        }
        operationLand = false
        var value = numText.text.toString()
        var btPressed = view as Button
        when (btPressed.id) {
            b0.id -> {
                value += "0"
            }
            b1.id -> {
                value += "1"
            }
            b2.id -> {
                value += "2"
            }
            b3.id -> {
                value += "3"
            }
            b4.id -> {
                value += "4"
            }
            b5.id -> {
                value += "5"
            }
            b6.id -> {
                value += "6"
            }
            b7.id -> {
                value += "7"
            }
            b8.id -> {
                value += "8"
            }
            b9.id -> {
                value += "9"
            }
            bDot.id -> {
                if (value.isNotEmpty()) {
                    value += "."
                } else {
                    value += "0."
                }
                bDot.isClickable = false
            }
        }
        numText.setText(value)
    }

    fun numLetter(view: View) {
        if (operationLand) {
            numText.text = ""
        }
        operationLand = false
        var btClickValue = numText.text.toString()
        var btPressed = view as Button
        when (btPressed.id) {
            bA.id -> {
                btClickValue += "A"
            }
            bB.id -> {
                btClickValue += "B"
            }
            bC.id -> {
                btClickValue += "C"
            }
            bD.id -> {
                btClickValue += "D"
            }
            bE.id -> {
                btClickValue += "E"
            }
            bF.id -> {
                btClickValue += "F"
            }
        }
        numText.setText(btClickValue)
    }

    fun landOperator(view: View) {
        bDot.isClickable = true
        var bPressed = view as Button

        if (!bBin.isEnabled) {
            var num = numText.text.toString().toLong()
            var x = binaryToDecimal(num)
            previousNumLand = x.toString()
            numText.setText("0")
        }
        if (!bHex.isEnabled) {
            var x = hexadecimalToDecimal(numText.text.toString());
            previousNumLand = x.toString()
            numText.setText("0")
        }
        if (!bDec.isEnabled) {
            previousNumLand = numText.text.toString()
            numText.setText("0")
        }
        when (bPressed.id) {
            bAdd.id -> {
                operatorLand = "+"
            }
            bSub.id -> {
                operatorLand = "-"
            }
            bMul.id -> {
                operatorLand = "x"
            }
            bDiv.id -> {
                operatorLand = "/"
            }
        }
        operationLand = true
        bEqual.isClickable = true
    }

    fun landEqual(view: View) {
        var num = numText.text.toString()
        var result = 0
        try {
            var newNum = numText.text.toString()
            if (!bBin.isEnabled) {
                var number = numText.text.toString().toLong()
                var x = binaryToDecimal(number)
                newNum = x.toString()
            }
            if (!bHex.isEnabled) {
                var x = hexadecimalToDecimal(numText.text.toString());
                newNum = x.toString()
            }
            if (!bDec.isEnabled) {
                newNum = numText.text.toString()
            }

            when (operatorLand) {
                "+" -> {
                    result = previousNumLand.toInt() + newNum.toInt()
                }
                "-" -> {
                    result = previousNumLand.toInt() - newNum.toInt()
                }
                "x" -> {
                    result = previousNumLand.toInt() * newNum.toInt()
                }
                "/" -> {
                    result = previousNumLand.toInt() / newNum.toInt()
                }
            }
            if (!bBin.isEnabled) {
                var bin = result.toString()
                equalText.setText(newNum + operatorLand + num)
                var equalBin = Integer.toBinaryString(bin.toInt())
                numText.setText(equalBin.toString())
                equalText.setText(previousNumLand + operatorLand + num)
            }
            if (!bHex.isEnabled) {
                var a = result.toString()
                var r = Integer.toHexString(a.toInt())
                numText.setText(r)
                equalText.setText(previousNumLand + operatorLand + num)
            }
            if (!bDec.isEnabled) {
                equalText.setText(result.toString())
                numText.setText(previousNumLand + operatorLand +num)
            }
            operationLand = true
            operation = true
        } catch (ex: NumberFormatException) {
            num = numText.text.toString()
        }catch (ae: ArithmeticException) {
            Toast.makeText(applicationContext, "No puedes dividir entre 0", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
        }
    }

    fun resetAllLand(view: View) {
        previousNum = 0.toString()
        previousNumLand = 0.toString()
        operator = ""
        operatorLand = ""
        equalText.text = ""
        numText.text = ""
        operation = true
        bDot.isClickable = true
        bEqual.isClickable = true
    }
    /*
    fun deleteLastLand(view: View) {
        val chain = numText.text.toString()
        if (chain.isNotEmpty()) {
            numText.text = chain.substring(0, chain.length - 1)
        }
    }
    */
    fun binaryToDecimal(num: Long): Int {
        var num = num
        var decimalNumber = 0
        var i = 0
        var remainder: Long

        while (num.toInt() != 0) {
            remainder = num % 10
            num /= 10
            decimalNumber += (remainder * Math.pow(2.0, i.toDouble())).toInt()
            ++i
        }
        return decimalNumber
    }

    fun hexadecimalToDecimal(hexadecimal: String): Long {
        var decimal: Long = 0
        var potencia = 0
        for (x in hexadecimal.length - 1 downTo 0) {
            val valor: Int = charHexadecimalToDecimal(hexadecimal[x])
            val elevado = Math.pow(16.0, potencia.toDouble()).toLong() * valor
            decimal += elevado
            potencia++
        }
        return decimal
    }

    fun charHexadecimalToDecimal(character: Char): Int {
        return when (character) {
            'A' -> 10
            'B' -> 11
            'C' -> 12
            'D' -> 13
            'E' -> 14
            'F' -> 15
            else -> character.toString().toInt()
        }
    }

    fun binary(view: View) {

        Toast.makeText(applicationContext, "Modo binario activado", Toast.LENGTH_SHORT).show()

        b2.visibility = View.INVISIBLE
        b3.visibility = View.INVISIBLE
        b4.visibility = View.INVISIBLE
        b5.visibility = View.INVISIBLE
        b6.visibility = View.INVISIBLE
        b7.visibility = View.INVISIBLE
        b8.visibility = View.INVISIBLE
        b9.visibility = View.INVISIBLE

        bA.visibility = View.INVISIBLE
        bB.visibility = View.INVISIBLE
        bC.visibility = View.INVISIBLE
        bD.visibility = View.INVISIBLE
        bE.visibility = View.INVISIBLE
        bF.visibility = View.INVISIBLE

        if (!bDec.isEnabled) {
            if (numText.text == "") {
                numText.text = "0"
            } else {
                try {
                    var toBin = numText.text.toString().toLong()
                    numText.setText(Integer.toBinaryString(toBin.toInt()))
                } catch (ae: NumberFormatException) { Toast.makeText(applicationContext, "Error", Toast.LENGTH_LONG).show()
                    numText.text = "0"
                }
            }
        }

        if (!bHex.isEnabled) {
            try {
                var toDec = hexadecimalToDecimal(numText.text.toString())
                numText.setText(Integer.toBinaryString(toDec.toInt()))
            } catch (ae: NumberFormatException) { Toast.makeText(applicationContext, "Error", Toast.LENGTH_LONG).show()
                numText.text = "0"
            }
        }

        bBin.setEnabled(false)
        bDec.setEnabled(true)
        bHex.setEnabled(true)
    }

    fun decimal(view: View) {
        Toast.makeText(applicationContext, "Modo decimal activado", Toast.LENGTH_SHORT).show()

        b2.visibility = View.VISIBLE
        b3.visibility = View.VISIBLE
        b4.visibility = View.VISIBLE
        b5.visibility = View.VISIBLE
        b6.visibility = View.VISIBLE
        b7.visibility = View.VISIBLE
        b8.visibility = View.VISIBLE
        b9.visibility = View.VISIBLE

        bA.visibility = View.INVISIBLE
        bB.visibility = View.INVISIBLE
        bC.visibility = View.INVISIBLE
        bD.visibility = View.INVISIBLE
        bE.visibility = View.INVISIBLE
        bF.visibility = View.INVISIBLE

        if (!bBin.isEnabled) {
            try {
                var toBin = numText.text.toString().toLong()
                var a = binaryToDecimal(toBin)
                numText.setText(a.toString())
            } catch (ae: NumberFormatException) { Toast.makeText(applicationContext, "Numero demasiado largo", Toast.LENGTH_LONG).show()
                numText.text = "0"
            }
        }
        if (!bHex.isEnabled) {
            try {
                var toDec = hexadecimalToDecimal(numText.text.toString())
                numText.setText(toDec.toString())
            } catch (ae: NumberFormatException) { Toast.makeText(applicationContext, "Numero demasiado largo", Toast.LENGTH_LONG).show()
                numText.text = "0"
            }
        }
        bBin.setEnabled(true)
        bDec.setEnabled(false)
        bHex.setEnabled(true)
    }

    fun hexa(view: View) {
        Toast.makeText(applicationContext, "Modo hexadecimal activado", Toast.LENGTH_SHORT).show()

        b2.visibility = View.VISIBLE
        b3.visibility = View.VISIBLE
        b4.visibility = View.VISIBLE
        b5.visibility = View.VISIBLE
        b6.visibility = View.VISIBLE
        b7.visibility = View.VISIBLE
        b8.visibility = View.VISIBLE
        b9.visibility = View.VISIBLE

        bA.visibility = View.VISIBLE
        bB.visibility = View.VISIBLE
        bC.visibility = View.VISIBLE
        bD.visibility = View.VISIBLE
        bE.visibility = View.VISIBLE
        bF.visibility = View.VISIBLE

        if (!bBin.isEnabled) {

            try {
                var toBin = numText.text.toString().toLong()
                var toDec = binaryToDecimal(toBin)
                numText.setText(Integer.toHexString(toDec))
            } catch (ae: NumberFormatException) { Toast.makeText(applicationContext, "Número demasiado largo", Toast.LENGTH_LONG).show()
                numText.text = "0"
            }
        }
        if (!bDec.isEnabled) {
            try {
                var toDec = hexadecimalToDecimal(numText.text.toString())
                numText.setText(Integer.toHexString(toDec.toInt()))
            } catch (ae: NumberFormatException) { Toast.makeText(applicationContext, "Número demasiado largo", Toast.LENGTH_LONG).show()
                numText.text = "0"
            }
        }
        bBin.setEnabled(true)
        bDec.setEnabled(true)
        bHex.setEnabled(false)
    }
}