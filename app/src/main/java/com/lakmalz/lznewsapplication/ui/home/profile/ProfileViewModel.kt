package com.lakmalz.lznewsapplication.ui.home.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lakmalz.lznewsapplication.data.models.User
import com.lakmalz.lznewsapplication.data.repositories.NewsRepository
import com.lakmalz.lznewsapplication.data.repositories.UserRepository
import com.lakmalz.lznewsapplication.ui.base.BaseViewModel
import javax.inject.Inject

class ProfileViewModel @Inject constructor(private val userRepo: UserRepository) :
    BaseViewModel(userRepo) {

    private var userLiveData = MutableLiveData<User>()

    fun getUserLiveData() = userLiveData

    fun getUserData() = userRepo.getCurrentUser()

    fun saveUserName(userName: String){
        userLiveData.postValue(userRepo.saveRegisteredUser(userName))
    }

    fun saveSelectedKeyword(keyword: String) {
        userRepo.saveSelectedKeyword(keyword)
    }

    fun logoutUser() {
        userRepo.logoutUser()
    }
}