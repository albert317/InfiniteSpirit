package com.albert.commons.database.di

import android.content.Context
import androidx.room.Room
import com.albert.commons.database.data.InfiniteSpiritDataBase
import com.albert.commons.database.data.dao.CategoryDao
import com.albert.commons.database.data.dao.DrinkDao
import com.albert.commons.database.data.dao.DrinkIngredientDao
import com.albert.commons.database.data.dao.IngredientDao
import com.albert.commons.database.data.dao.ManagerDao
import com.albert.commons.database.data.dao.PreparationDao
import com.albert.commons.database.datasource.CategoryRoomDataSource
import com.albert.commons.database.datasource.DrinkIngredientRoomDataSource
import com.albert.commons.database.datasource.DrinkRoomDataSource
import com.albert.commons.database.datasource.IngredientsRoomDataSource
import com.albert.commons.database.datasource.ManagerRoomDataSource
import com.albert.commons.database.datasource.PreparationStepRoomDataSource
import com.albert.feature_home.data.datasource.CategoryLocalDataSource
import com.albert.feature_home.data.datasource.DrinkIngredientLocalDataSource
import com.albert.feature_home.data.datasource.DrinkLocalDataSource
import com.albert.feature_home.data.datasource.IngredientLocalDataSource
import com.albert.feature_home.data.datasource.ManagerLocalDataSource
import com.albert.feature_home.data.datasource.PreparationStepLocalDataSource
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
    fun providesIngredientDao(database: InfiniteSpiritDataBase): IngredientDao {
        return database.ingredientDao()
    }

    @Provides
    @Singleton
    fun providesManagerDao(database: InfiniteSpiritDataBase): ManagerDao {
        return database.managerDao()
    }

    @Provides
    @Singleton
    fun providesDrinkIngredient(database: InfiniteSpiritDataBase): DrinkIngredientDao {
        return database.drinkIngredientDao()
    }

    @Provides
    @Singleton
    fun providesPreparation(database: InfiniteSpiritDataBase): PreparationDao {
        return database.preparationDao()
    }

    @Provides
    @Singleton
    fun providesTodoDataBaseInfiniteSpirit(@ApplicationContext appContext: Context): InfiniteSpiritDataBase {
        return Room.databaseBuilder(
            appContext,
            InfiniteSpiritDataBase::class.java,
            "InfiniteSpiritDataBase"
        ).fallbackToDestructiveMigration().build()
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class AppDataModule {
    @Binds
    abstract fun bindDrinkLocalDataSource(drinkRoomDataSource: DrinkRoomDataSource): DrinkLocalDataSource

    @Binds
    abstract fun bindCategoryLocalDataSource(categoryRoomDataSource: CategoryRoomDataSource): CategoryLocalDataSource

    @Binds
    abstract fun bindIngredientsLocalDataSource(ingredientsRoomDataSource: IngredientsRoomDataSource): IngredientLocalDataSource

    @Binds
    abstract fun bindManagerLocalDataSource(managerRoomDataSource: ManagerRoomDataSource): ManagerLocalDataSource

    @Binds
    abstract fun bindDrinkIngredientLocalDataSource(drinkIngredientRooDataSource: DrinkIngredientRoomDataSource): DrinkIngredientLocalDataSource

    @Binds
    abstract fun bindPreparationStepLocalDataSource(preparationStepRoomDataSource: PreparationStepRoomDataSource): PreparationStepLocalDataSource
}