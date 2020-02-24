package com.lakmalz.lznewsapplication.util

import android.app.Activity
import android.view.inputmethod.InputMethodManager
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


class Utils {

    companion object {
        /**
         * Key board hide
         */
        fun hideSoftKeyboard(activity: Activity?) {
            if (activity == null) {
                return
            }
            val inputMethodManager = activity.getSystemService(
                Activity.INPUT_METHOD_SERVICE
            ) as InputMethodManager
            if (inputMethodManager != null && activity.currentFocus != null) {
                try {
                    inputMethodManager.hideSoftInputFromWindow(
                        activity.currentFocus!!.windowToken, 0
                    )
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }
        }

        @Throws(ParseException::class)
        fun getFormatedDatewithTwoDecomalNormalForDashSeparatedFormat(unformatedDate: String?): String {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            val outputFormat = SimpleDateFormat("MM/dd/yyyy")
            var date: Date? = null
            date = inputFormat.parse(unformatedDate)
            return outputFormat.format(date)
        }
    }


}