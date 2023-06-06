package com.albert.feature_home.usecase.drink

import com.albert.feature_home.data.repository.DrinkRepository
import javax.inject.Inject

class IsEmptyDrinksUseCase @Inject constructor(private val repository: DrinkRepository) {
    suspend operator fun invoke() = repository.isEmptyDrinks()
}