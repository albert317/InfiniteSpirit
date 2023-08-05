package com.albert.feature_home.usecase.manager

import com.albert.feature_home.data.repository.CategoryRepository
import com.albert.feature_home.data.repository.DrinkIngredientRepository
import com.albert.feature_home.data.repository.DrinkRepository
import com.albert.feature_home.data.repository.IngredientRepository
import com.albert.feature_home.data.repository.ManagerRepository
import com.albert.feature_home.data.repository.PreparationStepRepository
import javax.inject.Inject

class ManagerUseCase @Inject constructor(
    private val repositoryManager: ManagerRepository,
    private val repositoryIngredient: IngredientRepository,
    private val repositoryDrink: DrinkRepository,
    private val repositoryCategory: CategoryRepository,
    private val repositoryPreparationStep:PreparationStepRepository,
    private val drinkIngredientRepository: DrinkIngredientRepository
) {
    suspend operator fun invoke() {
        repositoryManager.requestManagers()
        repositoryManager.getManagerSimple().map { manager ->
            if (manager.isUpdateRequired) {
                when (manager.name) {
                    "DrinkIngredient" -> {
                        drinkIngredientRepository.requestIngredientsOfDrinks()
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

                    "PreparationStep" -> {
                        repositoryPreparationStep.requestPreparationSteps()
                    }
                }
                repositoryManager.update(manager.copy(isUpdateRequired = false))
            }
        }
    }
}