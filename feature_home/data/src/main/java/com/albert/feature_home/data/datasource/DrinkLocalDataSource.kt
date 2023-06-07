package com.albert.feature_home.data.datasource

import com.albert.feature_home.domain.DrinkModel
import kotlinx.coroutines.flow.Flow

interface DrinkLocalDataSource {
    val drinks: Flow<List<DrinkModel>>
    suspend fun isEmpty(): Boolean
    fun findById(id: String): Flow<DrinkModel>
    suspend fun save(drinkModel: DrinkModel): String?
    suspend fun update(drinkModel: DrinkModel): String?
    suspend fun findByIdSimple(id: String): DrinkModel?
    suspend fun drinksSimple(): List<DrinkModel>
    suspend fun delete(drinkModel: DrinkModel):String?
}