package com.albert.feature_home.data.repository

import com.albert.feature_home.data.datasource.DrinkDataSource
import com.albert.feature_home.domain.DrinkModel
import javax.inject.Inject

class DrinkRepository @Inject constructor(
    private val drinkDataSource: DrinkDataSource,
) {
    fun getPopularDrinks() = drinkDataSource.drinks
    suspend fun isEmptyDrinks() = drinkDataSource.isEmpty()
    fun getDrink(id: String) = drinkDataSource.findById(id)
    suspend fun saveDrink(drinkModel: DrinkModel) = drinkDataSource.save(drinkModel)
}