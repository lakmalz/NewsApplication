package com.lakmalz.lznewsapplication.ui.home.customnews

import com.lakmalz.lznewsapplication.util.CustomKeyWords
import com.lakmalz.lznewsapplication.ui.base.BaseViewModel
import com.lakmalz.lznewsapplication.data.repositories.NewsRepository
import com.lakmalz.lznewsapplication.data.repositories.UserRepository
import javax.inject.Inject

class CustomNewsViewModel @Inject constructor(private val newsRepo: NewsRepository, private val userRepo: UserRepository) :
    BaseViewModel(newsRepo) {

    fun getCustomNewListLiveData() = newsRepo.getCustomNewsListLiveData()

    fun fetchCustomNewsList(@CustomKeyWords searchCategory: String?) {
        newsRepo.fetchCustomNewList(searchCategory)
    }



    fun getUserData() = userRepo.getCurrentUser()
}