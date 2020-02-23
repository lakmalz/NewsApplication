package com.lakmalz.lznewsapplication.di

import com.lakmalz.lznewsapplication.ui.base.BaseActivity
import com.lakmalz.lznewsapplication.ui.base.BaseFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ViewModelModule::class])
interface AppComponent {
    fun inject(activity: BaseActivity)
    fun inject(activity: BaseFragment)
}