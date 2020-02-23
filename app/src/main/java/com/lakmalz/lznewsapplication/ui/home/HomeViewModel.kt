package com.lakmalz.lznewsapplication.ui.home

import com.lakmalz.lznewsapplication.data.repositories.NewsRepository
import com.lakmalz.lznewsapplication.data.repositories.UserRepository
import com.lakmalz.lznewsapplication.ui.base.BaseViewModel
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val userRepo: UserRepository) :
    BaseViewModel(userRepo) {

    fun getUserData() = userRepo.getCurrentUser()
}