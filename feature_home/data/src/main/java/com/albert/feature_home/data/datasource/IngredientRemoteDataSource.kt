package com.albert.feature_home.data.datasource

import com.albert.feature_home.domain.IngredientModel

interface IngredientRemoteDataSource {
    suspend fun ingredients(): List<IngredientModel>
    suspend fun findById(id: String): IngredientModel?
    suspend fun save(ingredient: IngredientModel): String?
}