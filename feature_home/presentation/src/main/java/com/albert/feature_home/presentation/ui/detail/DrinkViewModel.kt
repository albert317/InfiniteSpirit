package com.albert.feature_home.presentation.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.albert.feature_home.domain.DrinkModel
import com.albert.feature_home.domain.IngredientOfDrinkModel
import com.albert.feature_home.domain.PreparationStepModel
import com.albert.feature_home.usecase.drink.GetDrinkUseCase
import com.albert.feature_home.usecase.ingredient.GetIngredientsOfDrinkUseCase
import com.albert.feature_home.usecase.preparationStep.GetPreparationStepUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DrinkViewModel @Inject constructor(
    private val getDrinkUseCase: GetDrinkUseCase,
    private val getIngredientsOfDrinkUseCase: GetIngredientsOfDrinkUseCase,
    private val getPreparationStepUseCase: GetPreparationStepUseCase,
) : ViewModel() {

    private val _uiStateDrink = MutableStateFlow(DrinkUiState())
    val uiStateDrink: StateFlow<DrinkUiState> = _uiStateDrink

    fun getDrink(idDrink: String) {
        viewModelScope.launch {
            getDrinkUseCase(idDrink).catch { }.collect { drink ->
                _uiStateDrink.value = _uiStateDrink.value.copy(isLoading = false, drink = drink)
            }
        }
    }

    fun getIngredients(idDrink: String) {
        viewModelScope.launch {
            getIngredientsOfDrinkUseCase(idDrink).catch { }.collect { ingredients ->
                _uiStateDrink.value =
                    _uiStateDrink.value.copy(isLoading = false, ingredients = ingredients)
            }
        }
    }

    fun getPreparationSteps(idDrink: String) {
        viewModelScope.launch {
            getPreparationStepUseCase(idDrink).catch { }.collect { steps ->
                _uiStateDrink.value = _uiStateDrink.value.copy(isLoading = false, steps = steps)
            }
        }
    }
}

data class DrinkUiState(
    val isLoading: Boolean = false,
    val ingredients: List<IngredientOfDrinkModel> = emptyList(),
    val steps: List<PreparationStepModel> = emptyList(),
    val drink: DrinkModel? = null,
    val error: Throwable? = null,
)
