package com.albert.commons.database.datasource

import android.util.Log
import com.albert.commons.database.data.dao.IngredientDao
import com.albert.commons.database.data.entity.IngredientEntity
import com.albert.commons.database.data.entity.IngredientWithQuantity
import com.albert.feature_home.data.datasource.IngredientLocalDataSource
import com.albert.feature_home.domain.IngredientModel
import com.albert.feature_home.domain.IngredientOfDrinkModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class IngredientsRoomDataSource @Inject constructor(
    private val ingredientDao: IngredientDao,
) : IngredientLocalDataSource {
    override fun ingredients(idDrink: String): Flow<List<IngredientOfDrinkModel>> =
        ingredientDao.getIngredientsForDrink()
            .map { ingredients ->
                ingredients.map {
                    val aa = it.toModel()
                    val bb = aa
                    it.toModel()
                }
            }

    override suspend fun ingredientsSimple(): List<IngredientOfDrinkModel> {
        val ingredients=ingredientDao.getIngredientsForDrinkSimple()
        return ingredients.map { it.toModel() }
    }

    override fun findById(id: String): Flow<IngredientModel?> =
        ingredientDao.findById(id).map { it.toModel() }

    override suspend fun findByIdSimple(id: String) =
        ingredientDao.findByIdSimple(id).toModel()

    override suspend fun save(ingredient: IngredientModel): String? {
        return try {
            ingredientDao.addIngredient(ingredient.toEntity())
            null
        } catch (e: Exception) {
            e.message
        }
    }

    override suspend fun isEmpty(): Boolean {
        return ingredientDao.ingredientCount() == 0
    }

    override suspend fun update(ingredient: IngredientModel): String? {
        return try {
            ingredientDao.updateIngredient(ingredient.toEntity())
            null
        } catch (e: Exception) {
            e.message
        }
    }

    override suspend fun getOnlyIngredients(): List<IngredientModel> {
        return ingredientDao.getOnlyIngredients().map { it.toModel() }
    }
}

fun IngredientWithQuantity.toModel() = IngredientOfDrinkModel(
    id = id,
    idDrink = idDrink,
    idIngredient = idIngredient,
    name = name,
    type = type,
    quantity = quantity,
    timeRegister = timeRegister,
    timeUpdate = timeUpdate
)

fun IngredientEntity.toModel() = IngredientModel(
    id = id,
    name = name,
    type = type,
    timeRegister = timeRegister,
    timeUpdate = timeUpdate
)

fun IngredientModel.toEntity() = IngredientEntity(
    id = id,
    name = name,
    type = type,
    timeRegister = timeRegister,
    timeUpdate = timeUpdate
)


