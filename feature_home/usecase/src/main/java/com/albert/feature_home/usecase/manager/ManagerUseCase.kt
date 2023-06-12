package com.albert.feature_home.usecase.manager

import com.albert.feature_home.data.repository.CategoryRepository
import com.albert.feature_home.data.repository.DrinkRepository
import com.albert.feature_home.data.repository.IngredientRepository
import com.albert.feature_home.data.repository.ManagerRepository
import javax.inject.Inject

class ManagerUseCase @Inject constructor(
    private val repositoryManager: ManagerRepository,
    private val repositoryIngredient: IngredientRepository,
    private val repositoryDrink: DrinkRepository,
    private val repositoryCategory: CategoryRepository,
) {
    suspend operator fun invoke() {
        repositoryManager.requestManagers()
        repositoryManager.getManagerSimple().map { manager ->
            if (manager.isUpdateRequired) {
                when (manager.name) {
                    "DrinkIngredient" -> {
                        repositoryIngredient.requestIngredients()
                    }

                    "Category" -> {
                        repositoryCategory.requestCategories()
                    }

                    "Drink" -> {
                        repositoryDrink.requestPopularDrinks()
                    }

                    "Ingredient" -> {
                        repositoryIngredient.requestIngredients()
                    }
                }
                repositoryManager.update(manager.copy(isUpdateRequired = false))
            }
        }
    }
}