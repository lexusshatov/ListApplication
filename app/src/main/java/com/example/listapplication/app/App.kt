package com.example.listapplication.app

import android.app.Application
import com.example.listapplication.app.di.databaseModule
import com.example.listapplication.app.di.repositoryModule
import com.example.listapplication.app.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level


class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            androidLogger(Level.DEBUG)
            modules(listOf(viewModelModule, repositoryModule, databaseModule))
        }
    }

}