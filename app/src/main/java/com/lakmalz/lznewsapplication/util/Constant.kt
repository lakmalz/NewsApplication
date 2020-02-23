package com.lakmalz.lznewsapplication.util

import androidx.annotation.StringDef

const val DEFAULT_COUNTRY = "us"
const val BASE_URL = "http://newsapi.org/"
const val API_KEY = "Bearer f0383482278d4b14a0d981d345ce9661"

@StringDef(HttpRequestStatus.SUCCESS, HttpRequestStatus.ERROR)
@Retention(AnnotationRetention.SOURCE)
annotation class HttpRequestStatus {
    companion object {
        const val SUCCESS = "ok"
        const val ERROR = "error"
    }

}

@StringDef(
    CustomKeyWords.BITCOIN,
    CustomKeyWords.APPLE,
    CustomKeyWords.EARTHQUAKE,
    CustomKeyWords.ANIMAL
)
@Retention(AnnotationRetention.SOURCE)
annotation class CustomKeyWords {
    companion object {
        const val BITCOIN = "bitcoin"
        const val APPLE = "apple"
        const val EARTHQUAKE = "earthquake"
        const val ANIMAL = "animal"
    }

}

