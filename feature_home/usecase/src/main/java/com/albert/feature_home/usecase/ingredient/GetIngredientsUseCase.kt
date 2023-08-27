package com.albert.feature_home.usecase.ingredient

import com.albert.feature_home.data.repository.IngredientRepository
import javax.inject.Inject

class GetIngredientsUseCase @Inject constructor(private val repository: IngredientRepository) {
    operator fun invoke() = repository.getIngredients()
}