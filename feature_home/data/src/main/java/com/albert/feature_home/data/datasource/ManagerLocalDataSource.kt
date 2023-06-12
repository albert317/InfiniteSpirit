package com.albert.feature_home.data.datasource

import com.albert.feature_home.domain.ManagerModel
import kotlinx.coroutines.flow.Flow

interface ManagerLocalDataSource {
    fun managers(): Flow<List<ManagerModel>>
    suspend fun managersSimple(): List<ManagerModel>
    fun findById(id: String): Flow<ManagerModel>
    suspend fun findByIdSimple(id: String): ManagerModel?
    suspend fun save(managerModel: ManagerModel): String?
    suspend fun isEmpty(): Boolean
    suspend fun update(managerModel: ManagerModel): String?

}