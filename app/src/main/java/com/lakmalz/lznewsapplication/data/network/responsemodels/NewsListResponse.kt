package com.lakmalz.lznewsapplication.data.network.responsemodels

import com.lakmalz.lznewsapplication.data.models.Article

data class NewsListResponse(
    val articles: List<Article>,
    val totalResults: Int
) : BaseResponse()