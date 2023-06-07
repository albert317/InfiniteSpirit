package com.albert.commons.firebase.di

import com.albert.commons.firebase.datasource.DrinkCloudFireStoreDataSource
import com.albert.feature_home.data.datasource.DrinkRemoteDataSource
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
    abstract fun bindDrinkRemoteDataSource(drinkCloudFireStoreDataSource:DrinkCloudFireStoreDataSource):DrinkRemoteDataSource

}