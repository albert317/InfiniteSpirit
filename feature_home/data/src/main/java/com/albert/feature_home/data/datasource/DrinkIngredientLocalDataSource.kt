package com.albert.feature_home.data.datasource

import com.albert.feature_home.domain.DrinkIngredientModel

interface DrinkIngredientLocalDataSource {
    suspend fun drinkIngredientsSimple():List<DrinkIngredientModel>
    suspend fun save(drinkIngredientModel:DrinkIngredientModel):String?
    suspend fun find(id: String): DrinkIngredientModel?
    suspend fun update(drinkIngredientModel: DrinkIngredientModel): String?
    suspend fun isEmpty(): Boolean
}