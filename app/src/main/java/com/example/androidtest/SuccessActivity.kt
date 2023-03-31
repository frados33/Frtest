package com.example.androidtest

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SuccessActivity : AppCompatActivity() {
    private lateinit var textView: TextView
    private lateinit var buttonSend: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_success)

        textView = findViewById(R.id.textView)
        buttonSend = findViewById(R.id.buttonSend)
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)

        val name = intent.getStringExtra("name")

        // Use getString() to get the translated string with a dynamic parameter
        val successMessage = getString(R.string.success, name)
        textView.text = successMessage

        buttonSend.setOnClickListener {
            val selectedRadioButton = findViewById<RadioButton>(radioGroup.checkedRadioButtonId)
            val phoneNumber = selectedRadioButton.text.toString()

            val uri: Uri= Uri.parse("smsto:$phoneNumber")
            val intent = Intent(Intent.ACTION_SENDTO, uri)
            intent.putExtra("sms_body", successMessage)
            startActivity(intent)
        }
    }
}
