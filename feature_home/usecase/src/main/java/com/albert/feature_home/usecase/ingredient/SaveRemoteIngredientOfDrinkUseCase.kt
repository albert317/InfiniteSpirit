package com.albert.feature_home.usecase.ingredient

import com.albert.feature_home.data.repository.IngredientRepository
import com.albert.feature_home.domain.IngredientModel
import javax.inject.Inject

class SaveRemoteIngredientOfDrinkUseCase @Inject constructor(
    private val repository: IngredientRepository,
) {
    suspend operator fun invoke(ingredient: IngredientModel, idDrink: String, quantity: String) {
        //repository.saveIngredientOfDrink(ingredient, idDrink, quantity)
    }
}