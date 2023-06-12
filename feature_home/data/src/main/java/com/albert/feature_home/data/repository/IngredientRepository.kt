package com.albert.feature_home.data.repository

import com.albert.feature_home.data.datasource.IngredientLocalDataSource
import com.albert.feature_home.data.datasource.IngredientRemoteDataSource
import com.albert.feature_home.domain.IngredientModel
import com.albert.feature_home.domain.IngredientOfDrinkModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class IngredientRepository @Inject constructor(
    private val ingredientLocalDataSource: IngredientLocalDataSource,
    private val ingredientRemoteDataSource: IngredientRemoteDataSource,
) {
    fun getIngredients(idDrink: String): Flow<List<IngredientOfDrinkModel>> {
        return ingredientLocalDataSource.ingredients(idDrink)
    }

    suspend fun getIngredientsSimple(): List<IngredientOfDrinkModel> {
        return ingredientLocalDataSource.ingredientsSimple()
    }

    suspend fun saveIngredient(ingredientModel: IngredientModel): String? {
        return ingredientLocalDataSource.save(ingredientModel)
    }

    private suspend fun update(ingredientModel: IngredientModel) =
        ingredientLocalDataSource.update(ingredientModel)

    suspend fun requestIngredients() {
        val ingredients = ingredientRemoteDataSource.ingredients()
        if (ingredientLocalDataSource.isEmpty()) {
            ingredients.map { saveIngredient(it) }
        } else {
            val ingredientsLocal = getIngredientsSimple()
            ingredients.map { ingredient ->
                val localIngredient = ingredientsLocal.find { it.id == ingredient.id }
                if (localIngredient == null) {
                    saveIngredient(ingredient)
                }
                if (localIngredient != null && localIngredient.timeUpdate < ingredient.timeRegister) {
                    update(ingredient)
                }
            }
        }
    }
}