package com.example.pricecheckshoppinglist.Views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pricecheckshoppinglist.ViewModel.ItemViewModel
import com.example.pricecheckshoppinglist.ViewModel.StoreViewModel

@Composable
fun ListView(
    viewModel: ItemViewModel = viewModel(),
    modifier: Modifier,
    onBackClick: () -> Unit,
    onEditItemPageClick: () -> Unit
){
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Button(onBackClick) {
            Text("Back")
        }
        Text("Temporary Title Store One")
        Button(onClick = onEditItemPageClick) {
            Text("Temporary Item One")
        }
    }
}

@Composable
fun EditItemView(
    viewModel: ItemViewModel = viewModel(),
    modifier: Modifier,
    onBackClick: () -> Unit,
    onMainPageClick: () -> Unit
){
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Button(onClick = onBackClick) {
            Text("Back")
        }
        Text("Temporary Edit Item One")
        Button(onClick = onMainPageClick) {
            Text("Home Page")
        }
    }
}