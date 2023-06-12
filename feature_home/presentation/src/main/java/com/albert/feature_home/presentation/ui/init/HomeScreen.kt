package com.albert.feature_home.presentation.ui.init

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.albert.feature_home.domain.CategoryModel
import com.albert.feature_home.domain.DrinkModel
import com.albert.feature_home.presentation.R
import com.albert.feature_home.presentation.nav.FeatureScreen

@Composable
fun HomeScreen(navController: NavHostController) {
    val lifecycle = LocalLifecycleOwner.current.lifecycle

    val drinksViewModel = hiltViewModel<DrinksViewModel>()
    val uiStateDrinks by produceState<DrinksUiState>(
        initialValue = DrinksUiState.Loading, key1 = lifecycle, key2 = drinksViewModel
    ) {
        lifecycle.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
            drinksViewModel.uiStateDrinks.collect { drinks -> value = drinks }
        }
    }

    val categoryViewModel = hiltViewModel<CategoriesViewModel>()
    val categoryUiState by produceState<CategoryUiState>(
        initialValue = CategoryUiState.Loading, key1 = lifecycle, key2 = categoryViewModel
    ) {
        lifecycle.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
            categoryViewModel.uiStateCategory.collect { categories -> value = categories }
        }
    }

    val colorResource1 = colorResource(id = R.color.resource1)
    val colorResource2 = colorResource(id = R.color.resource2)

    Box(
        modifier = Modifier.fillMaxSize()
        //.background(Brush.horizontalGradient(listOf(colorResource2, colorResource1)))
    ) {
        //Image(painter = painterResource(R.drawable.back_ground_demo), modifier = Modifier.fillMaxSize(), contentDescription = null)
        Column {
            SearchBarHome()
            when (uiStateDrinks) {
                is DrinksUiState.Error -> {}
                is DrinksUiState.Loading -> {}
                is DrinksUiState.Success -> {
                    if (categoryUiState is CategoryUiState.Success) {
                        PopularList(
                            navController,
                            (uiStateDrinks as DrinksUiState.Success).drinks,
                            (categoryUiState as CategoryUiState.Success).categories
                        )
                    }
                }
            }
        }
        FloatingActionButton(
            onClick = { drinksViewModel.saveDrink() },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Icon(Icons.Default.Add, contentDescription = "Agregar")
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarHome() {
    val ctx = LocalContext.current
    var query by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }

    fun onSearch(name: String) {
        Toast.makeText(ctx, "Search $name", Toast.LENGTH_SHORT).show()
        active = false
    }
    SearchBar(query = query,
        onQueryChange = { query = it },
        onSearch = { onSearch(query) },
        active = active,
        onActiveChange = { active = it },
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(4.dp),
        placeholder = { Text(text = "Search") },
        trailingIcon = {
            IconButton(onClick = { onSearch(query) }, enabled = query.isNotEmpty()) {
                Icon(imageVector = Icons.Default.Search, contentDescription = "search")
            }
        }) {
        if (query.isNotEmpty()) {
            val filteredCountries = countries.filter { it.second.contains(query, true) }
            LazyColumn {
                items(filteredCountries) { (flag, name) ->
                    Text(text = "$flag $name", modifier = Modifier
                        .padding(16.dp)
                        .clickable {
                            Toast
                                .makeText(ctx, name, Toast.LENGTH_SHORT)
                                .show()
                            active = false
                            query = name
                        })
                }
            }
        }
    }
}


@Composable
fun CategoryList(navController: NavHostController, categories: List<CategoryModel>) {
    LazyRow {
        items(categories) { obj ->
            CategoryItem(navController, obj)
        }
    }
}


@Composable
fun CategoryItem(navController: NavHostController, categoryModel: CategoryModel) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(8.dp)
    ) {
        val image = painterResource(R.drawable.lasagna)
        Image(
            painter = image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(70.dp)
                .clip(CircleShape)
                .border(2.dp, MaterialTheme.colorScheme.primary, CircleShape)
        )
        Text(
            text = categoryModel.name,
            style = MaterialTheme.typography.bodyLarge,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
    }
}


@Composable
fun PopularList(
    navController: NavHostController,
    drinks: List<DrinkModel>,
    categories: List<CategoryModel>,
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        item {
            CategoryList(navController, categories)
        }
        items(drinks) { obj ->
            Drink2Item(navController, obj)
        }
    }
}

@Composable
fun Drink2Item(navController: NavHostController, drinkModel: DrinkModel) {
    val colorResource3 = colorResource(id = R.color.resource3)
    val host = FeatureScreen.DetailScreen.withArgs(
        mapOf(Pair(FeatureScreen.ID_DRINK, drinkModel.id))
    )
    Card(
        Modifier
            .fillMaxWidth()
            .padding(8.dp, 0.dp)
            .clickable {
                navController.navigate(host)
            }, shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically, modifier = Modifier.wrapContentHeight()
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current).data(drinkModel.photo).build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(120.dp)
                    .height(140.dp)
            )

            Column(modifier = Modifier.padding(horizontal = 8.dp)) {
                Text(
                    text = drinkModel.name,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "Una experiencia embriagadora de suavidad y frescura, combinando dulzura y acidez con la exquisita espuma, evocando el deseo irrefrenable de un sorbo más.",
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 6,
                    overflow = TextOverflow.Ellipsis
                )
                val colorFire = colorResource(id = R.color.icon_fire)
                val colorSmile = colorResource(id = R.color.icon_smile)
                val colorSpa = colorResource(id = R.color.icon_spa)

                /*Row(
                    modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start
                ) {
                    val countrie = countries.find { it.second == "Peru" }
                    countrie?.let { (flag, name) ->
                        Text(text = "$flag")
                    }

                }*/
            }
        }
    }
}

@Composable
fun ItemTag(color: Color, icon: ImageVector) {
    Box(
        modifier = Modifier
            .size(16.dp)
            .background(color, CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(12.dp)
        )
    }
}

val countries = listOf(
    "🇵🇪" to "Peru",
    "🇨🇱" to "Chile",
    "🇦🇷" to "Argentina",
    "🇧🇷" to "Brazil",
    "🇨🇴" to "Colombia",
    "🇪🇨" to "Ecuador",
    "🇵🇾" to "Paraguay",
    "🇺🇾" to "Uruguay",
    "🇧🇴" to "Bolivia",
    "🇻🇪" to "Venezuela",
    "🇬🇾" to "Guyana",
    "🇸🇷" to "Suriname",
    "🇬🇫" to "French Guiana",
    "🇵🇦" to "Panama",
    "🇨🇷" to "Costa Rica",
    "🇳🇮" to "Nicaragua",
    "🇭🇳" to "Honduras",
    "🇬🇹" to "Guatemala",
    "🇧🇿" to "Belize",
    "🇲🇽" to "Mexico",
    "🇨🇺" to "Cuba",
    "🇩🇴" to "Dominican Republic",
    "🇭🇹" to "Haiti",
    // Agrega más países según sea necesario...
)

