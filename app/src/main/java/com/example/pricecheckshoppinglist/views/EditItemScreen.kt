package com.example.pricecheckshoppinglist.views

import android.R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.pricecheckshoppinglist.viewModels.ItemViewModel

@Composable
fun EditItemScreen(
    viewModel: ItemViewModel,
    modifier: Modifier,
    onBackClick: () -> Unit,
    onMainPageClick: () -> Unit,
    editItem: String,
    store: String
){
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var name = remember { mutableStateOf("") }
        var price = remember { mutableStateOf("") }
        var unit = remember { mutableStateOf("") }
        var quantity = remember { mutableStateOf("") }
        val regex = "\\d+\\.\\d{1,2}".toRegex()
        val unitRegex = "\\d+".toRegex()
        var title = "Add New Item"
        var storeName = "Choose a Store"

        if(!editItem.isEmpty()){
            name.value = editItem
            title = "Edit $editItem"
            price.value = viewModel.getPrice(editItem, store).toString()
            unit.value = viewModel.getUnit(editItem, store)
            quantity.value = viewModel.getQuantity(editItem, store).toString()
        }

        if(!store.isEmpty()){
            storeName = "Price at $store:"
        }

        Button(onClick = onBackClick,
            modifier = modifier.align(Alignment.Start)) {
            Text("Back")
        }
        Text(title,
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center)
        Column(modifier = Modifier.weight(.9f)) {
            Text("Name")
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth()
                    .padding(bottom = 15.dp),
                value = name.value,
                onValueChange = { name.value = it },
                label = { Text("Item Name") }
            )
            Text(storeName,
                modifier = Modifier.padding(bottom = 5.dp),
                style = MaterialTheme.typography.titleMedium)
            Row {
                Text("Price", modifier.width(120.dp))
                Spacer(Modifier.width(10.dp))
                Text("Unit", modifier.width(120.dp))
                Spacer(Modifier.width(10.dp))
                Text("Quantity", modifier.width(120.dp))
            }
            Row {
                OutlinedTextField(
                    modifier = Modifier.width(120.dp),
                    value = price.value,
                    onValueChange = { price.value = it },
                    label = { Text("Price") }
                )
                Spacer(Modifier.width(10.dp))
                OutlinedTextField(
                    modifier = Modifier.width(120.dp),
                    value = unit.value,
                    onValueChange = { unit.value = it },
                    label = { Text("Unit") }
                )
                Spacer(Modifier.width(10.dp))
                OutlinedTextField(
                    modifier = Modifier.width(120.dp),
                    value = quantity.value,
                    onValueChange = { quantity.value = it },
                    label = { Text("Quantity") }
                )
            }
        }
        Row(modifier = modifier,
            horizontalArrangement = Arrangement.Center) {
            Button(onClick = onMainPageClick) {
                Text("Home Page")
            }
            Spacer(modifier = Modifier.width(20.dp))
            Button(
                enabled = name.value.isNotBlank(),
                onClick = {
                    viewModel.addItem(name.value)
                    viewModel.setStore(name.value, store)
                    if(!price.value.isEmpty()){
                        if(regex.matches(price.value)){
                            viewModel.editPrice(name.value, store, price.value.toDouble())}
                    }
                    if(!unit.value.isEmpty()){
                        viewModel.editUnit(name.value, store, unit.value)
                    }
                    if(!quantity.value.isEmpty()){
                        if(regex.matches(quantity.value) || unitRegex.matches(quantity.value)){
                            viewModel.editQuantity(name.value, store, quantity.value.toDouble())}
                    }
                }) {
                Text("Save")
            }
        }
    }
}