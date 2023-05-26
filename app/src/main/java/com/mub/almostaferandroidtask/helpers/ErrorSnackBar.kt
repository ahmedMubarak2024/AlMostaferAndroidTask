package com.mub.almostaferandroidtask.helpers

import android.graphics.Color
import android.view.View
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.mub.almostaferandroidtask.R
import com.mub.almostaferandroidtask.features.home.adapter.HomeAdapter

/*
* used control the error show and to help with reloading
* */
class ErrorSnackBar(mainView: View) {
    private val retryCalls: MutableSet<HomeAdapter> = mutableSetOf()
    val snackbar = Snackbar.make(mainView, "", Snackbar.LENGTH_INDEFINITE)

    init {

        snackbar.view.setBackgroundColor(ContextCompat.getColor(mainView.context, R.color.black))
        snackbar.setActionTextColor(Color.WHITE)
        snackbar.setAction("RETRY") {
            val list = retryCalls.toList()
            list.forEach {
                it.retry()
            }
        }

    }

    fun addError(owner: HomeAdapter, message: String) {
        if (retryCalls.add(owner)) {
            snackbar.setText(message)
            snackbar.show()
        }
    }

    fun removeError(owner: HomeAdapter) {
        retryCalls.remove(owner)
        if (retryCalls.isEmpty()) snackbar.dismiss()
    }

}