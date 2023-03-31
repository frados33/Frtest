package com.example.androidtest

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale
import android.content.res.Configuration
class MainActivity : AppCompatActivity() {
    var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Toast.makeText(applicationContext, "onCreate", Toast.LENGTH_SHORT).show()
        Log.i("MyLog", "onCreate")
        setContentView(R.layout.activity_main)

        // Always start the counter from zero
        counter = 0

        findViewById<TextView>(R.id.counterView).text = counter.toString()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        val croatianMenuItem = menu.findItem(R.id.croatian)
        val englishMenuItem = menu.findItem(R.id.english)
        croatianMenuItem.setOnMenuItemClickListener {
            changeLanguage("hr")
            true
        }
        englishMenuItem.setOnMenuItemClickListener {
            changeLanguage("en")
            true
        }
        return true
    }

    fun changeLanguage(languageCode: String) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)
        val resources = resources
        val config = Configuration()
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)
        recreate()
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("counter", counter)
    }

    override fun onStart() {
        super.onStart()
        Toast.makeText(applicationContext, "onStart", Toast.LENGTH_SHORT).show()
        Log.i("MyLog", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Toast.makeText(applicationContext, "onResume", Toast.LENGTH_SHORT).show()
        Log.i("MyLog", "onResume")
    }

    override fun onRestart() {
        super.onRestart()
        Toast.makeText(applicationContext, "onRestart", Toast.LENGTH_SHORT).show()
        Log.i("MyLog", "onRestart")
    }

    override fun onPause() {
        super.onPause()
        Toast.makeText(applicationContext, "onPause", Toast.LENGTH_SHORT).show()
        Log.i("MyLog", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Toast.makeText(applicationContext, "onStop", Toast.LENGTH_SHORT).show()
        Log.i("MyLog", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(applicationContext, "onDestroy", Toast.LENGTH_SHORT).show()
        Log.i("MyLog", "onDestroy")
    }

    fun up(view: View) {
        counter++
        val shPr = getPreferences(Context.MODE_PRIVATE)
        val editor = shPr.edit()
        editor.putInt("counter", counter)
        editor.apply()
        if(counter ==10){
            counter=0
            val intent= Intent(this, SuccessActivity::class.java).apply {
                putExtra("name",findViewById<TextView>(R.id.plainTextName).text.toString())
            }
            startActivity(intent)
        }
        findViewById<TextView>(R.id.counterView).text = counter.toString()
    }

    fun down(view: View) {
        if(counter != 0) {
            counter--
            findViewById<TextView>(R.id.counterView).text = counter.toString()
        }
    }
}