package com.albert.feature_home.usecase.preparationStep

import com.albert.feature_home.data.repository.PreparationStepRepository
import javax.inject.Inject

class GetPreparationStepUseCase @Inject constructor(private val repository: PreparationStepRepository) {
    operator fun invoke(idDrink: String) = repository.getPreparationSteps(idDrink)
}