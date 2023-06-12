package com.albert.feature_home.data.repository

import com.albert.feature_home.data.datasource.ManagerLocalDataSource
import com.albert.feature_home.data.datasource.ManagerRemoteDataSource
import com.albert.feature_home.domain.ManagerModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ManagerRepository @Inject constructor(
    private val managerLocalDataSource: ManagerLocalDataSource,
    private val managerRemoteDataSource: ManagerRemoteDataSource,
) {
    fun getManagers(): Flow<List<ManagerModel>> =
        managerLocalDataSource.managers()

    suspend fun getManagerSimple(): List<ManagerModel> = managerLocalDataSource.managersSimple()

    suspend fun save(managerModel: ManagerModel): String? =
        managerLocalDataSource.save(managerModel)

    suspend fun update(managerModel: ManagerModel): String? =
        managerLocalDataSource.update(managerModel)

    suspend fun requestManagers() {
        val managers = managerRemoteDataSource.managers()
        if (managerLocalDataSource.isEmpty()) {
            managers.map { save(it) }
        } else {
            val localManagers = managerLocalDataSource.managersSimple()
            managers.map { manager ->
                val localManager = localManagers.find { it.id == manager.id }
                if (localManager == null) {
                    save(manager)
                }
                if (localManager != null && localManager.timeUpdate < manager.timeUpdate) {
                    update(manager)
                }
            }
        }
    }
}