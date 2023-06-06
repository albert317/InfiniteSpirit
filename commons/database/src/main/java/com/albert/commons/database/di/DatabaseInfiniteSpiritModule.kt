package com.albert.commons.database.di

import android.content.Context
import androidx.room.Room
import com.albert.commons.database.data.InfiniteSpiritDataBase
import com.albert.commons.database.data.dao.CategoryDao
import com.albert.commons.database.data.dao.DrinkDao
import com.albert.commons.database.datasource.CategoryLocalDataSource
import com.albert.commons.database.datasource.DrinkLocalDataSource
import com.albert.feature_home.data.datasource.CategoryDataSource
import com.albert.feature_home.data.datasource.DrinkDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseInfiniteSpiritModule {

    @Provides
    @Singleton
    fun providesDrinkDao(database: InfiniteSpiritDataBase): DrinkDao {
        return database.drinkDao()
    }


    @Provides
    @Singleton
    fun providesCategoryDao(database: InfiniteSpiritDataBase): CategoryDao {
        return database.categoryDao()
    }

    @Provides
    @Singleton
    fun providesTodoDataBaseInfiniteSpirit(@ApplicationContext appContext: Context): InfiniteSpiritDataBase {
        return Room.databaseBuilder(
            appContext,
            InfiniteSpiritDataBase::class.java,
            "InfiniteSpiritDataBase"
        ).build()
    }


}

@Module
@InstallIn(SingletonComponent::class)
abstract class AppDataModule {
    @Binds
    abstract fun bindDrinkLocalDataSource(drinkLocalDataSource: DrinkLocalDataSource): DrinkDataSource

    @Binds
    abstract fun bindCategoryLocalDataSource(categoryLocalDataSource: CategoryLocalDataSource): CategoryDataSource
}