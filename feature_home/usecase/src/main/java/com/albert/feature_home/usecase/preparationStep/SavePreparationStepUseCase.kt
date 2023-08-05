package com.albert.feature_home.usecase.preparationStep

import com.albert.feature_home.data.repository.PreparationStepRepository
import com.albert.feature_home.domain.PreparationStepModel
import javax.inject.Inject

class SavePreparationStepUseCase @Inject constructor(private val repository: PreparationStepRepository) {
    suspend operator fun invoke(preparationStepModel: PreparationStepModel) =
        repository.savePreparationStepsRemote(preparationStepModel)
}