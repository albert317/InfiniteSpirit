package com.albert.feature_home.usecase.ingredient

import com.albert.feature_home.data.repository.IngredientRepository
import javax.inject.Inject

class GetIngredientsOfDrinkUseCase @Inject constructor(private val repository: IngredientRepository) {
    operator fun invoke(idDrink:String) = repository.getIngredients(idDrink)
}