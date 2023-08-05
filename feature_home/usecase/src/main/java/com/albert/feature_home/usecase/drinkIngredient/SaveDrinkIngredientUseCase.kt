package com.albert.feature_home.usecase.drinkIngredient

import com.albert.feature_home.data.repository.DrinkIngredientRepository
import com.albert.feature_home.domain.DrinkIngredientModel
import javax.inject.Inject

class SaveDrinkIngredientUseCase @Inject constructor(private val repository:DrinkIngredientRepository){
    suspend operator fun invoke(drinkIngredientModel: DrinkIngredientModel)=
        repository.saveDrinkIngredientRemote(drinkIngredientModel)
}