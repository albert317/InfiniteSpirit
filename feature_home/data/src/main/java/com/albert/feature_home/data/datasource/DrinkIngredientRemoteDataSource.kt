package com.albert.feature_home.data.datasource

import com.albert.feature_home.domain.DrinkIngredientModel

interface DrinkIngredientRemoteDataSource {
    suspend fun drinksIngredient(): List<DrinkIngredientModel>
    suspend fun findById(id: String): DrinkIngredientModel?
    suspend fun save(drinkIngredientModel: DrinkIngredientModel): String?
}