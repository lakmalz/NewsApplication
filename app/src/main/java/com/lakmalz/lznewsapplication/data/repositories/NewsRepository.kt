package com.lakmalz.lznewsapplication.data.repositories

import androidx.lifecycle.MutableLiveData
import com.lakmalz.lznewsapplication.data.network.NewsService
import com.lakmalz.lznewsapplication.data.network.responsemodels.NewsListResponse
import com.lakmalz.lznewsapplication.ui.base.BaseRepository
import com.lakmalz.lznewsapplication.util.CustomKeyWords
import com.lakmalz.lznewsapplication.util.HttpRequestStatus
import com.lakmalz.lznewsapplication.util.NoInternetException
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class NewsRepository @Inject constructor(private val api: NewsService) : BaseRepository() {

    private var headlinesLiveData = MutableLiveData<NewsListResponse>()

    private var customNewsListLiveData = MutableLiveData<NewsListResponse>()

    fun getCustomNewsListLiveData() = customNewsListLiveData

    fun getHeadlinesLiveData() = headlinesLiveData

    /**
     * get headline news list form API
     */
    fun fetchHeadlines(country: String?, pageSize: Int, page: Int) {
        api.getHeadLines(country, pageSize, page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { getProgress().postValue(true) }
            .doFinally { getProgress().postValue(false) }
            .subscribeBy(
                onNext = {
                    when (it.status) {
                        HttpRequestStatus.SUCCESS -> {
                            headlinesLiveData.postValue(it)
                        }
                        HttpRequestStatus.ERROR -> {
                            getErrorMessage().postValue(it.message)
                        }
                        else -> {
                            getErrorMessage().postValue("Oops, Something went wrong.")
                        }
                    }
                },
                onError = {
                    when (it) {
                        is NoInternetException -> {
                            getNoInternet().postValue("No internet.")
                        }
                        else -> {
                            getErrorMessage().postValue("Oops, Something went wrong.")
                        }
                    }
                }
            ).addTo(getCompositeDisposable())
    }

    /**
     * get custom news list from API
     * by selected news type
     */
    fun fetchCustomNewList(@CustomKeyWords category: String?, pageSize: Int, page: Int) {
        api.getCustomNews(category, pageSize, page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { getProgress().postValue(true) }
            .doFinally { getProgress().postValue(false) }
            .subscribeBy(
                onNext = {
                    when (it.status) {
                        HttpRequestStatus.SUCCESS -> {
                            customNewsListLiveData.postValue(it)
                        }
                        HttpRequestStatus.ERROR -> {
                            getErrorMessage().postValue(it.message)
                        }
                        else -> {
                            getErrorMessage().postValue("Oops, Something went wrong.")
                        }
                    }
                },
                onError = {
                    when (it) {
                        is NoInternetException -> {
                            getNoInternet().postValue("No internet.")
                        }
                        else -> {
                            getErrorMessage().postValue("Oops, Something went wrong.")
                        }
                    }
                }
            ).addTo(getCompositeDisposable())
    }

}