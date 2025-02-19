package com.radzdev.radzupdater

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import com.google.gson.Gson
import com.radzdev.updater.UpdateActivity
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

class RadzUpdater(private val context: Context, private val updateUrl: String) {

    private val client = OkHttpClient()
    private val TAG = "RadzUpdater"

    // Start checking for updates
    fun start() {
        Log.d(TAG, "Starting update check...")
        fetchUpdateDetails()
    }

    // Fetch update details from the JSON URL
    private fun fetchUpdateDetails() {
        Log.d(TAG, "Fetching update details from: $updateUrl")
        val request = Request.Builder().url(updateUrl).build()

        Thread {
            try {
                val response: Response = client.newCall(request).execute()
                val jsonResponse = response.body?.string()

                Log.d(TAG, "Response received: $jsonResponse")

                if (jsonResponse != null) {
                    val updateDetails = Gson().fromJson(jsonResponse, UpdateDetails::class.java)
                    val currentVersion = getCurrentAppVersion()

                    Log.d(TAG, "Current version: $currentVersion, Latest version: ${updateDetails.latestVersion}")

                    if (updateDetails != null && currentVersion != updateDetails.latestVersion) {
                        Log.d(TAG, "New update available!")
                        Handler(Looper.getMainLooper()).post {
                            showUpdateDialog(updateDetails)
                        }
                    } else {
                        Log.d(TAG, "App is up to date.")
                    }
                } else {
                    Log.e(TAG, "JSON response is null")
                }
            } catch (e: Exception) {
                Log.e(TAG, "Error fetching update details: ${e.message}", e)
                Handler(Looper.getMainLooper()).post {
                    Toast.makeText(context, "Failed to fetch update details", Toast.LENGTH_SHORT).show()
                }
            }
        }.start()
    }

    private fun getCurrentAppVersion(): String {
        return try {
            val pInfo = context.packageManager.getPackageInfo(context.packageName, 0)
            pInfo.versionName ?: "Unknown"
        } catch (e: PackageManager.NameNotFoundException) {
            Log.e(TAG, "Error getting current version: ${e.message}", e)
            "Unknown"
        }
    }

    private fun showUpdateDialog(updateDetails: UpdateDetails) {
        Log.d(TAG, "Launching fullscreen update screen...")

        val activity = context as? Activity ?: return
        val intent = Intent(context, UpdateActivity::class.java).apply {
            putExtra("LATEST_VERSION", updateDetails.latestVersion)
            putExtra("RELEASE_NOTES", ArrayList(updateDetails.releaseNotes))
            putExtra("UPDATE_URL", updateDetails.url)
        }
        activity.startActivity(intent)
    }


    data class UpdateDetails(
        val latestVersion: String,
        val url: String,
        val releaseNotes: List<String>
    )
}
