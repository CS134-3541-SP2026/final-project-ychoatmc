package com.example.pricecheckshoppinglist.views

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.pricecheckshoppinglist.viewModels.StoreViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@Composable
fun HomeScreen(modifier: Modifier, onStoreEditClick: () -> Unit, onListClick: (String) -> Unit,
               viewModel: StoreViewModel
){
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text("Smarter Shopping App",
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center)
        Column(modifier = Modifier.weight(.5f),
            horizontalAlignment = Alignment.CenterHorizontally) {
            if (viewModel.storeCount() != 0) {
                viewModel.stores.collectAsState().value.forEach {
                    Button({ onListClick(it.name) }) {
                        Text(it.name)
                    }
                }
            }
        }
        Button(onClick = onStoreEditClick) {Text("Add/Edit Store") }
    }
}
