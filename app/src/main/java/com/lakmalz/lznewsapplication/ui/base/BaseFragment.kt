package com.lakmalz.lznewsapplication.ui.base

import android.app.AlertDialog
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.lakmalz.lznewsapplication.AppApplication
import com.lakmalz.lznewsapplication.R
import com.lakmalz.lznewsapplication.util.Utils
import java.util.*
import javax.inject.Inject

open class BaseFragment : Fragment() {

    @Inject
    protected lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppApplication.getInstance().appComponent.inject(this)
    }

    protected fun showMessage(title: String = getString(R.string.alert), message: String = getString(R.string.oops_wmthing_went_wrong)) {
        val alert = AlertDialog.Builder(context)
        alert.setTitle(title)
        alert.setMessage(message)
        alert.show()
    }

    fun dpToPx(dp: Int): Int {
        val displayMetrics = context?.resources?.displayMetrics
        return Math.round(dp * (displayMetrics?.xdpi?.div(DisplayMetrics.DENSITY_DEFAULT)!!))
    }

    fun hideKeyboard() {
        try {
            Utils.hideSoftKeyboard(Objects.requireNonNull<FragmentActivity>(activity))
        } catch (e: Exception) {
            if (e.message != null) {
                Log.e(javaClass.simpleName, e.message)
            }
        }

    }
}