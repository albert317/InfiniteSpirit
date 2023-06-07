package com.albert.commons.database.datasource

import com.albert.commons.database.data.dao.DrinkDao
import com.albert.commons.database.data.entity.DrinkEntity
import com.albert.feature_home.data.datasource.DrinkDataSource
import com.albert.feature_home.domain.DrinkModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DrinkLocalDataSource @Inject constructor(
    private val drinkDao: DrinkDao,
) : DrinkDataSource {

    override val drinks: Flow<List<DrinkModel>> =
        drinkDao.getDrinks().map { entities -> entities.map { it.toModel() } }

    override suspend fun isEmpty(): Boolean = drinkDao.drinkCount() == 0

    override fun findById(id: String): Flow<DrinkModel> =
        drinkDao.findById(id).map { it.toModel() }

    override suspend fun save(drinkModel: DrinkModel): String? {
        return try {
            drinkDao.addDrink(drinkModel.toEntity())
            null
        } catch (e: Exception) {
            e.message
        }
    }
}

fun DrinkEntity.toModel(): DrinkModel {
    return DrinkModel(
        id = id,
        name = name,
        description = description,
        origin = origin,
        photo = photo
    )
}

fun DrinkModel.toEntity(): DrinkEntity {
    return DrinkEntity(
        id = id,
        name = name,
        description = description,
        origin = origin,
        photo = photo
    )
}