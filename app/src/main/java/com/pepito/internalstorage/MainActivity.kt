package com.pepito.internalstorage

import android.os.Bundle
import androidx.activity.ComponentActivity
import android.content.Context
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.io.*

class MainActivity : ComponentActivity() {
    private lateinit var editText: EditText
    private lateinit var saveButton: Button
    private lateinit var retrieveButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.edit_text)
        saveButton = findViewById(R.id.save_button)
        retrieveButton = findViewById(R.id.load_button)

        saveButton.setOnClickListener {
            saveTextToFile()
        }

        retrieveButton.setOnClickListener {
            retrieveTextFromFile()
        }
    }

    private fun saveTextToFile() {
        val text = editText.text.toString()
        val file = "myFile.txt"

        try {
            val outputStreamWriter = OutputStreamWriter(openFileOutput(file, Context.MODE_PRIVATE))
            outputStreamWriter.write(text)
            outputStreamWriter.close()
            Toast.makeText(this, "Text saved to file", Toast.LENGTH_SHORT).show()
        } catch (e: IOException) {
            e.printStackTrace()
            Toast.makeText(this, "Error saving text", Toast.LENGTH_SHORT).show()
        }
    }

    private fun retrieveTextFromFile() {
        val file = "myFile.txt"

        try {
            val inputStream = openFileInput(file)
            val inputStreamReader = InputStreamReader(inputStream)
            val bufferedReader = BufferedReader(inputStreamReader)
            val stringBuilder = StringBuilder()
            var text: String? = null
            while (run {
                    text = bufferedReader.readLine()
                    text
                } != null) {
                stringBuilder.append(text)
            }
            inputStream.close()
            editText.setText(stringBuilder.toString())
            Toast.makeText(this, "Text loaded from file", Toast.LENGTH_SHORT).show()
        } catch (e: IOException) {
            e.printStackTrace()
            Toast.makeText(this, "Error loading text", Toast.LENGTH_SHORT).show()
        }
    }
}