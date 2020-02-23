package com.lakmalz.lznewsapplication.ui.base

import android.app.AlertDialog
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.lakmalz.lznewsapplication.AppApplication
import javax.inject.Inject

open class BaseFragment : Fragment() {

    @Inject
    protected lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppApplication.getInstance().appComponent.inject(this)
    }

    protected fun showMessage(title: String = "Alert", message: String = "Please try again. Something went wrong.") {
        val alert = AlertDialog.Builder(context)
        alert.setTitle(title)
        alert.setMessage(message)
        alert.show()
    }

    fun dpToPx(dp: Int): Int {
        val displayMetrics = context?.resources?.displayMetrics
        return Math.round(dp * (displayMetrics?.xdpi?.div(DisplayMetrics.DENSITY_DEFAULT)!!))
    }
}