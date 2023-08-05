package com.albert.commons.database.datasource

import com.albert.commons.database.data.dao.PreparationDao
import com.albert.commons.database.data.entity.PreparationStepEntity
import com.albert.feature_home.data.datasource.PreparationStepLocalDataSource
import com.albert.feature_home.domain.PreparationStepModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PreparationStepRoomDataSource @Inject constructor(
    private val preparationDao: PreparationDao,
) : PreparationStepLocalDataSource {
    override fun preparations(idDrink: String): Flow<List<PreparationStepModel>> {
        return preparationDao.getPreparations(idDrink)
            .map { preparations -> preparations.sortedBy { it.order }.map { it.toModel() } }
    }

    override suspend fun preparationsSimple(): List<PreparationStepModel> {
        return preparationDao.getPreparationsSimple().map { it.toModel() }
    }

    override suspend fun save(preparationStepModel: PreparationStepModel): String? = try {
        preparationDao.add(preparationStepModel.toEntity())
        null
    } catch (e: Exception) {
        e.message
    }

    override suspend fun update(preparationStepModel: PreparationStepModel): String? = try {
        preparationDao.update(preparationStepModel.toEntity())
        null
    } catch (e: Exception) {
        e.message
    }

    override suspend fun isEmpty(): Boolean = preparationDao.count() == 0


    private fun PreparationStepEntity.toModel() = PreparationStepModel(
        id = id,
        idDrink = idDrink,
        order = order,
        description = description,
        timeRegister = timeRegister,
        timeUpdate = timeUpdate,
    )

    private fun PreparationStepModel.toEntity() = PreparationStepEntity(
        id = id,
        idDrink = idDrink,
        order = order,
        description = description,
        timeRegister = timeRegister,
        timeUpdate = timeUpdate,
    )
}