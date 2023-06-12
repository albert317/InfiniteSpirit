package com.albert.commons.database.datasource

import com.albert.commons.database.data.dao.DrinkIngredientDao
import com.albert.commons.database.data.entity.DrinkIngredientEntity
import com.albert.feature_home.data.datasource.DrinkIngredientLocalDataSource
import com.albert.feature_home.domain.DrinkIngredientModel
import javax.inject.Inject

class DrinkIngredientRoomDataSource @Inject constructor(
    private val drinkIngredientDao: DrinkIngredientDao,
) : DrinkIngredientLocalDataSource {
    override suspend fun drinkIngredientsSimple(): List<DrinkIngredientModel> =
        drinkIngredientDao.getDrinkIngredient().map { drinkIngredient -> drinkIngredient.toModel() }

    override suspend fun save(drinkIngredientModel: DrinkIngredientModel) = try {
        drinkIngredientDao.add(drinkIngredientModel.toEntity())
        null
    } catch (e: Exception) {
        e.message
    }

    override suspend fun find(id: String): DrinkIngredientModel? =
        drinkIngredientDao.find(id)?.toModel()

    override suspend fun update(drinkIngredientModel: DrinkIngredientModel): String? = try {
        drinkIngredientDao.update(drinkIngredientModel.toEntity())
        null
    } catch (e: Exception) {
        e.message
    }

    override suspend fun isEmpty(): Boolean = drinkIngredientDao.count() == 0

    private fun DrinkIngredientEntity.toModel() = DrinkIngredientModel(
        id = id,
        idDrink = idDrink,
        idIngredient = idIngredient,
        quantity = quantity,
        timeRegister = timeRegister,
        timeUpdate = timeUpdate
    )

    private fun DrinkIngredientModel.toEntity() = DrinkIngredientEntity(
        id = id,
        idDrink = idDrink,
        idIngredient = idIngredient,
        quantity = quantity,
        timeRegister = timeRegister,
        timeUpdate = timeUpdate
    )
}