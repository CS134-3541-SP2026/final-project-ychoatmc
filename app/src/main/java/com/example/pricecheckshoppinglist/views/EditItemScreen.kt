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
import com.example.pricecheckshoppinglist.viewModels.ItemViewModel

@Composable
fun EditItemScreen(
    viewModel: ItemViewModel,
    modifier: Modifier,
    onBackClick: () -> Unit,
    onMainPageClick: () -> Unit,
    editItem: String
){
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val name = remember { mutableStateOf("") }
        val price = remember { mutableStateOf("") }
        val unit = remember { mutableStateOf("") }
        val quantity = remember { mutableStateOf("") }
        val regex = "\\d{1,2}\\.\\d{1,2}".toRegex()
        var title = "Add New Item"

        if(!editItem.isEmpty()){
            name.value = editItem
            title = "Edit $editItem"
            //price.value = viewModel.getCity(editStore)
            //tax.value = viewModel.getTax(editStore).toString()
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
//                    if(!city.value.isEmpty()){
  //                      viewModel.editCity(name.value, city.value)}
    //                if(!tax.value.isEmpty()){
      //                  if(regex.matches(tax.value)){
        //                    viewModel.editTax(name.value, tax.value.toDouble())}
          //          }
                }) {
                Text("Save")
            }
        }
    }
}