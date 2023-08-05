package com.albert.feature_home.data.datasource

import com.albert.feature_home.domain.IngredientModel
import com.albert.feature_home.domain.IngredientOfDrinkModel
import kotlinx.coroutines.flow.Flow

interface IngredientLocalDataSource {
    fun ingredients(idDrink: String): Flow<List<IngredientOfDrinkModel>>
    suspend fun ingredientsSimple(): List<IngredientOfDrinkModel>
    fun findById(id: String): Flow<IngredientModel?>
    suspend fun findByIdSimple(id: String): IngredientModel?
    suspend fun save(ingredient: IngredientModel): String?
    suspend fun isEmpty(): Boolean
    suspend fun update(ingredient: IngredientModel): String?
    suspend fun getOnlyIngredients(): List<IngredientModel>
}