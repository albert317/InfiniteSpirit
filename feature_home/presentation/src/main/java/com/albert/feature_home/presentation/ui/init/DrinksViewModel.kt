package com.albert.feature_home.presentation.ui.init

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.albert.feature_home.domain.DrinkModel
import com.albert.feature_home.usecase.drink.GetPopularDrinksUseCase
import com.albert.feature_home.usecase.drink.SaveDrinkUseCase
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
    private val saveDrinkUseCase: SaveDrinkUseCase,
    private val managerUseCase: ManagerUseCase,
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
    private fun initManager(){
        viewModelScope.launch {
            managerUseCase.invoke()
        }
    }

    fun saveDrink() {
        viewModelScope.launch {
            val drinks = listOf(
                DrinkModel(
                    id = System.currentTimeMillis().toString(),
                    name = "Pisco Sour",
                    description = "El Pisco Sour es un cóctel tradicional que proviene de Perú. Es bien conocido por su equilibrio entre el sabor ácido y dulce, complementado por la suavidad del Pisco, una especie de aguardiente de uva. Además, la espuma que se crea a partir de la clara de huevo le da al cóctel una textura única y agradable.",
                    origin = "Perú",
                    photo = "https://firebasestorage.googleapis.com/v0/b/infinitespirit-ea254.appspot.com/o/Albert_photo_of_pisco_source_served_8a739bb7-d417-4bbc-b414-e7997f15d742.png?alt=media&token=7d27eada-89b5-4902-b063-8683ec55b41a&_gl=1*pjwlxc*_ga*MTYyNDg5MzQzMy4xNjgwNjQwNzAz*_ga_CW55HF8NVT*MTY4NjE5MzU5OC40Ny4xLjE2ODYxOTM2MDEuMC4wLjA.",
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