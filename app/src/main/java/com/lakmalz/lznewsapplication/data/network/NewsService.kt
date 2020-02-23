package com.lakmalz.lznewsapplication.data.network

import com.lakmalz.lznewsapplication.data.network.responsemodels.NewsListResponse
import com.lakmalz.lznewsapplication.util.CustomKeyWords
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("v2/top-headlines")
    fun getHeadLines(@Query("country") country: String?): Observable<NewsListResponse>

    @GET("v2/everything")
    fun getCustomNews(@Query("q") @CustomKeyWords searchCategory: String?): Observable<NewsListResponse>
}