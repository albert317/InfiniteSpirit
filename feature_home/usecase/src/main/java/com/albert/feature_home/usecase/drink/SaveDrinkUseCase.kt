package com.albert.feature_home.usecase.drink

import com.albert.feature_home.data.repository.DrinkRepository
import com.albert.feature_home.domain.DrinkModel
import javax.inject.Inject

class SaveDrinkUseCase @Inject constructor(private val repository: DrinkRepository) {
    suspend operator fun invoke(drinkModel: DrinkModel) = repository.saveDrink(drinkModel)
}