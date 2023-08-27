package com.albert.feature_home.presentation.ui.init

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.albert.feature_home.domain.DrinkModel
import com.albert.feature_home.usecase.createdata.CreateDataUseCase
import com.albert.feature_home.usecase.drink.GetPopularDrinksUseCase
import com.albert.feature_home.usecase.manager.ManagerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DrinksViewModel @Inject constructor(
    private val getPopularDrinks: GetPopularDrinksUseCase,
    private val managerUseCase: ManagerUseCase,
    private val createDataUseCase: CreateDataUseCase,
) : ViewModel() {

    private val _uiStateDrinks : MutableState<DrinksUiState> = mutableStateOf(DrinksUiState())
    val uiStateDrinks: State<DrinksUiState> = _uiStateDrinks

    init {
        getPopulars()
        initManager()
    }

    private fun getPopulars() {
        _uiStateDrinks.value = _uiStateDrinks.value.copy(loading = true)
        viewModelScope.launch {
            getPopularDrinks().catch {
                _uiStateDrinks.value = _uiStateDrinks.value.copy(throwable = it)
            }.collect { drinks ->
                _uiStateDrinks.value = _uiStateDrinks.value.copy(drinks = drinks, loading = false)
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

data class DrinksUiState(
    val loading: Boolean = false,
    val throwable: Throwable? = null,
    val drinks: List<DrinkModel> = emptyList(),
)
