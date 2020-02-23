package com.lakmalz.lznewsapplication.ui.base

import android.app.AlertDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.lakmalz.lznewsapplication.AppApplication
import javax.inject.Inject

open class BaseActivity:AppCompatActivity() {

    @Inject
    protected lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as AppApplication).appComponent.inject(this)
    }

    protected fun showMessage(title: String = "Alert", message: String = "Please try again. Something went wrong.") {
        val alert = AlertDialog.Builder(this)
        alert.setTitle(title)
        alert.setMessage(message)
        alert.show()
    }
}