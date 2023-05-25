package com.mub.almostaferandroidtask

import android.app.Application
import com.mub.almostaferandroidtask.di.models
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

//this is custom android app to start koin and create the database and any other Components
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            // Log Koin into Android logger
            androidLogger()
            // Reference Android context
            androidContext(this@MyApplication)
            // Load modules
            modules(models)
        }
    }
}