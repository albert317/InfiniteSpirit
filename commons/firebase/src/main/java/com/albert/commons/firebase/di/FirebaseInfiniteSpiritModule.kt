package com.albert.commons.firebase.di

import com.albert.commons.firebase.datasource.CategoryCloudFireStoreDataSource
import com.albert.commons.firebase.datasource.DrinkCloudFireStoreDataSource
import com.albert.commons.firebase.datasource.DrinkIngredientCloudFireStoreDataSource
import com.albert.commons.firebase.datasource.IngredientCloudFirebaseStoreDataSource
import com.albert.commons.firebase.datasource.ManagerCloudFirebaseStoreDataSource
import com.albert.feature_home.data.datasource.CategoryRemoteDataSource
import com.albert.feature_home.data.datasource.DrinkIngredientRemoteDataSource
import com.albert.feature_home.data.datasource.DrinkRemoteDataSource
import com.albert.feature_home.data.datasource.IngredientRemoteDataSource
import com.albert.feature_home.data.datasource.ManagerRemoteDataSource
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FirebaseInfiniteSpiritModule {

    @Provides
    @Singleton
    fun providesFirebase(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

}

@Module
@InstallIn(SingletonComponent::class)
abstract class AppDataFirebaseModule {
    @Binds
    abstract fun bindDrinkRemoteDataSource(drinkCloudFireStoreDataSource: DrinkCloudFireStoreDataSource): DrinkRemoteDataSource

    @Binds
    abstract fun bindIngredientsRemoteDataSource(ingredientCloudFirebaseStoreDataSource: IngredientCloudFirebaseStoreDataSource): IngredientRemoteDataSource

    @Binds
    abstract fun bindManagerRemoteDataSource(managerCloudFirebaseStoreDataSource: ManagerCloudFirebaseStoreDataSource): ManagerRemoteDataSource

    @Binds
    abstract fun bindDrinkIngredientDataSource(drinkIngredientCloudFireStoreDataSource: DrinkIngredientCloudFireStoreDataSource): DrinkIngredientRemoteDataSource

    @Binds
    abstract fun bindCategoryDataSource(categoryCloudFirebaseStoreDataSource: CategoryCloudFireStoreDataSource): CategoryRemoteDataSource

}