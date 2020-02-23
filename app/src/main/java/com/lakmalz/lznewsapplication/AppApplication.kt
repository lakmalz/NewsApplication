package com.lakmalz.lznewsapplication

import android.app.Application
import com.facebook.stetho.Stetho
import com.lakmalz.lznewsapplication.di.AppComponent
import com.lakmalz.lznewsapplication.di.AppModule
import com.lakmalz.lznewsapplication.di.DaggerAppComponent

class AppApplication : Application() {

    lateinit var appComponent: AppComponent

    companion object {
        private lateinit var instance: AppApplication
        fun getInstance() = instance
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        Stetho.initializeWithDefaults(this);
        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()
    }

}