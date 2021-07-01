package com.example.listapplication.app.di

import android.app.Application
import androidx.room.Room
import com.example.listapplication.model.database.AppDatabase
import com.example.listapplication.model.database.repository.ItemDao
import com.example.listapplication.model.database.repository.ItemRepository
import com.example.listapplication.viewmodel.ItemListViewModel

import org.koin.dsl.module

val viewModelModule = module {
    single { ItemListViewModel(get()) }
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

    //single { provideDatabase(androidApplication()) }
    single { provideDao(get()) }
}

val repositoryModule = module {
    fun provideUserRepository(dao: ItemDao): ItemRepository {
        return ItemRepository(dao)
    }

    single { provideUserRepository(get()) }
}