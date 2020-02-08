package com.heady.ecommerceapp.data.pref

import android.content.SharedPreferences
import androidx.core.content.edit

class PreferenceManager(private val sharedPreferences: SharedPreferences) : Preference {

    private val DATA_DOWNLOADED = "DATA_DOWNLOADED"

    override fun setDataDownloaded(dataDownloaded: Boolean) {
        sharedPreferences.edit {
            putBoolean(DATA_DOWNLOADED, dataDownloaded)
        }
    }

    override fun isDataDownloaded(): Boolean {
        return sharedPreferences.getBoolean(DATA_DOWNLOADED, false)
    }
}