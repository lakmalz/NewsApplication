package com.lakmalz.lznewsapplication.ui.base

import android.app.AlertDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.lakmalz.lznewsapplication.AppApplication
import com.lakmalz.lznewsapplication.R
import javax.inject.Inject

open class BaseActivity:AppCompatActivity() {

    @Inject
    protected lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as AppApplication).appComponent.inject(this)
    }

    protected fun showMessage(title: String = getString(R.string.alert), message: String = getString(R.string.oops_wmthing_went_wrong)) {
        val alert = AlertDialog.Builder(this)
        alert.setTitle(title)
        alert.setMessage(message)
        alert.show()
    }

}