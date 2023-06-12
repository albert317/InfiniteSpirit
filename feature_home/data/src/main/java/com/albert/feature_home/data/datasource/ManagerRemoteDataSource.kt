package com.albert.feature_home.data.datasource

import com.albert.feature_home.domain.ManagerModel

interface ManagerRemoteDataSource {
    suspend fun managers(): List<ManagerModel>
    suspend fun findById(id: String): ManagerModel?
    suspend fun save(managerModel: ManagerModel): String?
}