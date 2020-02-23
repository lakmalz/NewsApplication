package com.lakmalz.lznewsapplication.data.network.responsemodels

import com.lakmalz.lznewsapplication.util.HttpRequestStatus

open class BaseResponse{
    @HttpRequestStatus
    lateinit var status: String
    lateinit var message: String
}