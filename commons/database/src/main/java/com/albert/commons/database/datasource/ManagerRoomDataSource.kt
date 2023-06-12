package com.albert.commons.database.datasource

import com.albert.commons.database.data.dao.ManagerDao
import com.albert.commons.database.data.entity.ManagerEntity
import com.albert.feature_home.data.datasource.ManagerLocalDataSource
import com.albert.feature_home.domain.ManagerModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ManagerRoomDataSource @Inject constructor(
    private val managerDao: ManagerDao,
) : ManagerLocalDataSource {
    override fun managers(): Flow<List<ManagerModel>> =
        managerDao.getManagers().map { managers -> managers.map { it.toModel() } }

    override fun findById(id: String): Flow<ManagerModel> =
        managerDao.findById(id).map { it.toModel() }

    override suspend fun findByIdSimple(id: String): ManagerModel? =
        managerDao.findByIdSimple(id).toModel()

    override suspend fun save(managerModel: ManagerModel): String? =
        try {
            managerDao.addManager(managerModel.toEntity())
            null
        } catch (e: Exception) {
            e.message
        }

    override suspend fun isEmpty(): Boolean =
        managerDao.managerCount() == 0


    override suspend fun update(managerModel: ManagerModel): String? = try {
        managerDao.updateManager(managerModel.toEntity())
        null
    } catch (e: Exception) {
        e.message
    }

    override suspend fun managersSimple(): List<ManagerModel> =
        managerDao.getManagersSimple().map { it.toModel() }

    private fun ManagerEntity.toModel() = ManagerModel(
        id = id,
        name = name,
        timeRegister = timeRegister,
        isUpdateRequired = isUpdateRequired,
        timeUpdate = timeUpdate
    )

    private fun ManagerModel.toEntity() = ManagerEntity(
        id = id,
        name = name,
        isUpdateRequired = isUpdateRequired,
        timeRegister = timeRegister,
        timeUpdate = timeUpdate
    )
}