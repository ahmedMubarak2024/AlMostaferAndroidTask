package com.mub.almostaferandroidtask.bases

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mub.almostaferandroidtask.helpers.SingleLiveEvent
import com.mub.almostaferandroidtask.model.ErrorModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

abstract class BaseViewModel : ViewModel(), KoinComponent {
    private val mErrorLiveData: SingleLiveEvent<ErrorModel> = SingleLiveEvent()
    val errorLiveData: LiveData<ErrorModel>
        get() = mErrorLiveData

    fun CoroutineScope.launchWithErrorHandling(block: suspend CoroutineScope.() -> Unit) {
        try {
            launch(Dispatchers.Main, block = block)
        } catch (e: java.lang.Exception) {
            handleError(e)
            e.printStackTrace()
        }
    }


    fun handleError(e: java.lang.Exception) {

    }


}