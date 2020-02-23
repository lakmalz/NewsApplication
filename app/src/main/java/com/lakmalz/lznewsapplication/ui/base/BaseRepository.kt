package com.lakmalz.lznewsapplication.ui.base

import androidx.lifecycle.MutableLiveData
import io.reactivex.disposables.CompositeDisposable

open class BaseRepository {

    private val mCompositeDisposable = CompositeDisposable()
    private val errorMessage = MutableLiveData<String>()
    private val noInternet = MutableLiveData<String>()
    private val progress = MutableLiveData<Boolean>()

    fun getErrorMessage() = errorMessage

    fun getNoInternet() = noInternet

    fun getProgress() = progress

    fun getCompositeDisposable() = mCompositeDisposable
}