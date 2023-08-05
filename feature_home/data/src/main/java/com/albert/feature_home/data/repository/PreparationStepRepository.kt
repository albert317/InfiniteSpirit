package com.albert.feature_home.data.repository

import com.albert.feature_home.data.datasource.PreparationStepLocalDataSource
import com.albert.feature_home.data.datasource.PreparationStepRemoteDataSource
import com.albert.feature_home.domain.PreparationStepModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PreparationStepRepository @Inject constructor(
    private val preparationStepLocalDataSource: PreparationStepLocalDataSource,
    private val preparationStepRemoteDataSource: PreparationStepRemoteDataSource,
) {
    fun getPreparationSteps(idDrink:String): Flow<List<PreparationStepModel>> =
        preparationStepLocalDataSource.preparations(idDrink)

    suspend fun getPreparationStepsSimple(): List<PreparationStepModel> =
        preparationStepLocalDataSource.preparationsSimple()

    suspend fun save(preparationStepModel: PreparationStepModel): String? =
        preparationStepLocalDataSource.save(preparationStepModel)

    suspend fun update(preparationStepModel: PreparationStepModel): String? =
        preparationStepLocalDataSource.update(preparationStepModel)

    suspend fun requestPreparationSteps() {
        val preparationSteps = preparationStepRemoteDataSource.preparationsStep()
        if (preparationStepLocalDataSource.isEmpty()) {
            preparationSteps.map { save(it) }
        } else {
            val localPreparationSteps = getPreparationStepsSimple()
            preparationSteps.map { step ->
                val localStep = localPreparationSteps.find { it.id == step.id }
                if (localStep == null) {
                    save(step)
                }
                if (localStep != null && localStep.timeUpdate < step.timeUpdate) {
                    update(step)
                }
            }
        }
    }

    suspend fun savePreparationStepsRemote(preparationStepModel: PreparationStepModel): String? {
        return preparationStepRemoteDataSource.save(preparationStepModel)
    }
}