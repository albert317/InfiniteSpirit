package com.albert.feature_home.presentation.ui.detail

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.albert.feature_home.domain.DrinkModel
import com.albert.feature_home.domain.IngredientOfDrinkModel
import com.albert.feature_home.domain.PreparationStepModel
import com.albert.feature_home.presentation.R

@Composable
fun DetailScreen(
    navHostController: NavHostController,
    idDrink: String,
    drinkViewModel: DrinkViewModel = hiltViewModel(),
) {

    val uiStateDrink by drinkViewModel.uiStateDrink.collectAsStateWithLifecycle()

    drinkViewModel.getDrink(idDrink)
    drinkViewModel.getIngredients(idDrink)
    drinkViewModel.getPreparationSteps(idDrink)

    if (!uiStateDrink.isLoading) {
        uiStateDrink.apply {
            drink?.let {
                ContentDetail(
                    drink = it,
                    ingredients = ingredients,
                    preparationSteps = steps
                )
            }
        }
    }
}

@Composable
fun ContentDetail(
    drink: DrinkModel,
    ingredients: List<IngredientOfDrinkModel>,
    preparationSteps: List<PreparationStepModel>,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
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
                Row(
                    verticalAlignment = Alignment.Bottom,
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    Text(
                        text = "Origen:",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = drink.origin,
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))
            }

            item {
                var expanded by remember { mutableStateOf(true) }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .clickable { expanded = !expanded }) {
                    Text(
                        text = "Descripción:",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.weight(1f), fontWeight = FontWeight.SemiBold
                    )
                    Icon(
                        imageVector = if (expanded) Icons.Default.ArrowDropUp else Icons.Default.ArrowDropDown,
                        contentDescription = "Expand or collapse icon"
                    )
                }

                AnimatedVisibility(visible = expanded) {
                    Text(
                        text = drink.description,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Ingredientes:",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth(),
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(4.dp))
            }
            itemsIndexed(ingredients) { index, ingredient ->
                Row(
                    modifier = Modifier
                        .background(if (index % 2 == 0) Color(0x20000000) else Color.Transparent)
                        .padding(vertical = 4.dp, horizontal = 16.dp)
                ) {
                    Text(
                        text = "• ${ingredient.name}",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier
                            .weight(1f)
                    )
                    Text(
                        text = ingredient.quantity,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier
                            .weight(1f)
                    )
                }
            }
            item {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Preparación:",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth(),
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(4.dp))
            }
            itemsIndexed(preparationSteps) { index, step ->
                Row(
                    modifier = Modifier
                        .background(if (index % 2 == 0) Color(0x20000000) else Color.Transparent)
                        .padding(vertical = 4.dp, horizontal = 16.dp)
                ) {
                    Text(
                        text = "${step.order}.",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = step.description,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier
                            .weight(1f)
                    )
                }
            }
        }

        FloatingActionButton(
            onClick = { },
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.BottomEnd)
        ) {
            Icon(Icons.Default.Add, contentDescription = "Agregar")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHome() {
    val ingredient = IngredientOfDrinkModel("", "", "", "Pisco", "", "3 onzas", "", "")
    val drink = DrinkModel("id", "Name", "description", "origin", "", "", "")
    val step = PreparationStepModel("","",1,"paso de preparación","","")
    ContentDetail(
        drink = drink,
        ingredients = listOf(ingredient,ingredient,ingredient),
        preparationSteps = listOf(step,step.copy(order = 2),step.copy(order = 3))
    )
}

