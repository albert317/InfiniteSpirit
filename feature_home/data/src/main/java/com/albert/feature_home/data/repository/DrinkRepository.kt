package com.albert.feature_home.data.repository

import com.albert.feature_home.data.datasource.DrinkLocalDataSource
import com.albert.feature_home.data.datasource.DrinkRemoteDataSource
import com.albert.feature_home.domain.DrinkModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DrinkRepository @Inject constructor(
    private val drinkLocalDataSource: DrinkLocalDataSource,
    private val drinkRemoteDataSource: DrinkRemoteDataSource,
) {
    fun getPopularDrinks(): Flow<List<DrinkModel>> {
        return drinkLocalDataSource.drinks
    }

    suspend fun isEmptyDrinks() = drinkLocalDataSource.isEmpty()
    fun getDrink(id: String) = drinkLocalDataSource.findById(id)
    suspend fun saveDrink(drinkModel: DrinkModel): String? {
        return drinkLocalDataSource.save(drinkModel)
    }

    private suspend fun update(drinkModel: DrinkModel) = drinkLocalDataSource.update(drinkModel)

    suspend fun requestPopularDrinks() {
        val drinks = drinkRemoteDataSource.drinks()
        if (!drinkLocalDataSource.isEmpty()) {
            drinks.map { saveDrink(it) }
        } else {
            val localDrinks = drinkLocalDataSource.drinksSimple()
            drinks.map { drink ->
                val localDrink = localDrinks.find { it.id == drink.id }
                if (localDrink == null) {
                    saveDrink(drink)
                }
                if (localDrink != null && localDrink.timeUpdate < drink.timeUpdate) {
                    update(drink)
                }
            }
        }
    }

    suspend fun deleteDrinks() {
        val drinks = drinkRemoteDataSource.drinks()
        val localDrinks = drinkLocalDataSource.drinksSimple()
        localDrinks.map { local ->
            if (drinks.find { remote -> remote.id == local.id } == null) {
                drinkLocalDataSource.delete(local)
            }
        }
    }

    suspend fun saveDrinkRemote(drinkModel: DrinkModel): String? {
        return drinkRemoteDataSource.save(drinkModel)
    }
}