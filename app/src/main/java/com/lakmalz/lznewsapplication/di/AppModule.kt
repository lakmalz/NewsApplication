package com.lakmalz.lznewsapplication.di

import android.annotation.TargetApi
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.lakmalz.lznewsapplication.data.network.NewsService
import com.lakmalz.lznewsapplication.util.API_KEY
import com.lakmalz.lznewsapplication.util.BASE_URL
import com.lakmalz.lznewsapplication.util.NoInternetException
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule(val context: Context) {


    @Provides
    @Singleton
    internal fun provideContext() = context.applicationContext

    @Provides
    @Singleton
    internal fun providePostApi(retrofit: Retrofit) = retrofit.create(NewsService::class.java)

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    @Provides
    @Singleton
    internal fun provideOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .addNetworkInterceptor(StethoInterceptor())
            .addInterceptor { chain ->
                if (!isInternetAvailable())
                    throw NoInternetException("Make sure you have an active data connection")
                chain.proceed(chain.request())
            }
            .addInterceptor { chain ->
                val originalRequest = chain.request()
                val newRequest = originalRequest.newBuilder()
                    .header("Authorization", API_KEY)
                    .build()
                chain.proceed(newRequest)
            }

        return builder.build()
    }

    @TargetApi(Build.VERSION_CODES.M)
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun isInternetAvailable(): Boolean {
        var result = false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        connectivityManager?.let {
            it.getNetworkCapabilities(connectivityManager.activeNetwork)?.apply {
                result = when {
                    hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    else -> false
                }
            }
        }
        return result
    }

    @Provides
    @Singleton
    internal fun provideRetrofitInterface(okHttpClient: OkHttpClient) = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

}