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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import com.example.pricecheckshoppinglist.viewModels.StoreViewModel

@Composable
fun ChooseStoreScreen(
    viewModel: StoreViewModel,
    modifier: Modifier,
    onBackClick: () -> Unit,
    onEditStoreClick: () -> Unit
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
        Text("Click on Store to Edit",
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center)
        Column(modifier = Modifier.weight(.9f),
            horizontalAlignment = Alignment.CenterHorizontally) {
            if (viewModel.storeCount() != 0)
                viewModel.stores.collectAsState().value.forEach {
                    Button({onEditStoreClick()}) {
                        Text(it.name)
                    }
                }
        }
        Button(onClick = {onEditStoreClick()}) {
            Text("Add New Store")
        }
    }
}



