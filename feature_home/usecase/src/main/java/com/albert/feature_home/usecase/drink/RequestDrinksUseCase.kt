package com.albert.feature_home.usecase.drink

import com.albert.feature_home.data.repository.DrinkRepository
import javax.inject.Inject

class RequestDrinksUseCase @Inject constructor(private val repository: DrinkRepository) {
    suspend operator fun invoke()  {
        repository.requestPopularDrinks()
        repository.deleteDrinks()
    }
}