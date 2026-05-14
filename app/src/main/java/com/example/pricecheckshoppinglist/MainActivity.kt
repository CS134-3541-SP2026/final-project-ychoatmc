package com.example.pricecheckshoppinglist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import com.example.pricecheckshoppinglist.viewModels.StoreViewModel
import com.example.pricecheckshoppinglist.ui.theme.PriceCheckShoppingListTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pricecheckshoppinglist.viewModels.ItemViewModel
import com.example.pricecheckshoppinglist.views.ChooseStoreScreen
import com.example.pricecheckshoppinglist.views.EditItemScreen
import com.example.pricecheckshoppinglist.views.EditStoreScreen
import com.example.pricecheckshoppinglist.views.HomeScreen
import com.example.pricecheckshoppinglist.views.ListScreen
import java.text.DecimalFormatSymbols


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            PriceCheckShoppingApp()
        }
    }
}

object Destinations {
    val HOME_SCREEN = "home_screen"
    val STORE_SCREEN = "store_screen"
    val STORE_EDIT_SCREEN = "store_edit_screen/{storeName}"
    val LIST_SCREEN = "list_screen"
    val EDIT_ITEM_SCREEN = "edit_item_screen"
    fun editStoreScreen(storeName: String): String {
        return "store_edit_screen/$storeName"
    }
}

@PreviewScreenSizes
@Composable
fun PriceCheckShoppingApp() {
    var currentDestination by rememberSaveable() { mutableStateOf(AppDestinations.HOME)}
    val homeViewModel: StoreViewModel = viewModel()
    val itemViewModel: ItemViewModel = viewModel()
    val shoppingListNavController = rememberNavController()

    NavHost(
        navController = shoppingListNavController,
        startDestination = Destinations.HOME_SCREEN
    ){
        composable(route = Destinations.HOME_SCREEN){
            HomeScreen(modifier = Modifier,
                onStoreEditClick = {shoppingListNavController.navigate(Destinations.STORE_SCREEN)},
                onListClick = {shoppingListNavController.navigate(route = Destinations.LIST_SCREEN)},
                viewModel = homeViewModel)
        }
        composable (route = Destinations.STORE_SCREEN){
            ChooseStoreScreen(
                modifier = Modifier,
                onBackClick = { shoppingListNavController.popBackStack() },
                onEditStoreClick = {
                    storeName ->
                    shoppingListNavController.navigate(Destinations.editStoreScreen(storeName))},
                viewModel = homeViewModel
            )
        }
        composable (route = Destinations.STORE_EDIT_SCREEN,
            arguments = listOf(navArgument("storeName"){
                type = NavType.StringType
            })) {
            backStackEntry ->
            val storeName = backStackEntry.arguments?.getString("storeName")
                ?: return@composable
            EditStoreScreen(modifier = Modifier,
                onBackClick = {shoppingListNavController.popBackStack()},
                onMainPageClick = {shoppingListNavController.navigate(Destinations.HOME_SCREEN)},
                viewModel = homeViewModel,
                editStore = storeName
            )
        }
        composable (route = Destinations.LIST_SCREEN){
            ListScreen(modifier = Modifier,
                viewModel = itemViewModel,
                onBackClick = {shoppingListNavController.popBackStack()},
                onEditItemPageClick = {shoppingListNavController.navigate(Destinations.EDIT_ITEM_SCREEN)})
        }
        composable (route = Destinations.EDIT_ITEM_SCREEN){
            EditItemScreen(modifier = Modifier,
                onBackClick = {shoppingListNavController.popBackStack()},
                viewModel = itemViewModel,
                onMainPageClick = {shoppingListNavController.navigate(Destinations.HOME_SCREEN)}
            )
        }
    }
    /**
    NavigationSuiteScaffold(
        navigationSuiteItems = {
            AppDestinations.entries.forEach {
                item(
                    icon = {
                        Icon(
                            painter = painterResource(it.icon),
                            contentDescription = it.label
                        )
                    },
                    label = {Text(it.label)},
                    selected = it == currentDestination,
                    onClick = {currentDestination = it}
                )
            }
        }
    ){
        Scaffold(modifier = Modifier.fillMaxSize()) {innerPadding ->
            when (currentDestination){
                AppDestinations.HOME -> MainStoreView(
                    viewModel = homeViewModel,
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }
    }*/
}

enum class AppDestinations(
    val label: String,
    val icon: Int
){
    HOME("Home", R.drawable.outline_home_24)
}

@Preview(showBackground = true)
@Composable
fun PriceCheckShoppingAppPreview() {
    PriceCheckShoppingListTheme {
        PriceCheckShoppingApp()
    }
}