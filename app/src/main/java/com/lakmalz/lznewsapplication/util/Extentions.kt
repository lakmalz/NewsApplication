package com.lakmalz.lznewsapplication.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View =
    LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)

fun Boolean?.getVisibility(): Int = if (this != null && this) View.VISIBLE else View.GONE