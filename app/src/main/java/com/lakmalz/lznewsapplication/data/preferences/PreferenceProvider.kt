package com.lakmalz.lznewsapplication.data.preferences

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import javax.inject.Inject

private const val KEY_USER_NAME = "key_username"
private const val KEY_SELECTED_NEWS_KEYWORD = "key_news_keyword"

class PreferenceProvider @Inject constructor(private val context: Context) {

    private val preference: SharedPreferences
        get() = PreferenceManager.getDefaultSharedPreferences(context)

    fun saveUserName(userName: String?) {
        preference.edit().putString(
            KEY_USER_NAME,
            userName
        ).apply()
    }

    fun getUserName(): String? {
        return preference.getString(KEY_USER_NAME, null)
    }

    fun saveKeyWords(selectedKeyword: String?) {
        preference.edit().putString(
            KEY_SELECTED_NEWS_KEYWORD,
            selectedKeyword
        ).apply()
    }

    fun getSelectedKeyWord(): String? {
        return preference.getString(KEY_SELECTED_NEWS_KEYWORD, null)
    }

    fun clearAllPreferences() {
        preference.edit().clear().apply()
    }

}