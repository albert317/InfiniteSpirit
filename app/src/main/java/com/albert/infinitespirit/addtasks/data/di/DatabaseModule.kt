package com.albert.infinitespirit.addtasks.data.di

import android.content.Context
import androidx.room.Room
import com.albert.infinitespirit.addtasks.data.TaskDao
import com.albert.infinitespirit.addtasks.data.TaskDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun providesTaskDao(taskDataBase: TaskDataBase): TaskDao {
        return taskDataBase.taskDao()
    }

    @Provides
    @Singleton
    fun provideTodoDatabase(@ApplicationContext appContext: Context): TaskDataBase {
        return Room.databaseBuilder(appContext, TaskDataBase::class.java, "TaskDatabase").build()
    }
}