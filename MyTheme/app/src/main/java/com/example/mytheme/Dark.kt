package com.example.mytheme

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate

class Dark : AppCompatActivity() {

    private lateinit var prefs: SharedPreferences
    private lateinit var themeText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        prefs = getSharedPreferences("theme_prefs", MODE_PRIVATE)
        applySavedTheme()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dark)

        themeText = findViewById(R.id.currentThemeText)
        val lightBtn = findViewById<Button>(R.id.lightThemeBtn)
        val darkBtn = findViewById<Button>(R.id.darkThemeBtn)

        updateThemeText()

        lightBtn.setOnClickListener {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            prefs.edit().putBoolean("dark_mode", false).apply()
        }

        darkBtn.setOnClickListener {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            prefs.edit().putBoolean("dark_mode", true).apply()
        }
    }

    private fun applySavedTheme() {
        val isDark = prefs.getBoolean("dark_mode", false)
        if (isDark) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    private fun updateThemeText() {
        val currentNightMode = resources.configuration.uiMode and
                android.content.res.Configuration.UI_MODE_NIGHT_MASK
        themeText.text = if (currentNightMode == android.content.res.Configuration.UI_MODE_NIGHT_YES) {
            "Current Theme: Dark"
        } else {
            "Current Theme: Light"
        }
    }
}