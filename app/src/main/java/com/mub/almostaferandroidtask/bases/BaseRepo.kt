package com.mub.almostaferandroidtask.bases

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class BaseRepo {

    suspend fun <T> backgroundContext(block: suspend CoroutineScope.() -> T) =
        withContext(Dispatchers.IO, block)
}