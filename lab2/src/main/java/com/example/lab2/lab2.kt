package com.example.lab2

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.nio.charset.Charset


class lab2 : AppCompatActivity() {
    private val WIDTH_NAME = "WIDTH_NAME"
    private val HEIGHT_NAME = "HEIGHT_NAME"
    private val AMOUNT_NAME = "AMOUNT_NAME"
    private val CALCULATE_RESULT_NAME = "CALCULATE_RESULT_NAME"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lab2)
    }

    fun OnClickCalculation(view: View)
    {
        val price = 2;
        val sizeWidth = findViewById<EditText>(R.id.SizeWidth).text.toString();
        val width: Int = sizeWidth.toIntOrNull() ?: 0
        val sizeHeight = findViewById<EditText>(R.id.SizeHeight).text.toString();
        val height: Int = sizeHeight.toIntOrNull() ?: 0

        val numberText = findViewById<EditText>(R.id.Number).text.toString();
        val number = numberText.toIntOrNull() ?: 0

        val colorСorrection = findViewById<CheckBox>(R.id.СolorСorrectioncheckBox).isChecked
        val calvulateResultText = findViewById<TextView>(R.id.CalculationResult)

        var colorСorrectionPrice = 1f;
        if(colorСorrection)
        {
            colorСorrectionPrice = 1.5f;
        }

        val resultValue = (price * (width * height)) * number * colorСorrectionPrice;
        calvulateResultText.text = resultValue.toString();

        try
        {
            WriteFileInTextView(sizeWidth, WIDTH_NAME);
            WriteFileInTextView(sizeHeight, HEIGHT_NAME);
            WriteFileInTextView(numberText, AMOUNT_NAME);
            WriteFileInTextView(resultValue.toString(), CALCULATE_RESULT_NAME);
            Toast.makeText(
                this, "Файл сохранен",
                Toast.LENGTH_SHORT
            ).show()
        }
        catch (ex: IOException) {
            Toast.makeText(
                this,
                ex.message, Toast.LENGTH_SHORT
            ).show()
        }
    }

    fun OnClickReadFile(view: View)
    {
        try
        {
            ReadFileInTextView(R.id.SizeWidth, WIDTH_NAME)
            ReadFileInTextView(R.id.SizeHeight, HEIGHT_NAME)
            ReadFileInTextView(R.id.Number, AMOUNT_NAME)

            val fileInputStream = openFileInput(CALCULATE_RESULT_NAME)
            val inputStreamReader = InputStreamReader(fileInputStream, Charset.forName("UTF-8"))
            val bufferedReader = BufferedReader(inputStreamReader)
            val text = bufferedReader.readLine()
            bufferedReader.close()
            val view = findViewById<TextView>(R.id.CalculationResult)
            view.setText(text)

            Toast.makeText(
                this, "Файл прочитан",
                Toast.LENGTH_SHORT
            ).show()
        }
        catch (ex: IOException) {
            Toast.makeText(
                this,
                ex.message, Toast.LENGTH_SHORT
            ).show()
        }
    }

    fun ReadFileInTextView(id: Int, fileName: String)
    {
        val fileInputStream = openFileInput(fileName)
        val inputStreamReader = InputStreamReader(fileInputStream, Charset.forName("UTF-8"))
        val bufferedReader = BufferedReader(inputStreamReader)
        val text = bufferedReader.readLine()
        bufferedReader.close()
        val view = findViewById<EditText>(id)
        view.setText(text)
    }

    fun WriteFileInTextView(value: String, fileName: String)
    {
        val fileOutputStream = openFileOutput(fileName, Context.MODE_PRIVATE)
        val outputStreamWriter = OutputStreamWriter(fileOutputStream)
        outputStreamWriter.write(value)
        outputStreamWriter.close()
    }
}

