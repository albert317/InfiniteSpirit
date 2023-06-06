package com.albert.feature_home.usecase.drink

import com.albert.feature_home.data.repository.DrinkRepository
import javax.inject.Inject

class GetPopularDrinksUseCase @Inject constructor(private val repository: DrinkRepository) {
    operator fun invoke() = repository.getPopularDrinks()
}