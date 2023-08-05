package com.albert.feature_home.presentation.ui.init

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.albert.feature_home.domain.DrinkModel
import com.albert.feature_home.usecase.createdata.CreateDataUseCase
import com.albert.feature_home.usecase.drink.GetPopularDrinksUseCase
import com.albert.feature_home.usecase.manager.ManagerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DrinksViewModel @Inject constructor(
    private val getPopularDrinks: GetPopularDrinksUseCase,
    private val managerUseCase: ManagerUseCase,
    private val createDataUseCase: CreateDataUseCase,
) : ViewModel() {

    private val _uiStateDrinks = MutableStateFlow<DrinksUiState>(DrinksUiState.Loading)
    val uiStateDrinks: StateFlow<DrinksUiState> = _uiStateDrinks

    init {
        getPopulars()
        initManager()
    }

    private fun getPopulars() {
        viewModelScope.launch {
            getPopularDrinks().catch {}.collect { drinks ->
                _uiStateDrinks.value = DrinksUiState.Success(drinks)
            }
        }
    }

    private fun initManager() {
        viewModelScope.launch {
            managerUseCase.invoke()
        }
    }

    fun saveIngredientsOfModel() {
        viewModelScope.launch {
            createDataUseCase.saveIngredients()
            createDataUseCase.saveDrink()
        }
    }

}

sealed interface DrinksUiState {
    object Loading : DrinksUiState
    data class Error(val throwable: Throwable) : DrinksUiState
    data class Success(val drinks: List<DrinkModel>) : DrinksUiState
}