package com.example.listapplication.app.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.listapplication.model.database.AppDatabase
import com.example.listapplication.model.database.repository.ItemDao
import com.example.listapplication.model.database.repository.ItemRepository
import com.example.listapplication.viewmodel.ItemViewModel
import com.example.listapplication.viewmodel.ListItemViewModel
import org.koin.android.ext.koin.androidApplication

import org.koin.dsl.module

val viewModelModule = module {
    single { ListItemViewModel(get()) }
    single { ItemViewModel(get()) }
}

val databaseModule = module {

    fun provideDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, "eds.database")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    fun provideDao(database: AppDatabase): ItemDao {
        return database.itemDao
    }

    single { provideDatabase(androidApplication()) }
    single { provideDao(get()) }
}

val repositoryModule = module {
    fun provideUserRepository(dao: ItemDao, context: Context): ItemRepository {
        return ItemRepository(dao, context)
    }

    single { provideUserRepository(get(), get()) }
}