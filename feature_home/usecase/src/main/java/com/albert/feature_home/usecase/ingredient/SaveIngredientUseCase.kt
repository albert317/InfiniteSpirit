package com.albert.feature_home.usecase.ingredient

import com.albert.feature_home.data.repository.IngredientRepository
import com.albert.feature_home.domain.IngredientModel
import javax.inject.Inject

class SaveIngredientUseCase @Inject constructor(private val repository: IngredientRepository) {
    suspend operator fun invoke(ingredientModel: IngredientModel) =
        repository.saveIngredientRemote(ingredientModel)
}