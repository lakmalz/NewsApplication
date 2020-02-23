package com.lakmalz.lznewsapplication.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel(private val baseRepo: BaseRepository): ViewModel() {

    fun getProgress() = baseRepo.getProgress()

    fun getErrors() = baseRepo.getErrorMessage()

    fun noInternetError() = baseRepo.getNoInternet()

    override fun onCleared() {
        super.onCleared()
        baseRepo.getCompositeDisposable().clear()
        baseRepo.getCompositeDisposable().dispose()
    }
}