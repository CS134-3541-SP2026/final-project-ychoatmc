package com.example.pricecheckshoppinglist.views

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
import com.example.pricecheckshoppinglist.viewModels.ItemViewModel

@Composable
fun ListScreen(
    viewModel: ItemViewModel,
    modifier: Modifier,
    onBackClick: () -> Unit,
    onEditItemPageClick: (String) -> Unit,
    storeName: String
){
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onBackClick,
            modifier = Modifier.align(Alignment.Start)){
            Text("Back")
        }
        Text(storeName,
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center)
        Column(modifier = Modifier.weight(.9f),
            horizontalAlignment = Alignment.CenterHorizontally) {
            if (viewModel.itemCount() != 0)
                viewModel.allItems.collectAsState().value.forEach {
                    Button({onEditItemPageClick(it.name)}) {
                        Text(it.name)
                    }
                }
        }
        Button(onClick = {onEditItemPageClick("")}) {
            Text("Add New Item")
        }
    }
}