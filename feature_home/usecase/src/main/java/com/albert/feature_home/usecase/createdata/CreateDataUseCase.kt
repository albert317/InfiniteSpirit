package com.albert.feature_home.usecase.createdata

import com.albert.feature_home.domain.DrinkIngredientModel
import com.albert.feature_home.domain.DrinkModel
import com.albert.feature_home.domain.IngredientModel
import com.albert.feature_home.domain.PreparationStepModel
import com.albert.feature_home.usecase.drink.SaveDrinkUseCase
import com.albert.feature_home.usecase.drinkIngredient.SaveDrinkIngredientUseCase
import com.albert.feature_home.usecase.ingredient.SaveIngredientUseCase
import com.albert.feature_home.usecase.preparationStep.SavePreparationStepUseCase
import javax.inject.Inject

class CreateDataUseCase @Inject constructor(
    private val saveDrinkUseCase: SaveDrinkUseCase,
    private val savePreparationStepUseCase: SavePreparationStepUseCase,
    private val saveIngredientUseCase: SaveIngredientUseCase,
    private val saveDrinkIngredientUseCase: SaveDrinkIngredientUseCase,
) {

    private fun getPreparedStepsMojito(idDrink: String): List<PreparationStepModel> = listOf(
        PreparationStepModel(
            id = "",
            idDrink = idDrink,
            order = 1,
            description = "En un vaso alto, coloca las hojas de hierbabuena y el azúcar. Usando un mortero, aplasta suavemente las hojas de hierbabuena con el azúcar para liberar sus aceites. Esto se llama 'macerado'.",
            timeRegister = System.currentTimeMillis().toString(),
            timeUpdate = System.currentTimeMillis().toString(),
        ),
        PreparationStepModel(
            id = "",
            idDrink = idDrink,
            order = 2,
            description = "Añade el jugo de limón y mezcla bien para asegurarte de que el azúcar se disuelve.",
            timeRegister = System.currentTimeMillis().toString(),
            timeUpdate = System.currentTimeMillis().toString(),
        ),
        PreparationStepModel(
            id = "",
            idDrink = idDrink,
            order = 3,
            description = "Llena el vaso con cubitos de hielo.",
            timeRegister = System.currentTimeMillis().toString(),
            timeUpdate = System.currentTimeMillis().toString(),
        ),
        PreparationStepModel(
            id = "",
            idDrink = idDrink,
            order = 4,
            description = "Vierte el ron blanco sobre el hielo.",
            timeRegister = System.currentTimeMillis().toString(),
            timeUpdate = System.currentTimeMillis().toString(),
        ),
        PreparationStepModel(
            id = "",
            idDrink = idDrink,
            order = 5,
            description = "Rellena el resto del vaso con soda o agua con gas, y revuelve bien para mezclar todos los ingredientes.",
            timeRegister = System.currentTimeMillis().toString(),
            timeUpdate = System.currentTimeMillis().toString(),
        ),
        PreparationStepModel(
            id = "",
            idDrink = idDrink,
            order = 6,
            description = "Finalmente, decora el vaso con una rodaja de limón y unas hojas de hierbabuena.",
            timeRegister = System.currentTimeMillis().toString(),
            timeUpdate = System.currentTimeMillis().toString(),
        )
    )

    private fun getPreparedStepsPinaColada(idDrink: String): List<PreparationStepModel> = listOf(
        PreparationStepModel(
            id = "",
            idDrink = idDrink,
            order = 1,
            description = "Combina 2 oz de ron blanco, 1 oz de crema de coco, 1 oz de crema de leche y 6 oz de zumo de piña en una coctelera o licuadora.",
            timeRegister = System.currentTimeMillis().toString(),
            timeUpdate = System.currentTimeMillis().toString(),
        ),
        PreparationStepModel(
            id = "",
            idDrink = idDrink,
            order = 2,
            description = "Si estás utilizando una coctelera, agita bien los ingredientes. Si estás utilizando una licuadora, mezcla los ingredientes a alta velocidad hasta que queden suaves.",
            timeRegister = System.currentTimeMillis().toString(),
            timeUpdate = System.currentTimeMillis().toString(),
        ),
        PreparationStepModel(
            id = "",
            idDrink = idDrink,
            order = 3,
            description = "Vierta la mezcla en un vaso alto lleno de hielo.",
            timeRegister = System.currentTimeMillis().toString(),
            timeUpdate = System.currentTimeMillis().toString(),
        ),
        PreparationStepModel(
            id = "",
            idDrink = idDrink,
            order = 4,
            description = "Adorna con una rodaja de piña y una cereza de cóctel. Sirve con una pajita.",
            timeRegister = System.currentTimeMillis().toString(),
            timeUpdate = System.currentTimeMillis().toString(),
        )
    )

    private fun getPreparedStepsMargarita(idDrink: String): List<PreparationStepModel> = listOf(
        PreparationStepModel(
            id = "",
            idDrink = idDrink,
            order = 1,
            description = "Prepara el vaso de Margarita espolvoreando sal sobre el borde. Puedes hacer esto frotando una rodaja de limón alrededor del borde del vaso y luego volcando el vaso boca abajo sobre un plato con sal.",
            timeRegister = System.currentTimeMillis().toString(),
            timeUpdate = System.currentTimeMillis().toString(),
        ),
        PreparationStepModel(
            id = "",
            idDrink = idDrink,
            order = 2,
            description = "En una coctelera, añade el tequila, el licor de naranja y el jugo de limón.",
            timeRegister = System.currentTimeMillis().toString(),
            timeUpdate = System.currentTimeMillis().toString(),
        ),
        PreparationStepModel(
            id = "",
            idDrink = idDrink,
            order = 3,
            description = "Agrega hielo y agita vigorosamente hasta que la coctelera esté fría al tacto.",
            timeRegister = System.currentTimeMillis().toString(),
            timeUpdate = System.currentTimeMillis().toString(),
        ),
        PreparationStepModel(
            id = "",
            idDrink = idDrink,
            order = 4,
            description = "Vierte la mezcla en el vaso preparado.",
            timeRegister = System.currentTimeMillis().toString(),
            timeUpdate = System.currentTimeMillis().toString(),
        ),
        PreparationStepModel(
            id = "",
            idDrink = idDrink,
            order = 5,
            description = "Finalmente, adorna con una rodaja de limón y sirve inmediatamente.",
            timeRegister = System.currentTimeMillis().toString(),
            timeUpdate = System.currentTimeMillis().toString(),
        )
    )

    private fun getPreparedStepsBloodyMary(idDrink: String): List<PreparationStepModel> = listOf(
        PreparationStepModel(
            id = "",
            idDrink = idDrink,
            order = 1,
            description = "Prepara un vaso alto espolvoreando sal sobre el borde. Puedes hacer esto frotando una rodaja de limón alrededor del borde del vaso y luego volcando el vaso boca abajo sobre un plato con sal.",
            timeRegister = System.currentTimeMillis().toString(),
            timeUpdate = System.currentTimeMillis().toString(),
        ),
        PreparationStepModel(
            id = "",
            idDrink = idDrink,
            order = 2,
            description = "En el vaso, combina el vodka, el jugo de tomate, el jugo de limón, la salsa Worcestershire y la salsa picante. También puedes añadir sal y pimienta al gusto.",
            timeRegister = System.currentTimeMillis().toString(),
            timeUpdate = System.currentTimeMillis().toString(),
        ),
        PreparationStepModel(
            id = "",
            idDrink = idDrink,
            order = 3,
            description = "Llena el vaso con hielo y revuelve bien para mezclar todos los ingredientes.",
            timeRegister = System.currentTimeMillis().toString(),
            timeUpdate = System.currentTimeMillis().toString(),
        ),
        PreparationStepModel(
            id = "",
            idDrink = idDrink,
            order = 4,
            description = "Finalmente, decora con un tallo de apio y una rodaja de limón antes de servir.",
            timeRegister = System.currentTimeMillis().toString(),
            timeUpdate = System.currentTimeMillis().toString(),
        )
    )

    private fun getPreparedStepsCosmopolitan(idDrink: String): List<PreparationStepModel> = listOf(
        PreparationStepModel(
            id = "",
            idDrink = idDrink,
            order = 1,
            description = "En una coctelera, añade el vodka, el triple sec, el jugo de arándanos y el jugo de lima.",
            timeRegister = System.currentTimeMillis().toString(),
            timeUpdate = System.currentTimeMillis().toString(),
        ),
        PreparationStepModel(
            id = "",
            idDrink = idDrink,
            order = 2,
            description = "Añade hielo y agita bien hasta que la coctelera se sienta fría al tacto.",
            timeRegister = System.currentTimeMillis().toString(),
            timeUpdate = System.currentTimeMillis().toString(),
        ),
        PreparationStepModel(
            id = "",
            idDrink = idDrink,
            order = 3,
            description = "Cuela el cóctel en una copa de martini fría.",
            timeRegister = System.currentTimeMillis().toString(),
            timeUpdate = System.currentTimeMillis().toString(),
        ),
        PreparationStepModel(
            id = "",
            idDrink = idDrink,
            order = 4,
            description = "Finalmente, adorna con una rodaja de lima o una cáscara de limón.",
            timeRegister = System.currentTimeMillis().toString(),
            timeUpdate = System.currentTimeMillis().toString(),
        )
    )

    private fun getPreparedStepsOldFashioned(idDrink: String): List<PreparationStepModel> = listOf(
        PreparationStepModel(
            id = "",
            idDrink = idDrink,
            order = 1,
            description = "Coloca el azúcar en un vaso Old Fashioned y añade los amargos. Agrega un chorrito de agua.",
            timeRegister = System.currentTimeMillis().toString(),
            timeUpdate = System.currentTimeMillis().toString(),
        ),
        PreparationStepModel(
            id = "",
            idDrink = idDrink,
            order = 2,
            description = "Mezcla hasta que el azúcar se disuelva, luego llena el vaso con cubitos de hielo.",
            timeRegister = System.currentTimeMillis().toString(),
            timeUpdate = System.currentTimeMillis().toString(),
        ),
        PreparationStepModel(
            id = "",
            idDrink = idDrink,
            order = 3,
            description = "Vierte el bourbon o whisky de centeno. Revuelve bien para mezclar.",
            timeRegister = System.currentTimeMillis().toString(),
            timeUpdate = System.currentTimeMillis().toString(),
        ),
        PreparationStepModel(
            id = "",
            idDrink = idDrink,
            order = 4,
            description = "Finalmente, decora con una rodaja de naranja y una cereza al marrasquino.",
            timeRegister = System.currentTimeMillis().toString(),
            timeUpdate = System.currentTimeMillis().toString(),
        )
    )

    private fun getPreparedStepsMartini(idDrink: String): List<PreparationStepModel> = listOf(
        PreparationStepModel(
            id = "",
            idDrink = idDrink,
            order = 1,
            description = "Llena una coctelera con cubitos de hielo.",
            timeRegister = System.currentTimeMillis().toString(),
            timeUpdate = System.currentTimeMillis().toString(),
        ),
        PreparationStepModel(
            id = "",
            idDrink = idDrink,
            order = 2,
            description = "Añade la ginebra y el vermut seco. Agita bien hasta que la mezcla esté bien fría.",
            timeRegister = System.currentTimeMillis().toString(),
            timeUpdate = System.currentTimeMillis().toString(),
        ),
        PreparationStepModel(
            id = "",
            idDrink = idDrink,
            order = 3,
            description = "Cuela la mezcla en una copa de cóctel fría.",
            timeRegister = System.currentTimeMillis().toString(),
            timeUpdate = System.currentTimeMillis().toString(),
        ),
        PreparationStepModel(
            id = "",
            idDrink = idDrink,
            order = 4,
            description = "Finalmente, adorna con una aceituna o una rodaja de limón.",
            timeRegister = System.currentTimeMillis().toString(),
            timeUpdate = System.currentTimeMillis().toString(),
        )
    )

    private fun getPreparedStepsNegroni(idDrink: String): List<PreparationStepModel> = listOf(
        PreparationStepModel(
            id = "",
            idDrink = idDrink,
            order = 1,
            description = "Llena un vaso corto con cubitos de hielo.",
            timeRegister = System.currentTimeMillis().toString(),
            timeUpdate = System.currentTimeMillis().toString(),
        ),
        PreparationStepModel(
            id = "",
            idDrink = idDrink,
            order = 2,
            description = "Vierte partes iguales de ginebra, vermut rojo y Campari sobre el hielo.",
            timeRegister = System.currentTimeMillis().toString(),
            timeUpdate = System.currentTimeMillis().toString(),
        ),
        PreparationStepModel(
            id = "",
            idDrink = idDrink,
            order = 3,
            description = "Revuelve bien para mezclar los ingredientes.",
            timeRegister = System.currentTimeMillis().toString(),
            timeUpdate = System.currentTimeMillis().toString(),
        ),
        PreparationStepModel(
            id = "",
            idDrink = idDrink,
            order = 4,
            description = "Finalmente, adorna con una rodaja de naranja.",
            timeRegister = System.currentTimeMillis().toString(),
            timeUpdate = System.currentTimeMillis().toString(),
        )
    )

    private fun getPreparedStepsTequilaSunrise(idDrink: String): List<PreparationStepModel> =
        listOf(
            PreparationStepModel(
                id = "",
                idDrink = idDrink,
                order = 1,
                description = "Llena un vaso alto con cubitos de hielo.",
                timeRegister = System.currentTimeMillis().toString(),
                timeUpdate = System.currentTimeMillis().toString(),
            ),
            PreparationStepModel(
                id = "",
                idDrink = idDrink,
                order = 2,
                description = "Vierte el tequila sobre el hielo.",
                timeRegister = System.currentTimeMillis().toString(),
                timeUpdate = System.currentTimeMillis().toString(),
            ),
            PreparationStepModel(
                id = "",
                idDrink = idDrink,
                order = 3,
                description = "Añade jugo de naranja hasta casi llenar el vaso y revuelve bien.",
                timeRegister = System.currentTimeMillis().toString(),
                timeUpdate = System.currentTimeMillis().toString(),
            ),
            PreparationStepModel(
                id = "",
                idDrink = idDrink,
                order = 4,
                description = "Vierte lentamente la granadina en el vaso. Debería hundirse y luego subir lentamente para mezclarse con los otros ingredientes, creando el efecto de 'amanecer'.",
                timeRegister = System.currentTimeMillis().toString(),
                timeUpdate = System.currentTimeMillis().toString(),
            ),
            PreparationStepModel(
                id = "",
                idDrink = idDrink,
                order = 5,
                description = "Para decorar, puedes añadir una rodaja de naranja y una cereza al vaso.",
                timeRegister = System.currentTimeMillis().toString(),
                timeUpdate = System.currentTimeMillis().toString(),
            )
        )

    private fun getPreparedStepsDaiquiri(idDrink: String): List<PreparationStepModel> = listOf(
        PreparationStepModel(
            id = "",
            idDrink = idDrink,
            order = 1,
            description = "En una coctelera, añade el ron, el jugo de limón y el azúcar.",
            timeRegister = System.currentTimeMillis().toString(),
            timeUpdate = System.currentTimeMillis().toString(),
        ),
        PreparationStepModel(
            id = "",
            idDrink = idDrink,
            order = 2,
            description = "Llena la coctelera con hielo y agita bien hasta que los ingredientes estén bien mezclados y la mezcla esté fría.",
            timeRegister = System.currentTimeMillis().toString(),
            timeUpdate = System.currentTimeMillis().toString(),
        ),
        PreparationStepModel(
            id = "",
            idDrink = idDrink,
            order = 3,
            description = "Cuela la mezcla en una copa de cóctel fría.",
            timeRegister = System.currentTimeMillis().toString(),
            timeUpdate = System.currentTimeMillis().toString(),
        ),
        PreparationStepModel(
            id = "",
            idDrink = idDrink,
            order = 4,
            description = "Para decorar, puedes añadir una rodaja de limón a la copa.",
            timeRegister = System.currentTimeMillis().toString(),
            timeUpdate = System.currentTimeMillis().toString(),
        )
    )

    private fun getPreparedStepsWhiskeySour(idDrink: String): List<PreparationStepModel> = listOf(
        PreparationStepModel(
            id = "",
            idDrink = idDrink,
            order = 1,
            description = "En una coctelera, agrega el whiskey, el jugo de limón, el azúcar y la clara de huevo.",
            timeRegister = System.currentTimeMillis().toString(),
            timeUpdate = System.currentTimeMillis().toString(),
        ),
        PreparationStepModel(
            id = "",
            idDrink = idDrink,
            order = 2,
            description = "Realiza una 'agitación en seco' sin hielo para emulsionar la clara de huevo.",
            timeRegister = System.currentTimeMillis().toString(),
            timeUpdate = System.currentTimeMillis().toString(),
        ),
        PreparationStepModel(
            id = "",
            idDrink = idDrink,
            order = 3,
            description = "Añade hielo a la coctelera y agita de nuevo hasta que la mezcla esté bien fría.",
            timeRegister = System.currentTimeMillis().toString(),
            timeUpdate = System.currentTimeMillis().toString(),
        ),
        PreparationStepModel(
            id = "",
            idDrink = idDrink,
            order = 4,
            description = "Cuela la mezcla en un vaso bajo, preferiblemente con un colador fino para retener cualquier espuma de la clara de huevo.",
            timeRegister = System.currentTimeMillis().toString(),
            timeUpdate = System.currentTimeMillis().toString(),
        ),
        PreparationStepModel(
            id = "",
            idDrink = idDrink,
            order = 5,
            description = "Para decorar, añade una rodaja de naranja y una cereza marrasquino en el vaso.",
            timeRegister = System.currentTimeMillis().toString(),
            timeUpdate = System.currentTimeMillis().toString(),
        )
    )

    private fun getPreparedStepsLongIsland(idDrink: String): List<PreparationStepModel> = listOf(
        PreparationStepModel(
            id = "",
            idDrink = idDrink,
            order = 1,
            description = "En una coctelera, añade hielo, vodka, ron, tequila, ginebra, triple sec y jugo de limón.",
            timeRegister = System.currentTimeMillis().toString(),
            timeUpdate = System.currentTimeMillis().toString(),
        ),
        PreparationStepModel(
            id = "",
            idDrink = idDrink,
            order = 2,
            description = "Agita bien hasta que los ingredientes estén bien combinados y la mezcla esté fría.",
            timeRegister = System.currentTimeMillis().toString(),
            timeUpdate = System.currentTimeMillis().toString(),
        ),
        PreparationStepModel(
            id = "",
            idDrink = idDrink,
            order = 3,
            description = "Cuela la mezcla en un vaso alto lleno de hielo.",
            timeRegister = System.currentTimeMillis().toString(),
            timeUpdate = System.currentTimeMillis().toString(),
        ),
        PreparationStepModel(
            id = "",
            idDrink = idDrink,
            order = 4,
            description = "Añade un chorrito de refresco de cola para darle color y un toque de dulzor.",
            timeRegister = System.currentTimeMillis().toString(),
            timeUpdate = System.currentTimeMillis().toString(),
        ),
        PreparationStepModel(
            id = "",
            idDrink = idDrink,
            order = 5,
            description = "Decora con una rodaja de limón y una pajita. Disfruta de tu Long Island Iced Tea.",
            timeRegister = System.currentTimeMillis().toString(),
            timeUpdate = System.currentTimeMillis().toString(),
        )
    )

    private fun getPreparedStepsCaipirinha(idDrink: String): List<PreparationStepModel> = listOf(
        PreparationStepModel(
            id = "",
            idDrink = idDrink,
            order = 1,
            description = "Corta una lima en ocho partes. Coloca las partes en un vaso corto.",
            timeRegister = System.currentTimeMillis().toString(),
            timeUpdate = System.currentTimeMillis().toString(),
        ),
        PreparationStepModel(
            id = "",
            idDrink = idDrink,
            order = 2,
            description = "Añade dos cucharadas de azúcar al vaso.",
            timeRegister = System.currentTimeMillis().toString(),
            timeUpdate = System.currentTimeMillis().toString(),
        ),
        PreparationStepModel(
            id = "",
            idDrink = idDrink,
            order = 3,
            description = "Usa un mortero para aplastar la lima y el azúcar juntos, asegurándote de que los aceites esenciales de la lima y el azúcar se combinen bien.",
            timeRegister = System.currentTimeMillis().toString(),
            timeUpdate = System.currentTimeMillis().toString(),
        ),
        PreparationStepModel(
            id = "",
            idDrink = idDrink,
            order = 4,
            description = "Añade dos onzas de cachaça al vaso y mezcla bien.",
            timeRegister = System.currentTimeMillis().toString(),
            timeUpdate = System.currentTimeMillis().toString(),
        ),
        PreparationStepModel(
            id = "",
            idDrink = idDrink,
            order = 5,
            description = "Llena el resto del vaso con hielo picado y vuelve a mezclar. Disfruta de tu Caipirinha.",
            timeRegister = System.currentTimeMillis().toString(),
            timeUpdate = System.currentTimeMillis().toString(),
        )
    )

    private fun getPreparedStepsSidecar(idDrink: String): List<PreparationStepModel> = listOf(
        PreparationStepModel(
            id = "",
            idDrink = idDrink,
            order = 1,
            description = "Rodea el borde de una copa de cóctel con un limón y luego sumérgelo en azúcar para azucarar el borde.",
            timeRegister = System.currentTimeMillis().toString(),
            timeUpdate = System.currentTimeMillis().toString(),
        ),
        PreparationStepModel(
            id = "",
            idDrink = idDrink,
            order = 2,
            description = "En una coctelera, combina dos partes de coñac, una parte de Cointreau y una parte de zumo de limón.",
            timeRegister = System.currentTimeMillis().toString(),
            timeUpdate = System.currentTimeMillis().toString(),
        ),
        PreparationStepModel(
            id = "",
            idDrink = idDrink,
            order = 3,
            description = "Añade hielo y agita vigorosamente.",
            timeRegister = System.currentTimeMillis().toString(),
            timeUpdate = System.currentTimeMillis().toString(),
        ),
        PreparationStepModel(
            id = "",
            idDrink = idDrink,
            order = 4,
            description = "Cuela la mezcla en la copa de cóctel preparada. Disfruta de tu Sidecar.",
            timeRegister = System.currentTimeMillis().toString(),
            timeUpdate = System.currentTimeMillis().toString(),
        )
    )

    private fun getPreparedStepsIrishCoffee(idDrink: String): List<PreparationStepModel> = listOf(
        PreparationStepModel(
            id = "",
            idDrink = idDrink,
            order = 1,
            description = "Calienta una taza de café o vidrio resistente al calor con agua caliente, luego vacíala.",
            timeRegister = System.currentTimeMillis().toString(),
            timeUpdate = System.currentTimeMillis().toString(),
        ),
        PreparationStepModel(
            id = "",
            idDrink = idDrink,
            order = 2,
            description = "Agrega dos cucharaditas de azúcar moreno y una buena medida de whisky irlandés en la taza caliente.",
            timeRegister = System.currentTimeMillis().toString(),
            timeUpdate = System.currentTimeMillis().toString(),
        ),
        PreparationStepModel(
            id = "",
            idDrink = idDrink,
            order = 3,
            description = "Llena la taza con café caliente hasta que quede un espacio de centímetro y medio en la parte superior, luego revuelve hasta que el azúcar se disuelva completamente.",
            timeRegister = System.currentTimeMillis().toString(),
            timeUpdate = System.currentTimeMillis().toString(),
        ),
        PreparationStepModel(
            id = "",
            idDrink = idDrink,
            order = 4,
            description = "Cubre ligeramente la superficie del café con crema batida, asegurándote de que la crema flote en el café antes de servirlo.",
            timeRegister = System.currentTimeMillis().toString(),
            timeUpdate = System.currentTimeMillis().toString(),
        )
    )

    private fun getPreparedStepsPisco(idDrink: String): List<PreparationStepModel> = listOf(
        PreparationStepModel(
            id = "",
            idDrink = idDrink,
            order = 1,
            description = "En una coctelera, combina el Pisco, el jugo de limón, el jarabe de azúcar y la clara de huevo.",
            timeRegister = System.currentTimeMillis().toString(),
            timeUpdate = System.currentTimeMillis().toString(),
        ),
        PreparationStepModel(
            id = "",
            idDrink = idDrink,
            order = 2,
            description = "Agita bien todos estos ingredientes juntos sin hielo primero. Este proceso se llama \"dry shake\" y ayuda a que la clara de huevo se mezcle bien con los demás ingredientes, creando una espuma más densa y estable.",
            timeRegister = System.currentTimeMillis().toString(),
            timeUpdate = System.currentTimeMillis().toString(),
        ),
        PreparationStepModel(
            id = "",
            idDrink = idDrink,
            order = 3,
            description = "Luego añade cubitos de hielo a la coctelera y vuelve a agitar vigorosamente. Esto se llama \"wet shake\" y enfría la mezcla al tiempo que diluye ligeramente el cóctel.",
            timeRegister = System.currentTimeMillis().toString(),
            timeUpdate = System.currentTimeMillis().toString(),
        ),
        PreparationStepModel(
            id = "",
            idDrink = idDrink,
            order = 4,
            description = "Cuela la mezcla en una copa de cóctel (sin hielo) para obtener una superficie suave y espumosa en la parte superior. La espuma debe ser lo suficientemente gruesa como para soportar unas gotas de amargo de angostura.",
            timeRegister = System.currentTimeMillis().toString(),
            timeUpdate = System.currentTimeMillis().toString(),
        ),
        PreparationStepModel(
            id = "",
            idDrink = idDrink,
            order = 5,
            description = "Finalmente, añade unas gotas de amargo de angostura en la espuma para decorar. No solo añade un toque de color, sino también un aroma maravilloso que complementa los sabores del cóctel.",
            timeRegister = System.currentTimeMillis().toString(),
            timeUpdate = System.currentTimeMillis().toString(),
        )
    )

    private fun getPreparedStepsMaiTai(idDrink: String): List<PreparationStepModel> = listOf(
        PreparationStepModel(
            id = "",
            idDrink = idDrink,
            order = 1,
            description = "Llena una coctelera con hielo y añade 2 oz de ron oscuro, 1 oz de licor de naranja, 1 oz de jugo de lima, 1/2 oz de almíbar y 1/2 oz de orgeat (un jarabe de almendras).",
            timeRegister = System.currentTimeMillis().toString(),
            timeUpdate = System.currentTimeMillis().toString(),
        ),
        PreparationStepModel(
            id = "",
            idDrink = idDrink,
            order = 2,
            description = "Agita bien los ingredientes.",
            timeRegister = System.currentTimeMillis().toString(),
            timeUpdate = System.currentTimeMillis().toString(),
        ),
        PreparationStepModel(
            id = "",
            idDrink = idDrink,
            order = 3,
            description = "Vierta la mezcla en un vaso alto lleno de hielo.",
            timeRegister = System.currentTimeMillis().toString(),
            timeUpdate = System.currentTimeMillis().toString(),
        ),
        PreparationStepModel(
            id = "",
            idDrink = idDrink,
            order = 4,
            description = "Adorna con una ramita de menta y una rodaja de lima. Puedes añadir también una cereza de cóctel si lo deseas.",
            timeRegister = System.currentTimeMillis().toString(),
            timeUpdate = System.currentTimeMillis().toString(),
        )
    )

    private fun getPreparedStepsScrewdriver(idDrink: String): List<PreparationStepModel> = listOf(
        PreparationStepModel(
            id = "",
            idDrink = idDrink,
            order = 1,
            description = "Llena un vaso alto con hielo.",
            timeRegister = System.currentTimeMillis().toString(),
            timeUpdate = System.currentTimeMillis().toString(),
        ),
        PreparationStepModel(
            id = "",
            idDrink = idDrink,
            order = 2,
            description = "Añade 1.5 oz de vodka.",
            timeRegister = System.currentTimeMillis().toString(),
            timeUpdate = System.currentTimeMillis().toString(),
        ),
        PreparationStepModel(
            id = "",
            idDrink = idDrink,
            order = 3,
            description = "Rellena el resto del vaso con jugo de naranja fresco.",
            timeRegister = System.currentTimeMillis().toString(),
            timeUpdate = System.currentTimeMillis().toString(),
        ),
        PreparationStepModel(
            id = "",
            idDrink = idDrink,
            order = 4,
            description = "Remueve suavemente para mezclar.",
            timeRegister = System.currentTimeMillis().toString(),
            timeUpdate = System.currentTimeMillis().toString(),
        ),
        PreparationStepModel(
            id = "",
            idDrink = idDrink,
            order = 5,
            description = "Para decorar, puedes añadir una rodaja de naranja o una cereza de cóctel.",
            timeRegister = System.currentTimeMillis().toString(),
            timeUpdate = System.currentTimeMillis().toString(),
        )
    )

    private fun getPreparedStepsCubaLibre(idDrink: String): List<PreparationStepModel> = listOf(
        PreparationStepModel(
            id = "",
            idDrink = idDrink,
            order = 1,
            description = "Llena un vaso alto con hielo.",
            timeRegister = System.currentTimeMillis().toString(),
            timeUpdate = System.currentTimeMillis().toString(),
        ),
        PreparationStepModel(
            id = "",
            idDrink = idDrink,
            order = 2,
            description = "Añade 2 oz de ron blanco.",
            timeRegister = System.currentTimeMillis().toString(),
            timeUpdate = System.currentTimeMillis().toString(),
        ),
        PreparationStepModel(
            id = "",
            idDrink = idDrink,
            order = 3,
            description = "Exprime el jugo de medio limón en el vaso.",
            timeRegister = System.currentTimeMillis().toString(),
            timeUpdate = System.currentTimeMillis().toString(),
        ),
        PreparationStepModel(
            id = "",
            idDrink = idDrink,
            order = 4,
            description = "Rellena el resto del vaso con Coca-Cola.",
            timeRegister = System.currentTimeMillis().toString(),
            timeUpdate = System.currentTimeMillis().toString(),
        ),
        PreparationStepModel(
            id = "",
            idDrink = idDrink,
            order = 5,
            description = "Remueve suavemente para mezclar.",
            timeRegister = System.currentTimeMillis().toString(),
            timeUpdate = System.currentTimeMillis().toString(),
        ),
        PreparationStepModel(
            id = "",
            idDrink = idDrink,
            order = 6,
            description = "Para decorar, puedes añadir una rodaja de limón.",
            timeRegister = System.currentTimeMillis().toString(),
            timeUpdate = System.currentTimeMillis().toString(),
        )
    )

    private fun getPreparedStepsTomCollins(idDrink: String): List<PreparationStepModel> = listOf(
        PreparationStepModel(
            id = "",
            idDrink = idDrink,
            order = 1,
            description = "En una coctelera, agrega 2 oz de ginebra, 1 oz de jugo de limón y 1/2 oz de azúcar.",
            timeRegister = System.currentTimeMillis().toString(),
            timeUpdate = System.currentTimeMillis().toString(),
        ),
        PreparationStepModel(
            id = "",
            idDrink = idDrink,
            order = 2,
            description = "Agita bien hasta que la azúcar esté completamente disuelta.",
            timeRegister = System.currentTimeMillis().toString(),
            timeUpdate = System.currentTimeMillis().toString(),
        ),
        PreparationStepModel(
            id = "",
            idDrink = idDrink,
            order = 3,
            description = "Llena un vaso Collins con cubitos de hielo y vierte la mezcla en el vaso.",
            timeRegister = System.currentTimeMillis().toString(),
            timeUpdate = System.currentTimeMillis().toString(),
        ),
        PreparationStepModel(
            id = "",
            idDrink = idDrink,
            order = 4,
            description = "Llena el resto del vaso con agua carbonatada.",
            timeRegister = System.currentTimeMillis().toString(),
            timeUpdate = System.currentTimeMillis().toString(),
        ),
        PreparationStepModel(
            id = "",
            idDrink = idDrink,
            order = 5,
            description = "Remueve suavemente para mezclar y decora con una rodaja de limón y una cereza Maraschino.",
            timeRegister = System.currentTimeMillis().toString(),
            timeUpdate = System.currentTimeMillis().toString(),
        )
    )

    private fun getPreparedStepsBellini(idDrink: String): List<PreparationStepModel> = listOf(
        PreparationStepModel(
            id = "",
            idDrink = idDrink,
            order = 1,
            description = "En un procesador de alimentos o licuadora, puré de 1-2 duraznos blancos frescos hasta obtener una consistencia suave.",
            timeRegister = System.currentTimeMillis().toString(),
            timeUpdate = System.currentTimeMillis().toString(),
        ),
        PreparationStepModel(
            id = "",
            idDrink = idDrink,
            order = 2,
            description = "Añade un poco de azúcar al puré de durazno, al gusto, si los duraznos no son lo suficientemente dulces.",
            timeRegister = System.currentTimeMillis().toString(),
            timeUpdate = System.currentTimeMillis().toString(),
        ),
        PreparationStepModel(
            id = "",
            idDrink = idDrink,
            order = 3,
            description = "Llena aproximadamente un tercio de una copa de flauta con el puré de durazno.",
            timeRegister = System.currentTimeMillis().toString(),
            timeUpdate = System.currentTimeMillis().toString(),
        ),
        PreparationStepModel(
            id = "",
            idDrink = idDrink,
            order = 4,
            description = "Llena lentamente el resto de la copa con Prosecco frío, agitando suavemente la copa para mezclar.",
            timeRegister = System.currentTimeMillis().toString(),
            timeUpdate = System.currentTimeMillis().toString(),
        ),
        PreparationStepModel(
            id = "",
            idDrink = idDrink,
            order = 5,
            description = "Servir de inmediato.",
            timeRegister = System.currentTimeMillis().toString(),
            timeUpdate = System.currentTimeMillis().toString(),
        )
    )

    suspend fun saveDrink() {
        armarDrinkPreparationStep(0, getPreparedStepsMojito(""))
        armarDrinkPreparationStep(1, getPreparedStepsPinaColada(""))
        armarDrinkPreparationStep(2, getPreparedStepsMargarita(""))
        armarDrinkPreparationStep(3, getPreparedStepsBloodyMary(""))
        armarDrinkPreparationStep(4, getPreparedStepsCosmopolitan(""))
        armarDrinkPreparationStep(5, getPreparedStepsOldFashioned(""))
        armarDrinkPreparationStep(6, getPreparedStepsMartini(""))
        armarDrinkPreparationStep(7, getPreparedStepsNegroni(""))
        armarDrinkPreparationStep(8, getPreparedStepsTequilaSunrise(""))
        armarDrinkPreparationStep(9, getPreparedStepsDaiquiri(""))
        armarDrinkPreparationStep(10, getPreparedStepsWhiskeySour(""))
        armarDrinkPreparationStep(11, getPreparedStepsLongIsland(""))
        armarDrinkPreparationStep(12, getPreparedStepsCaipirinha(""))
        armarDrinkPreparationStep(13, getPreparedStepsSidecar(""))
        armarDrinkPreparationStep(14, getPreparedStepsIrishCoffee(""))
        armarDrinkPreparationStep(15, getPreparedStepsPisco(""))
        armarDrinkPreparationStep(16, getPreparedStepsMaiTai(""))
        armarDrinkPreparationStep(17, getPreparedStepsScrewdriver(""))
        armarDrinkPreparationStep(18, getPreparedStepsCubaLibre(""))
        armarDrinkPreparationStep(19, getPreparedStepsTomCollins(""))
        armarDrinkPreparationStep(20, getPreparedStepsBellini(""))
    }

    private val drinks = listOf(
        DrinkModel(
            id = "0",
            name = "Mojito",
            description = "El Mojito es un clásico cóctel cubano, perfecto para los días de calor. Combina el ron blanco con el frescor de la hierbabuena, el ácido del limón y un toque de dulzor. Su mezcla refrescante, suave y con ese punto cítrico, hace que sea una bebida muy popular en todo el mundo.",
            origin = "Cuba",
            photo = "https://firebasestorage.googleapis.com/v0/b/infinitespirit-ea254.appspot.com/o/Albert_photo_of_pisco_source_served_8a739bb7-d417-4bbc-b414-e7997f15d742.png?alt=media&token=7d27eada-89b5-4902-b063-8683ec55b41a&_gl=1*pjwlxc*_ga*MTYyNDg5MzQzMy4xNjgwNjQwNzAz*_ga_CW55HF8NVT*MTY4NjE5MzU5OC40Ny4xLjE2ODYxOTM2MDEuMC4wLjA.",
            timeUpdate = System.currentTimeMillis().toString(),
            timeRegister = System.currentTimeMillis().toString()
        ),
        DrinkModel(
            id = "1",
            name = "Piña Colada",
            description = "La Piña Colada es un cóctel tropical famoso por su sabor a piña y coco. A menudo se sirve mezclado o agitado con hielo y es perfecto para los días de calor.",
            origin = "Puerto Rico",
            photo = "",
            timeUpdate = System.currentTimeMillis().toString(),
            timeRegister = System.currentTimeMillis().toString()
        ),
        DrinkModel(
            id = "2",
            name = "Margarita",
            description = "La Margarita es un famoso cóctel mexicano que combina el sabor agrio del jugo de limón, el dulzor del jarabe de azúcar y el fuerte sabor del tequila. Es una bebida perfecta para las noches de verano o para acompañar un plato de tacos.",
            origin = "México",
            photo = "https://firebasestorage.googleapis.com/v0/b/infinitespirit-ea254.appspot.com/o/Albert_photo_of_pisco_source_served_8a739bb7-d417-4bbc-b414-e7997f15d742.png?alt=media&token=7d27eada-89b5-4902-b063-8683ec55b41a&_gl=1*pjwlxc*_ga*MTYyNDg5MzQzMy4xNjgwNjQwNzAz*_ga_CW55HF8NVT*MTY4NjE5MzU5OC40Ny4xLjE2ODYxOTM2MDEuMC4wLjA.",
            timeUpdate = System.currentTimeMillis().toString(),
            timeRegister = System.currentTimeMillis().toString()
        ), DrinkModel(
            id = "3",
            name = "Bloody Mary",
            description = "El Bloody Mary es un cóctel clásico conocido en todo el mundo. Con su combinación única de vodka, jugo de tomate, salsa Worcestershire, salsa picante, limón y especias, este cóctel salado es a menudo disfrutado en el brunch o como un 'antídoto' para la resaca.",
            origin = "Estados Unidos",
            photo = "",
            timeUpdate = System.currentTimeMillis().toString(),
            timeRegister = System.currentTimeMillis().toString()
        ), DrinkModel(
            id = "4",
            name = "Cosmopolitan",
            description = "El Cosmopolitan, también conocido como Cosmo, es un cóctel sofisticado y refrescante que combina vodka, triple sec, jugo de arándanos y lima. Es conocido en todo el mundo, en parte gracias a la popularidad de la serie de televisión 'Sexo en la Ciudad'.",
            origin = "Estados Unidos",
            photo = "",
            timeUpdate = System.currentTimeMillis().toString(),
            timeRegister = System.currentTimeMillis().toString()
        ), DrinkModel(
            id = "5",
            name = "Old Fashioned",
            description = "El Old Fashioned es un cóctel clásico y uno de los más antiguos conocidos. Este cóctel combina bourbon o whisky de centeno con un toque de azúcar, amargos y un toque de agua. Se sirve en un vaso corto, sobre hielo, a menudo con un simple adorno de una rodaja de naranja y una cereza.",
            origin = "Estados Unidos",
            photo = "",
            timeUpdate = System.currentTimeMillis().toString(),
            timeRegister = System.currentTimeMillis().toString()
        ), DrinkModel(
            id = "6",
            name = "Martini",
            description = "El Martini es uno de los cócteles más conocidos y populares del mundo. Este cóctel clásico combina ginebra y vermut seco, y se sirve en una copa de cóctel con una aceituna o una rodaja de limón como adorno.",
            origin = "Estados Unidos",
            photo = "",
            timeUpdate = System.currentTimeMillis().toString(),
            timeRegister = System.currentTimeMillis().toString()
        ), DrinkModel(
            id = "7",
            name = "Negroni",
            description = "El Negroni es un cóctel italiano amargo y refrescante. Es un aperitivo clásico que combina partes iguales de ginebra, vermut rojo y Campari, y se sirve generalmente con una rodaja de naranja para realzar su sabor cítrico.",
            origin = "Italia",
            photo = "",
            timeUpdate = System.currentTimeMillis().toString(),
            timeRegister = System.currentTimeMillis().toString()
        ),
        DrinkModel(
            id = "8",
            name = "Tequila Sunrise",
            description = "El Tequila Sunrise es un cóctel que se destaca por su presentación con colores que recuerdan a un amanecer. Con una base de tequila, la dulzura del jugo de naranja y el toque del granadina, este cóctel es una delicia tropical.",
            origin = "México",
            photo = "",
            timeUpdate = System.currentTimeMillis().toString(),
            timeRegister = System.currentTimeMillis().toString()
        ), DrinkModel(
            id = "9",
            name = "Daiquiri",
            description = "El Daiquiri es un clásico cóctel cubano que es refrescante y ácido a la vez. Elaborado con ron, jugo de limón y azúcar, es una bebida sencilla pero deliciosa que se disfruta mejor fresca.",
            origin = "Cuba",
            photo = "",
            timeUpdate = System.currentTimeMillis().toString(),
            timeRegister = System.currentTimeMillis().toString()
        ),
        DrinkModel(
            id = "10",
            name = "Whiskey Sour",
            description = "El Whiskey Sour es un cóctel clásico que combina el whiskey, preferiblemente bourbon, con jugo de limón y un toque de azúcar. La adición de una clara de huevo le da una rica textura cremosa.",
            origin = "Estados Unidos",
            photo = "",
            timeUpdate = System.currentTimeMillis().toString(),
            timeRegister = System.currentTimeMillis().toString()
        ),
        DrinkModel(
            id = "11",
            name = "Long Island Iced Tea",
            description = "El Long Island Iced Tea es un cóctel alto, refrescante y lleno de sabor. Es conocido por su alta concentración de alcohol, ya que incluye vodka, ron, tequila, ginebra y triple sec, todo equilibrado con jugo de limón y un toque de refresco de cola.",
            origin = "Estados Unidos",
            photo = "",
            timeUpdate = System.currentTimeMillis().toString(),
            timeRegister = System.currentTimeMillis().toString()
        ), DrinkModel(
            id = "12",
            name = "Caipirinha",
            description = "La Caipirinha es la bebida nacional de Brasil, famosa en todo el mundo. La combinación de cachaça, azúcar y lima hace que este cóctel sea refrescante y fuerte a la vez.",
            origin = "Brasil",
            photo = "",
            timeUpdate = System.currentTimeMillis().toString(),
            timeRegister = System.currentTimeMillis().toString()
        ), DrinkModel(
            id = "13",
            name = "Sidecar",
            description = "El Sidecar es un cóctel clásico originario de la época de la prohibición. Es una mezcla de coñac, Cointreau y zumo de limón que se sirve normalmente en una copa de cóctel con el borde azucarado.",
            origin = "Francia",
            photo = "",
            timeUpdate = System.currentTimeMillis().toString(),
            timeRegister = System.currentTimeMillis().toString()
        ), DrinkModel(
            id = "14",
            name = "Irish Coffee",
            description = "El Irish Coffee es un cóctel caliente clásico que combina café, whisky irlandés, azúcar y crema. Es conocido por su preparación única y su equilibrio perfecto de sabores.",
            origin = "Irlanda",
            photo = "",
            timeUpdate = System.currentTimeMillis().toString(),
            timeRegister = System.currentTimeMillis().toString()
        ), DrinkModel(
            id = "15",
            name = "Pisco Sour",
            description = "El Pisco Sour es un cóctel tradicional que proviene de Perú. Es bien conocido por su equilibrio entre el sabor ácido y dulce, complementado por la suavidad del Pisco, una especie de aguardiente de uva. Además, la espuma que se crea a partir de la clara de huevo le da al cóctel una textura única y agradable.",
            origin = "Perú",
            photo = "https://firebasestorage.googleapis.com/v0/b/infinitespirit-ea254.appspot.com/o/Albert_photo_of_pisco_source_served_8a739bb7-d417-4bbc-b414-e7997f15d742.png?alt=media&token=7d27eada-89b5-4902-b063-8683ec55b41a&_gl=1*pjwlxc*_ga*MTYyNDg5MzQzMy4xNjgwNjQwNzAz*_ga_CW55HF8NVT*MTY4NjE5MzU5OC40Ny4xLjE2ODYxOTM2MDEuMC4wLjA.",
            timeUpdate = System.currentTimeMillis().toString(),
            timeRegister = System.currentTimeMillis().toString()
        ), DrinkModel(
            id = "16",
            name = "Mai Tai",
            description = "El Mai Tai es un cóctel famoso de la cultura Tiki, reconocido por su sabor a ron y su vibrante color. El nombre significa 'lo mejor' en tahitiano, y con su mezcla de ron, licor de naranja, almíbar, lima y menta, realmente hace honor a su nombre.",
            origin = "Estados Unidos",
            photo = "",
            timeUpdate = System.currentTimeMillis().toString(),
            timeRegister = System.currentTimeMillis().toString()
        ),
        DrinkModel(
            id = "",
            name = "Screwdriver",
            description = "El Screwdriver es un cóctel sencillo y refrescante hecho con dos ingredientes: vodka y jugo de naranja. Originado a mediados del siglo XX, se ha convertido en un básico en bares de todo el mundo. A pesar de su sencillez, cuando se hace bien, puede ser una bebida impresionantemente sabrosa y refrescante.",
            origin = "Desconocido",
            photo = "",
            timeUpdate = System.currentTimeMillis().toString(),
            timeRegister = System.currentTimeMillis().toString()
        ), DrinkModel(
            id = "",
            name = "Cuba Libre",
            description = "El Cuba Libre es un cóctel popular que data de principios del siglo XX. Tradicionalmente se hace con ron ligero, Coca-Cola y limón. El nombre 'Cuba Libre', que significa 'Cuba libre' en español, se dice que fue un grito de batalla de los cubanos durante la guerra de independencia contra España.",
            origin = "Cuba",
            photo = "",
            timeUpdate = System.currentTimeMillis().toString(),
            timeRegister = System.currentTimeMillis().toString()
        ), DrinkModel(
            id = "",
            name = "Tom Collins",
            description = "El Tom Collins es un cóctel clásico a base de ginebra que ha sido apreciado por su refrescante sabor cítrico desde el siglo XIX. El cóctel se hace típicamente con ginebra, jugo de limón, azúcar y agua carbonatada, y se sirve en un vaso 'Collins' con hielo y una rodaja de limón.",
            origin = "Nueva York, Estados Unidos",
            photo = "",
            timeUpdate = System.currentTimeMillis().toString(),
            timeRegister = System.currentTimeMillis().toString()
        ), DrinkModel(
            id = "",
            name = "Bellini",
            description = "El Bellini es un cóctel que proviene de Venecia, Italia. Es una mezcla simple y elegante de duraznos blancos frescos y Prosecco, un vino espumoso italiano. Se sirve tradicionalmente en una copa de flauta y es especialmente popular en eventos de brunch.",
            origin = "Venecia, Italia",
            photo = "",
            timeUpdate = System.currentTimeMillis().toString(),
            timeRegister = System.currentTimeMillis().toString()
        )
    )

    suspend fun armarDrinkPreparationStep(
        position: Int,
        preparationSteps: List<PreparationStepModel>,
    ) {
        val idDrink = saveDrinkUseCase(drinks[position])
        idDrink?.let { preparationSteps.map { it.idDrink = idDrink } }
        preparationSteps.map { savePreparationStepUseCase(it) }
        if (position == 0) {
            idDrink?.let { getIngredientsMojito(it).map { saveDrinkIngredientUseCase(it) } }
        }
    }

    suspend fun saveIngredients() {
        getIngredients().map { saveIngredientUseCase.invoke(it) }
    }

    suspend fun getIngredientsMojito(idDrink: String) = listOf(
        DrinkIngredientModel(
            id = "",
            idDrink = idDrink,
            idIngredient = "1",
            quantity = "2 onzas"
        ),
        DrinkIngredientModel(
            id = "",
            idDrink = idDrink,
            idIngredient = "2",
            quantity = "10 hojas"
        ),
        DrinkIngredientModel(
            id = "",
            idDrink = idDrink,
            idIngredient = "3",
            quantity = "2 cucharadas"
        ),
        DrinkIngredientModel(
            id = "",
            idDrink = idDrink,
            idIngredient = "4",
            quantity = "1 onza"
        ),
    )

    fun getIngredients() = listOf(
        IngredientModel(
            id = "1",
            name = "Ron Blanco",
            type = "Licor"
        ),
        IngredientModel(
            id = "2",
            name = "Hierbabuena",
            type = "Hierba"
        ),
        IngredientModel(
            id = "3",
            name = "Azúcar",
            type = "Aditivo"
        ),
        IngredientModel(
            id = "4",
            name = "Jugo de Limón",
            type = "Jugo"
        ),
        IngredientModel(
            id = "5",
            name = "Soda",
            type = "Aditivo"
        ),
        IngredientModel(
            id = "6",
            name = "Cubitos de Hielo",
            type = "Aditivo"
        ),
        IngredientModel(
            id = "7",
            name = "Pisco",
            type = "Licor"
        ),
        IngredientModel(
            id = "8",
            name = "Clara de Huevo",
            type = "Aditivo"
        ),
        IngredientModel(
            id = "9",
            name = "Amargo de Angostura",
            type = "Licor"
        ),
        IngredientModel(
            id = "10",
            name = "Jugo de Naranja",
            type = "Jugo"
        ),
        IngredientModel(
            id = "11",
            name = "Tequila",
            type = "Licor"
        ),
        IngredientModel(
            id = "12",
            name = "Jugo de Granadina",
            type = "Jugo"
        ),
        IngredientModel(
            id = "13",
            name = "Jugo de Piña",
            type = "Jugo"
        ),
        IngredientModel(
            id = "14",
            name = "Cerveza de Ginger",
            type = "Aditivo"
        ),
        IngredientModel(
            id = "15",
            name = "Whiskey",
            type = "Licor"
        ),
        IngredientModel(
            id = "16",
            name = "Vermut Rojo",
            type = "Licor"
        ),
        IngredientModel(
            id = "17",
            name = "Campari",
            type = "Licor"
        ),
        IngredientModel(
            id = "18",
            name = "Ginebra",
            type = "Licor"
        ),
        IngredientModel(
            id = "19",
            name = "Ron",
            type = "Licor"
        ),
        IngredientModel(
            id = "20",
            name = "Jugo de Limón",
            type = "Jugo"
        ),
        IngredientModel(
            id = "21",
            name = "Vodka",
            type = "Licor"
        ),
        IngredientModel(
            id = "22",
            name = "Arándanos Rojos",
            type = "Fruta"
        ),
        IngredientModel(
            id = "23",
            name = "Triple Sec",
            type = "Licor"
        ),
        IngredientModel(
            id = "24",
            name = "Licor de Naranja",
            type = "Licor"
        ),
        IngredientModel(
            id = "25",
            name = "Bourbon",
            type = "Licor"
        ),
        IngredientModel(
            id = "26",
            name = "Vermut Seco",
            type = "Licor"
        ),
        IngredientModel(
            id = "27",
            name = "Licor de Cerezas",
            type = "Licor"
        ),
        IngredientModel(
            id = "28",
            name = "Jugo de Naranja",
            type = "Jugo"
        ),
        IngredientModel(
            id = "29",
            name = "Jugo de Pomelo",
            type = "Jugo"
        ),
        IngredientModel(
            id = "30",
            name = "Ron Oscuro",
            type = "Licor"
        ),
        IngredientModel(
            id = "31",
            name = "Jugo de Piña",
            type = "Jugo"
        ),
        IngredientModel(
            id = "32",
            name = "Crema de Coco",
            type = "Lácteo"
        ),
        IngredientModel(
            id = "33",
            name = "Licor de Almendras",
            type = "Licor"
        ),
        IngredientModel(
            id = "34",
            name = "Jugo de Naranja",
            type = "Jugo"
        ),
        IngredientModel(
            id = "35",
            name = "Refresco de Cola",
            type = "Refresco"
        ),
        IngredientModel(
            id = "36",
            name = "Jugo de Limón",
            type = "Jugo"
        ),
        IngredientModel(
            id = "37",
            name = "Prosecco",
            type = "Vino"
        ),
        IngredientModel(
            id = "38",
            name = "Puré de Durazno",
            type = "Puré"
        )
    )

}