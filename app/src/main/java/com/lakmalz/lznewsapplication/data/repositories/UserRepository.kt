package com.lakmalz.lznewsapplication.data.repositories

import android.content.Context
import androidx.lifecycle.LiveData
import com.lakmalz.lznewsapplication.data.models.User
import com.lakmalz.lznewsapplication.data.network.NewsService
import com.lakmalz.lznewsapplication.data.preferences.PreferenceProvider
import com.lakmalz.lznewsapplication.ui.base.BaseRepository
import javax.inject.Inject

class UserRepository @Inject constructor(private val pref: PreferenceProvider) : BaseRepository() {

    fun getCurrentUser() = User(pref.getUserName(), pref.getSelectedKeyWord())

    fun saveRegisteredUser(name: String): User{
        pref.saveUserName(name)
        return getCurrentUser()
    }

    fun saveSelectedKeyword(keyword: String){
        pref.saveKeyWords(keyword)
    }


    fun logoutUser() {
        pref.clearAllPreferences()
    }
}