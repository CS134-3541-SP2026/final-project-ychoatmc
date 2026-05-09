package com.example.pricecheckshoppinglist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pricecheckshoppinglist.ViewModel.StoreViewModel
import com.example.pricecheckshoppinglist.ui.theme.PriceCheckShoppingListTheme
import com.example.pricecheckshoppinglist.Views.MainStoreView
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pricecheckshoppinglist.Views.ChooseStoreEditView
import com.example.pricecheckshoppinglist.Views.EditItemView
import com.example.pricecheckshoppinglist.Views.ListView
import com.example.pricecheckshoppinglist.Views.StoreEditView


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            PriceCheckShoppingApp()
        }
    }
}

object Destinations{
    val HOME_SCREEN = "home_screen"
    val STORE_SCREEN = "store_screen"
    val STORE_EDIT_SCREEN = "store_edit_screen"
    val LIST_SCREEN = "list_screen"
    val EDIT_ITEM_SCREEN = "edit_item_screen"
}

@PreviewScreenSizes
@Composable
fun PriceCheckShoppingApp() {
    var currentDestination by rememberSaveable() { mutableStateOf(AppDestinations.HOME)}
    val homeViewModel: StoreViewModel = viewModel()
    val shoppingListNavController = rememberNavController()

    NavHost(
        navController = shoppingListNavController,
        startDestination = Destinations.HOME_SCREEN
    ){
        composable(route = Destinations.HOME_SCREEN){
            MainStoreView(modifier = Modifier,
                onStoreEditClick = {shoppingListNavController.navigate(Destinations.STORE_SCREEN)},
                onListClick = {shoppingListNavController.navigate(route = Destinations.LIST_SCREEN)})
        }
        composable (route = Destinations.STORE_SCREEN){
            ChooseStoreEditView(modifier = Modifier,
                onBackClick = {shoppingListNavController.popBackStack()},
                onEditPageClick = {shoppingListNavController.navigate(Destinations.STORE_EDIT_SCREEN)})
        }
        composable (route = Destinations.STORE_EDIT_SCREEN) {
            StoreEditView(modifier = Modifier,
                onBackClick = {shoppingListNavController.popBackStack()},
                onMainPageClick = {shoppingListNavController.navigate(Destinations.HOME_SCREEN)})
        }
        composable (route = Destinations.LIST_SCREEN){
            ListView(modifier = Modifier,
                onBackClick = {shoppingListNavController.popBackStack()},
                onEditItemPageClick = {shoppingListNavController.navigate(Destinations.EDIT_ITEM_SCREEN)})
        }
        composable (route = Destinations.EDIT_ITEM_SCREEN){
            EditItemView(modifier = Modifier,
                onBackClick = {shoppingListNavController.popBackStack()},
                onMainPageClick = {shoppingListNavController.navigate(Destinations.HOME_SCREEN)})
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