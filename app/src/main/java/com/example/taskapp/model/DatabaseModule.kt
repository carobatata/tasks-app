package com.example.taskapp.model

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    fun provideTaskDao(appDatabase: AppDatabase): TaskDAO {
        return appDatabase.taskDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "tasks_database"
        ).build()
    }
}

//InstallIn tells Hilt that the dependencies provided by this module should stay alive as long as the app is running.
//@Module tells Hilt that this class contributes to dependency injection object graph.
//@Provides marks the method provideTaskDao as a provider of TaskDao.

//@Singleton annotation tells Hilt that AppDatabase should be initialized only once. And the same instance should be provided every time it’s needed to be injected.
//@ApplicationContext allows hilt to provide application context without having to explicitly specify how to obtain it.