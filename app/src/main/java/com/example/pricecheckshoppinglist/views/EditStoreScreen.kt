package com.example.pricecheckshoppinglist.views

import android.content.Context
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.pricecheckshoppinglist.viewModels.StoreViewModel
import kotlin.coroutines.CoroutineContext

@Composable
fun EditStoreScreen(
    viewModel: StoreViewModel,
    modifier: Modifier,
    onBackClick: () -> Unit,
    onMainPageClick: () -> Unit,
    editStore: String,
    onSaveClick: (String) -> Unit
){
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var name = remember { mutableStateOf("") }
        var city = remember { mutableStateOf("") }
        var tax = remember { mutableStateOf("") }
        var initialName = "Store Name"
        var initialCity = "City"
        var initialTax = "0.0"
        var title = "Add Store"
        var storeAdded = false
        val regex = "\\d{1,2}\\.\\d{1,2}".toRegex()

        if(!editStore.isEmpty()){
            initialName = editStore
            title = "Edit $editStore"
            initialCity = viewModel.getCity(editStore)
            initialTax = viewModel.getTax(editStore).toString()
        }

        Button(onClick = onBackClick,
            modifier = modifier.align(Alignment.Start)) {
            Text("Back")
        }
        Text(title,
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center)
        Column(modifier = Modifier.weight(.9f),
            horizontalAlignment = Alignment.Start) {
            Text("Name")
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth()
                    .padding(bottom = 15.dp),
                value = name.value,
                onValueChange = { name.value = it },
                placeholder = {Text(initialName)}
            )
            Text("City")
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth()
                    .padding(bottom = 15.dp),
                value = city.value,
                onValueChange = { city.value = it },
                placeholder = {Text(initialCity)}
            )
            Text("Sales Tax")
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth()
                    .padding(bottom = 15.dp),
                value = tax.value,
                onValueChange = { tax.value = it },
                placeholder = {Text(initialTax)}
            )
        }
        Row(modifier = modifier) {
            Button(onClick = onMainPageClick) {
                Text("Home Page")
            }
            Spacer(modifier = Modifier.width(20.dp))
            Button(
//                enabled = name.value.isNotBlank(),
                onClick = {
                    var currentStore = ""
                    if(!editStore.isEmpty() || !name.value.isEmpty()){
                        //add new store
                        if(editStore.isEmpty()){
                            currentStore = name.value
                            viewModel.addStore(currentStore)
                        }
                        //or not
                        else{
                            currentStore = editStore
                        }
                        //update city & tax if applicable
                        if (!city.value.isEmpty()) {
                            viewModel.editCity(currentStore, city.value)
                        }
                        if (!tax.value.isEmpty()) {
                            if (regex.matches(tax.value)) {
                                viewModel.editTax(currentStore, tax.value.toDouble())
                            }
                        }
                        //edit name of existing store if applicable
                        if(!editStore.isEmpty() && !name.value.isEmpty()){
                            currentStore = name.value
                            viewModel.editName(editStore, name.value)
                        }
                        onSaveClick(currentStore)
                    }
                }) {
                Text("Save")
            }
        }
        Text("Store Name: $editStore")
        Text("Store Id: ${viewModel.getId(editStore)}")
        /**
        Text("Store Count: ${viewModel.storeCount()}")
        Text("Store name: ${name.value}")
        Text("City: ${city.value}")
        Text("Tax: ${tax.value}")
        Text("Store added: $storeAdded")*/
    }
}