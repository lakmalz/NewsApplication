package com.lakmalz.lznewsapplication.ui.home.headline

import com.lakmalz.lznewsapplication.ui.base.BaseViewModel
import com.lakmalz.lznewsapplication.data.repositories.NewsRepository
import javax.inject.Inject

class HeadlinesViewModel @Inject constructor(private val repo: NewsRepository) :
    BaseViewModel(repo) {

    fun getHeadlinesLiveData() = repo.getHeadlinesLiveData()

    fun fetchHeadlines(country: String?, pageSize: Int, page: Int) = repo.fetchHeadlines(country, pageSize, page)

}
