package com.albert.feature_home.data.datasource

import com.albert.feature_home.domain.PreparationStepModel

interface PreparationStepRemoteDataSource {
    suspend fun preparationsStep(): List<PreparationStepModel>
    suspend fun findById(id: String): PreparationStepModel?
    suspend fun save(preparationStepModel: PreparationStepModel): String?
}