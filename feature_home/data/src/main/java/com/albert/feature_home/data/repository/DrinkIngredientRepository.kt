package com.albert.feature_home.data.repository

import com.albert.feature_home.data.datasource.DrinkIngredientLocalDataSource
import com.albert.feature_home.data.datasource.DrinkIngredientRemoteDataSource
import com.albert.feature_home.domain.DrinkIngredientModel
import javax.inject.Inject

class DrinkIngredientRepository @Inject constructor(
    private val drinkIngredientLocalDataSource: DrinkIngredientLocalDataSource,
    private val drinkIngredientRemoteDataSource: DrinkIngredientRemoteDataSource,
) {

    suspend fun getDrinkIngredients(): List<DrinkIngredientModel> {
        return drinkIngredientLocalDataSource.drinkIngredientsSimple()
    }

    suspend fun save(drinkIngredientModel: DrinkIngredientModel): String? {
        return drinkIngredientLocalDataSource.save(drinkIngredientModel)
    }

    suspend fun update(drinkIngredientModel: DrinkIngredientModel): String? {
        return drinkIngredientLocalDataSource.update(drinkIngredientModel)
    }

    suspend fun requestIngredientsOfDrinks() {
        val drinkIngredientRemote = drinkIngredientRemoteDataSource.drinksIngredient()
        if (drinkIngredientLocalDataSource.isEmpty()) {
            drinkIngredientRemote.map { save(it) }
        } else {
            val localIngredients = getDrinkIngredients()
            drinkIngredientRemote.map { drinkIngr ->
                val localIngredient = localIngredients.find { it.id == drinkIngr.id }
                if (localIngredient == null) {
                    save(drinkIngr)
                }
                if (localIngredient != null && localIngredient.timeUpdate < drinkIngr.timeUpdate) {
                    update(drinkIngr)
                }
            }
        }
    }

    suspend fun saveDrinkIngredientRemote(drinkIngredientModel: DrinkIngredientModel):String?{
        return drinkIngredientRemoteDataSource.save(drinkIngredientModel)
    }

}