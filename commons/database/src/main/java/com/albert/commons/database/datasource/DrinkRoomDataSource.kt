package com.albert.commons.database.datasource

import com.albert.commons.database.data.dao.DrinkDao
import com.albert.commons.database.data.entity.DrinkEntity
import com.albert.feature_home.data.datasource.DrinkLocalDataSource
import com.albert.feature_home.domain.DrinkModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DrinkRoomDataSource @Inject constructor(
    private val drinkDao: DrinkDao,
) : DrinkLocalDataSource {

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

    override suspend fun findByIdSimple(id: String): DrinkModel? =
        drinkDao.findByIdSimple(id)?.toModel()

    override suspend fun update(drinkModel: DrinkModel): String? {
        return try {
            drinkDao.updateDrink(drinkModel.toEntity())
            null
        } catch (e: Exception) {
            e.message
        }
    }

    override suspend fun drinksSimple(): List<DrinkModel> =
        drinkDao.getDrinksSimple().map { drink -> drink.toModel() }

    override suspend fun delete(drinkModel: DrinkModel): String? {
        return try {
            drinkDao.deleteDrink(drinkModel.toEntity())
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
        photo = photo,
        timeRegister = timeRegister,
        timeUpdate = timeUpdate
    )
}

fun DrinkModel.toEntity(): DrinkEntity {
    return DrinkEntity(
        id = id,
        name = name,
        description = description,
        origin = origin,
        photo = photo,
        timeRegister = timeRegister,
        timeUpdate = timeUpdate
    )
}