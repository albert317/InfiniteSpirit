package com.albert.feature_home.presentation.ui.init

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.albert.feature_home.domain.DrinkModel
import com.albert.feature_home.usecase.drink.GetPopularDrinksUseCase
import com.albert.feature_home.usecase.drink.RequestDrinksUseCase
import com.albert.feature_home.usecase.drink.SaveDrinkUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DrinksViewModel @Inject constructor(
    private val getPopularDrinks: GetPopularDrinksUseCase,
    private val saveDrinkUseCase: SaveDrinkUseCase,
    private val requestDrinksUseCase: RequestDrinksUseCase,
) : ViewModel() {

    private val _uiStateDrinks = MutableStateFlow<DrinksUiState>(DrinksUiState.Loading)
    val uiStateDrinks: StateFlow<DrinksUiState> = _uiStateDrinks

    init {
        getPopulars()
        collectDrinks()
    }

    private fun collectDrinks() {
        viewModelScope.launch {
            requestDrinksUseCase.invoke()
        }
    }

    private fun getPopulars() {
        viewModelScope.launch {
            getPopularDrinks().catch {}.collect { drinks ->
                _uiStateDrinks.value = DrinksUiState.Success(drinks)
            }
        }
    }

    fun saveDrink() {
        viewModelScope.launch {
            val drinks = listOf(
                DrinkModel(
                    id = System.currentTimeMillis().toString(),
                    name = "gdfgd",
                    description = "gdfg",
                    origin = "dfgdfgdf",
                    photo = "gdfgdfg",
                    timeUpdate = System.currentTimeMillis().toString(),
                    timeRegister = System.currentTimeMillis().toString()
                )
            )
            drinks.map { saveDrinkUseCase.invoke(it) }
        }
    }

}

sealed interface DrinksUiState {
    object Loading : DrinksUiState
    data class Error(val throwable: Throwable) : DrinksUiState
    data class Success(val drinks: List<DrinkModel>) : DrinksUiState
}