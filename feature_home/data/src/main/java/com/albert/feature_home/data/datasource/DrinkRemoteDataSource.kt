package com.albert.feature_home.data.datasource

import com.albert.feature_home.domain.DrinkModel
import kotlinx.coroutines.flow.Flow

interface DrinkRemoteDataSource {
    suspend fun drinks(): List<DrinkModel>
    suspend fun findById(id: String): DrinkModel?
    suspend fun save(drinkModel: DrinkModel): String?
}