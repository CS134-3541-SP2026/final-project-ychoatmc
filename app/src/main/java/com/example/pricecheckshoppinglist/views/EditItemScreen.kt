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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.pricecheckshoppinglist.viewModels.ItemViewModel

@Composable
fun EditItemScreen(
    viewModel: ItemViewModel,
    modifier: Modifier,
    onBackClick: () -> Unit,
    onMainPageClick: () -> Unit,
    decimalFormatter: (String) -> String
){
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        val name = remember { mutableStateOf("") }
        val price = remember { mutableStateOf("") }
        val unit = remember { mutableStateOf("") }
        val quantity = remember { mutableStateOf("") }

        Button(onClick = onBackClick) {
            Text("Back")
        }
        Text("Temporary Edit Item One")
        Text("Name")
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = name.value,
            onValueChange = {name.value = it},
            label = {Text("Item Name")}
        )
        Row {
            Text("Price", modifier.width(120.dp))
            Spacer(Modifier.width(10.dp))
            Text("Unit", modifier.width(120.dp))
            Spacer(Modifier.width(10.dp))
            Text("Quantity", modifier.width(120.dp))
        }
        Row{
            OutlinedTextField(
                modifier = Modifier.width(120.dp),
                value = price.value,
                onValueChange = { price.value = decimalFormatter(it) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                label = { Text("Price") }
            )
            Spacer(Modifier.width(10.dp))
            OutlinedTextField(
                modifier = Modifier.width(120.dp),
                value = unit.value,
                onValueChange = {unit.value = it},
                label = {Text("Unit")}
            )
            Spacer(Modifier.width(10.dp))
            OutlinedTextField(
                modifier = Modifier.width(120.dp),
                value = quantity.value,
                onValueChange = { quantity.value = decimalFormatter(it) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                label = { Text("Quantity") }
            )
        }

        Button(onClick = onMainPageClick) {
            Text("Home Page")
        }
    }
}