package com.albert.feature_home.data.datasource

import com.albert.feature_home.domain.PreparationStepModel
import kotlinx.coroutines.flow.Flow

interface PreparationStepLocalDataSource {
    fun preparations(idDrink:String): Flow<List<PreparationStepModel>>
    suspend fun preparationsSimple(): List<PreparationStepModel>
    suspend fun save(preparationStepModel: PreparationStepModel): String?
    suspend fun update(preparationStepModel: PreparationStepModel): String?
    suspend fun isEmpty():Boolean
}