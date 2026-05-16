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
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
        var initialName = "Item Name"
        var initalPrice = "Price"
        var initialUnit = "Unit"
        var initialQuantity = "Quantity"
        val regex = "\\d+\\.\\d{1,2}".toRegex()
        val unitRegex = "\\d+".toRegex()
        var title = "Add New Item"
        var storeName = "Choose a Store"

        if(!editItem.isEmpty()){
            initialName = editItem
            title = "Edit $editItem"
            initalPrice = viewModel.getPrice(editItem, store).toString()
            initialUnit = viewModel.getUnit(editItem, store)
            initialQuantity = viewModel.getQuantity(editItem, store).toString()
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
                placeholder = {Text(initialName)}
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
                    placeholder = {Text(initalPrice)}
                )
                Spacer(Modifier.width(10.dp))
                OutlinedTextField(
                    modifier = Modifier.width(120.dp),
                    value = unit.value,
                    onValueChange = { unit.value = it },
                    placeholder = {Text(initialUnit)}
                )
                Spacer(Modifier.width(10.dp))
                OutlinedTextField(
                    modifier = Modifier.width(120.dp),
                    value = quantity.value,
                    onValueChange = { quantity.value = it },
                    placeholder = {Text(initialQuantity)}
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
                onClick = {
                    if(editItem.isEmpty()){
                        if(!name.value.isEmpty()){
                            viewModel.addItem(name.value)
                            viewModel.setStore(name.value, store)
                            if (!price.value.isEmpty() && regex.matches(price.value)) {
                                viewModel.editPrice(name.value, store, price.value.toDouble())
                            }
                            if (!unit.value.isEmpty()) {
                                viewModel.editUnit(name.value, store, unit.value)
                            }
                            if (!quantity.value.isEmpty() && (regex.matches(quantity.value) || unitRegex.matches(quantity.value))) {
                                viewModel.editQuantity(
                                    name.value,
                                    store,
                                    quantity.value.toDouble()
                                )
                            }
                        }
                    }
                    else{
                        var currentItem = editItem
                        if(!name.value.isEmpty()){
                            viewModel.editName(editItem, name.value)
                            currentItem = name.value
                        }
                        if(!viewModel.itemAtStore(currentItem, store)){
                            viewModel.setStore(currentItem, store)
                        }
                        if(!price.value.isEmpty() && regex.matches(price.value)) {
                            viewModel.editPrice(currentItem, store, price.value.toDouble())
                        }
                        if(!unit.value.isEmpty()){
                            viewModel.editUnit(currentItem, store, unit.value)
                        }
                        if(!quantity.value.isEmpty() && (regex.matches(quantity.value) || unitRegex.matches(quantity.value))){
                            viewModel.editQuantity(currentItem, store, quantity.value.toDouble())
                        }
                    }

                }) {
                Text("Save")
            }
        }
    }
}