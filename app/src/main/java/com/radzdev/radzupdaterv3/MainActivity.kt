package com.radzdev.radzupdaterv3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import com.radzdev.radzupdater.RadzUpdater


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val appUpdater = RadzUpdater(this, "https://raw.githubusercontent.com/Radzdevteam/test/refs/heads/main/RadzUpdaterv3.json")
        appUpdater.start()

    }
}
