package com.example.pricecheckshoppinglist.views

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
import com.example.pricecheckshoppinglist.viewModels.StoreViewModel
import org.intellij.lang.annotations.Pattern

@Composable
fun EditStoreScreen(
    viewModel: StoreViewModel,
    modifier: Modifier,
    onBackClick: () -> Unit,
    onMainPageClick: () -> Unit,
    editStore: String
){
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val name = remember { mutableStateOf("") }
        val city = remember { mutableStateOf("") }
        val tax = remember { mutableStateOf("") }
        var title = "Add Store"
        var storeAdded = false
        val regex = "\\d{1,2}\\.\\d{1,2}".toRegex()

        if(!editStore.isEmpty()){
            name.value = editStore
            title = "Edit $editStore"
            city.value = viewModel.getCity(editStore)
            tax.value = viewModel.getTax(editStore).toString()
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
                label = { Text("Store Name") },
            )
            Text("City")
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth()
                    .padding(bottom = 15.dp),
                value = city.value,
                onValueChange = { city.value = it },
                label = { Text("City") }
            )
            Text("Sales Tax")
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth()
                    .padding(bottom = 15.dp),
                value = tax.value,
                onValueChange = { tax.value = it },
                label = { Text("Sales Tax") }
            )
        }
        Row(modifier = modifier) {
            Button(onClick = onMainPageClick) {
                Text("Home Page")
            }
            Spacer(modifier = Modifier.width(20.dp))
            Button(
                enabled = name.value.isNotBlank(),
                onClick = {
                    viewModel.addStore(name.value)
                     if(!city.value.isEmpty()){
                       viewModel.editCity(name.value, city.value)}
                    if(!tax.value.isEmpty()){
                        if(regex.matches(tax.value)){
                            viewModel.editTax(name.value, tax.value.toDouble())}
                    }
                }) {
                Text("Save")
            }
        }/**
        Text("Store Count: ${viewModel.storeCount()}")
        Text("Store name: ${name.value}")
        Text("City: ${city.value}")
        Text("Tax: ${tax.value}")
        Text("Store added: $storeAdded")*/
    }
}