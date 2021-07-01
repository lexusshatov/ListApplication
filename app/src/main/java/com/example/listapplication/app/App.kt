package com.example.listapplication.app

import android.app.Application
import com.example.listapplication.app.di.databaseModule
import com.example.listapplication.app.di.repositoryModule
import com.example.listapplication.app.di.viewModelModule
import org.koin.core.context.startKoin


class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(listOf(viewModelModule, repositoryModule, databaseModule))
        }
    }

}