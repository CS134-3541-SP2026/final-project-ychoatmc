package com.example.pricecheckshoppinglist

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.viewModelScope
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
import kotlinx.coroutines.launch

val Context.dataStore by preferencesDataStore("store_prefs")
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
    val LIST_SCREEN = "list_screen/{storeName}"
    val EDIT_ITEM_SCREEN = "edit_item_screen/{itemName}/{storeName}"
    fun editStoreScreen(storeName: String): String {
        return "store_edit_screen/$storeName"
    }

    fun storesListScreen(storeName: String): String{
        return "list_screen/$storeName"
    }

    fun itemEditScreen(itemName: String, storeName: String): String{
        return "edit_item_screen/$itemName/$storeName"
    }
}

@SuppressLint("FlowOperatorInvokedInComposition", "CoroutineCreationDuringComposition")
@PreviewScreenSizes
@Composable
fun PriceCheckShoppingApp() {
    val context: Context = LocalContext.current
    val scope = rememberCoroutineScope()
    val homeViewModel: StoreViewModel = viewModel()
    val itemViewModel: ItemViewModel = viewModel()
    val shoppingListNavController = rememberNavController()


    homeViewModel.viewModelScope.launch {
        homeViewModel.load(context)
    }


    scope.launch {
        itemViewModel.load(context)
    }

    NavHost(
        navController = shoppingListNavController,
        startDestination = Destinations.HOME_SCREEN
    ){
        composable(route = Destinations.HOME_SCREEN){
            HomeScreen(modifier = Modifier,
                onStoreEditClick = {shoppingListNavController.navigate(Destinations.STORE_SCREEN)},
                onListClick = {
                    storeName ->
                    shoppingListNavController.navigate(route = Destinations.storesListScreen(storeName))},
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
                editStore = storeName,
                onSaveClick =
                {
                    currentStore ->
                    scope.launch {
                        homeViewModel.save(context, currentStore)
                    }
                })
        }
        composable (route = Destinations.LIST_SCREEN,
            arguments = listOf(navArgument("storeName"){
                type = NavType.StringType
            })){
            backStackEntry ->
            val storeName = backStackEntry.arguments?.getString("storeName")
                ?: return@composable
            ListScreen(modifier = Modifier,
                viewModel = itemViewModel,
                onBackClick = {shoppingListNavController.popBackStack()},
                onEditItemPageClick = {
                    itemName, storeName ->
                    shoppingListNavController.navigate(Destinations.itemEditScreen(itemName, storeName))},
                storeName = storeName,
                storeViewModel = homeViewModel)
        }
        composable (route = Destinations.EDIT_ITEM_SCREEN,
            arguments = listOf(navArgument("itemName"){
                type = NavType.StringType
            })){
            backStackEntry ->
            val itemName = backStackEntry.arguments?.getString("itemName")
                ?: return@composable
            val storeName = backStackEntry.arguments?.getString("storeName")
                ?: return@composable
            EditItemScreen(modifier = Modifier,
                onBackClick = {shoppingListNavController.popBackStack()},
                viewModel = itemViewModel,
                editItem = itemName,
                store = storeName,
                onMainPageClick = {shoppingListNavController.navigate(Destinations.HOME_SCREEN)},
                onSaveClick = {
                    currentItem ->
                    scope.launch {
                        itemViewModel.save(context, currentItem)
                    }
                }
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

@Preview(showBackground = true)
@Composable
fun PriceCheckShoppingAppPreview() {
    PriceCheckShoppingListTheme {
        PriceCheckShoppingApp()
    }
}