package com.albert.feature_home.presentation.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.albert.feature_home.domain.DrinkModel
import com.albert.feature_home.domain.IngredientOfDrinkModel
import com.albert.feature_home.presentation.R

@Composable
fun DetailScreen(navHostController: NavHostController, idDrink: String) {

    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val drinkViewModel = hiltViewModel<DrinkViewModel>()

    val uiStateDrink by produceState<DrinkUiState>(
        initialValue = DrinkUiState.Loading,
        key1 = lifecycle,
        key2 = drinkViewModel
    ) {
        lifecycle.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
            drinkViewModel.uiStateDrink.collect { drink -> value = drink }
        }
    }


    drinkViewModel.getDrink(idDrink)
    drinkViewModel.getIngredients(idDrink)

    val colorResource1 = colorResource(id = R.color.resource1)
    val colorResource2 = colorResource(id = R.color.resource2)

    when (uiStateDrink) {
        is DrinkUiState.Error -> {}
        DrinkUiState.Loading -> {}
        is DrinkUiState.Success -> {
            (uiStateDrink as DrinkUiState.Success).let {
                it.drink?.let { it1 ->
                    ContentDetail(
                        drink = it1,
                        ingredients = it.ingredients,
                        drinkViewModel
                    )
                }
            }
        }
    }
}

@Composable
fun ContentDetail(
    drink: DrinkModel,
    ingredients: List<IngredientOfDrinkModel>,
    drinkViewModel: DrinkViewModel,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
            //.background(Brush.horizontalGradient(listOf(colorResource1, colorResource2)))
        ) {
            item {
                val imagePainter = painterResource(R.drawable.lasagna)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp)
                ) {
                    val imagePainter = painterResource(R.drawable.back_ground_preparation)

                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(drink.photo)
                            .build(),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize(), placeholder = imagePainter
                    )

                    Box(
                        modifier = Modifier
                            .background(
                                Brush.verticalGradient(
                                    listOf(
                                        Color.Transparent,
                                        colorResource(id = R.color.semi_transaparent)
                                    )
                                )
                            )
                            .align(Alignment.BottomStart)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = drink.name,
                            style = MaterialTheme.typography.titleLarge,
                            modifier = Modifier.padding(16.dp),
                            color = Color.White,
                        )
                    }

                }
                Spacer(modifier = Modifier.height(4.dp))
            }

            item {
                Text(
                    text = "Origen:",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(horizontal = 16.dp),
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = drink.origin,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))
            }

            item {
                Text(
                    text = "Descripción:",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(horizontal = 16.dp),
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = drink.description,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))
            }

            item {
                Text(
                    text = "Ingredientes:",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(horizontal = 16.dp),
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(4.dp))
            }
            itemsIndexed(ingredients) { index, ingredient ->
                Row(
                    modifier = Modifier
                        .background(if (index % 2 == 0) Color(0xFFF5F5F5) else Color.Transparent)
                        .padding(vertical = 4.dp, horizontal = 16.dp)
                ) {
                    Text(
                        text = "• ${ingredient.name}",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier
                            .weight(1f)
                    )
                    Text(
                        text = "${ingredient.quantity}",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier
                            .weight(1f)
                    )
                }
            }
        }

        FloatingActionButton(
            onClick = { drinkViewModel.saveIngredientsOfModel(drink.id) },
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.BottomEnd)
        ) {
            Icon(Icons.Default.Add, contentDescription = "Agregar")
        }
    }

}

