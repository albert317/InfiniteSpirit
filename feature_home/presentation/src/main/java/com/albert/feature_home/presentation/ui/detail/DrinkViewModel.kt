package com.albert.feature_home.presentation.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.albert.feature_home.domain.DrinkModel
import com.albert.feature_home.domain.IngredientModel
import com.albert.feature_home.domain.IngredientOfDrinkModel
import com.albert.feature_home.usecase.drink.GetDrinkUseCase
import com.albert.feature_home.usecase.ingredient.GetIngredientsOfDrinkUseCase
import com.albert.feature_home.usecase.ingredient.RequestIngredientsOfDrinkUseCase
import com.albert.feature_home.usecase.ingredient.SaveRemoteIngredientOfDrinkUseCase
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
    private val saveRemoteIngredientOfDrinkUseCase: SaveRemoteIngredientOfDrinkUseCase,
    private val requestIngredientsOfDrinkUseCase: RequestIngredientsOfDrinkUseCase,
) : ViewModel() {

    private val _uiStateDrink = MutableStateFlow<DrinkUiState>(DrinkUiState.Loading)
    val uiStateDrink: StateFlow<DrinkUiState> = _uiStateDrink

    init {
        viewModelScope.launch {
            requestIngredientsOfDrinkUseCase()
        }
    }

    fun getDrink(idDrink: String) {
        viewModelScope.launch {
            getDrinkUseCase(idDrink).catch { }.collect { drink ->
                if (_uiStateDrink.value is DrinkUiState.Success) {
                    _uiStateDrink.value =
                        (_uiStateDrink.value as DrinkUiState.Success).copy(drink = drink)
                } else {
                    _uiStateDrink.value = DrinkUiState.Success(drink, emptyList())
                }
            }
        }
    }

    fun getIngredients(idDrink: String) {
        viewModelScope.launch {
            getIngredientsOfDrinkUseCase(idDrink).catch { }.collect { ingredients ->
                if (_uiStateDrink.value is DrinkUiState.Success) {
                    _uiStateDrink.value =
                        (_uiStateDrink.value as DrinkUiState.Success).copy(ingredients = ingredients)
                } else {
                    _uiStateDrink.value = DrinkUiState.Success(null, ingredients)
                }
            }
        }
    }

    fun saveIngredientsOfModel(idDrink: String) {
        viewModelScope.launch {
            saveRemoteIngredientOfDrinkUseCase(
                idDrink = idDrink,
                ingredient = IngredientModel(
                    "12345",
                    "Pisco",
                    "1",
                    System.currentTimeMillis().toString(),
                    System.currentTimeMillis().toString()
                ),
                quantity = "3 onzas"
            )
        }
    }
}

sealed interface DrinkUiState {
    object Loading : DrinkUiState
    data class Error(val throwable: Throwable) : DrinkUiState
    data class Success(val drink: DrinkModel?, val ingredients: List<IngredientOfDrinkModel>) :
        DrinkUiState
}
