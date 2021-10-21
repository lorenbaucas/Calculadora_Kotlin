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
        var btPressed = view as Button
        when (btPressed.id) {
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
                    Toast.makeText(
                        applicationContext,
                        "No puedes dividir entre 0",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }

        equalText.setText(previousNum + operator + newNum)
        numText.text = result.toString()
        operation = true
    }

    fun deleteLast(view: View) {
        val chain = numText.text.toString()
        if (chain.isNotEmpty()) {
            numText.text = chain.substring(0, chain.length - 1)
        }
    }

    fun resetAll(view: View) {

        previousNum = 0.toString()
        numText.text = "0"
        equalText.text = ""
        operation = true
        bEqual.isClickable = true
    }

    var isNewOpLand = true
    var previousNumLand = ""
    var operatorLand = "+"

    //funciones on click para el layout landscape

    fun landNum(view: View) {
        if (isNewOpLand) {
            numText.text = ""
        }
        isNewOpLand = false
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

    fun numLetter(view: View) {
        if (isNewOpLand) {
            numText.text = ""
        }
        isNewOpLand = false
        var btClickValue = numText.text.toString()
        var btPressed = view as Button
        when (btPressed.id) {
            bA.id -> {
                btClickValue += "a"
            }
            bB.id -> {
                btClickValue += "b"
            }
            bC.id -> {
                btClickValue += "c"
            }
            bD.id -> {
                btClickValue += "d"
            }
            bE.id -> {
                btClickValue += "e"
            }
            bF.id -> {
                btClickValue += "f"
            }
        }
        numText.setText(btClickValue)
    }

    fun landOperator(view: View) {
        bDot.isClickable = true
        var btPressed = view as Button

        if (!bBin.isEnabled) {
            var numero = numText.text.toString().toLong()
            var a = binaryToDecimal(numero)
            previousNumLand = a.toString()
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
        when (btPressed.id) {
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
        isNewOpLand = true
        bEqual.isClickable = true
    }

    fun landEqual(view: View) {
        var currentNum = numText.text
        var result = 0
        try {
            var newNum = numText.text.toString()
            if (!bBin.isEnabled) {
                var numero = numText.text.toString().toLong()
                var x = binaryToDecimal(numero)
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

                var resBin = Integer.toBinaryString(bin.toInt())
                numText.setText(resBin.toString())
            }
            if (!bHex.isEnabled) {
                var e = result.toString()
                var r = Integer.toHexString(e.toInt())
                numText.setText(r)
            }
            if (!bDec.isEnabled) {
                numText.setText(result.toString())
            }

            isNewOpLand = true
        } catch (ae: ArithmeticException) {
            Toast.makeText(applicationContext, "No puedes dividir entre 0", Toast.LENGTH_SHORT)
                .show()
            result = 0

        } catch (ex: NumberFormatException) {
            currentNum = numText.text
        } catch (e: Exception) {
        }

    }

    fun resetAllLand(view: View) {
        previousNum = 0.toString()
        numText.text = "0"
        operation = true
    }

    fun deleteLastLand(view: View) {
        val chain = numText.text.toString()
        if (chain.isNotEmpty()) {
            numText.text = chain.substring(0, chain.length - 1)
        }
    }

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

    fun charHexadecimalToDecimal(caracter: Char): Int {
        return when (caracter) {
            'a' -> 10
            'b' -> 11
            'c' -> 12
            'd' -> 13
            'e' -> 14
            'f' -> 15
            else -> caracter.toString().toInt()
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
                    var toBi = numText.text.toString().toLong()
                    numText.setText(Integer.toBinaryString(toBi.toInt()))
                } catch (ae: NumberFormatException) {
                    Toast.makeText(applicationContext, "Número demasiado largo", Toast.LENGTH_LONG)
                        .show()
                    numText.text = "0"
                }

            }
        }

        if (!bHex.isEnabled) {
            try {
                var toDec = hexadecimalToDecimal(numText.text.toString())
                numText.setText(Integer.toBinaryString(toDec.toInt()))
            } catch (ae: NumberFormatException) {
                Toast.makeText(applicationContext, "Número demasiado largo", Toast.LENGTH_LONG)
                    .show()
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
                var toBi = numText.text.toString().toLong()
                var a = binaryToDecimal(toBi)
                numText.setText(a.toString())
            } catch (ae: NumberFormatException) {
                Toast.makeText(applicationContext, "Numero demasiado largo", Toast.LENGTH_LONG)
                    .show()
                numText.text = "0"
            }
        }
        if (!bHex.isEnabled) {
            try {
                var toDec = hexadecimalToDecimal(numText.text.toString())
                numText.setText(toDec.toString())
            } catch (ae: NumberFormatException) {
                Toast.makeText(applicationContext, "Numero demasiado largo", Toast.LENGTH_LONG)
                    .show()
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
                var toBi = numText.text.toString().toLong()
                var toDec = binaryToDecimal(toBi)
                numText.setText(Integer.toHexString(toDec))
            } catch (ae: NumberFormatException) {
                Toast.makeText(applicationContext, "Número demasiado largo", Toast.LENGTH_LONG)
                    .show()
                numText.text = "0"
            }
        }
        if (!bDec.isEnabled) {
            try {
                var toDec = hexadecimalToDecimal(numText.text.toString())
                numText.setText(Integer.toHexString(toDec.toInt()))
            } catch (ae: NumberFormatException) {
                Toast.makeText(applicationContext, "Número demasiado largo", Toast.LENGTH_LONG)
                    .show()
                numText.text = "0"
            }
        }
        bBin.setEnabled(true)
        bDec.setEnabled(true)
        bHex.setEnabled(false)
    }
}