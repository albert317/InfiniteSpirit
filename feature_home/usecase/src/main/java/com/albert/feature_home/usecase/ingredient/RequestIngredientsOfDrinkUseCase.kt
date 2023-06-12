package com.albert.feature_home.usecase.ingredient

import com.albert.feature_home.data.repository.DrinkIngredientRepository
import com.albert.feature_home.data.repository.IngredientRepository
import javax.inject.Inject

class RequestIngredientsOfDrinkUseCase @Inject constructor(
    private val repository: IngredientRepository,
    private val repositoryDrinkIngredient:DrinkIngredientRepository
) {
    suspend operator fun invoke(){
        repository.requestIngredients()
        repositoryDrinkIngredient.requestIngredientsOfDrinks()
    }
}