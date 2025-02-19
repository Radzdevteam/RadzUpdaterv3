package com.radzdev.updater


import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import com.radzdev.radzupdater.Downloader

class UpdateActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)
        enableEdgeToEdge()


        val latestVersion = intent.getStringExtra("LATEST_VERSION") ?: "Unknown"
        val releaseNotes = intent.getStringArrayListExtra("RELEASE_NOTES") ?: arrayListOf()
        val updateUrl = intent.getStringExtra("UPDATE_URL") ?: ""


        val newVersionText = findViewById<TextView>(R.id.newVersionText)
        val releaseNotesText = findViewById<TextView>(R.id.releaseNotesText)
        val updateButton = findViewById<Button>(R.id.updateButton)


        newVersionText.text = "Version : $latestVersion"
        releaseNotesText.text = releaseNotes.joinToString("\n")

        updateButton.setOnClickListener {
            val downloader = Downloader(this)
            downloader.startDownloadingApk(updateUrl, "Update $latestVersion")
        }
    }
}