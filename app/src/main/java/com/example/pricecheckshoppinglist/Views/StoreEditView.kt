package com.example.pricecheckshoppinglist.Views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pricecheckshoppinglist.ViewModel.StoreViewModel
import java.io.DataInput
import java.text.DecimalFormatSymbols

@Composable
fun ChooseStoreEditView(
    viewModel: StoreViewModel = viewModel(),
    modifier: Modifier,
    onBackClick: () -> Unit,
    onEditPageClick: () -> Unit
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
        Text("Click on Store to Edit")
        Button(onClick = onEditPageClick) {
            Text("Add New Store")
        }
    }
}

@Composable
fun StoreEditView(
    viewModel: StoreViewModel = viewModel(),
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
        val tax = remember { mutableStateOf("") }

        Button(onClick = onBackClick) {
            Text("Back")
        }
        Text("Edit Store")
        Text("Name")
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = name.value,
            onValueChange = {name.value = it},
            label = {Text("Store Name")}
        )
        Text("Sales Tax")
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = tax.value,
            onValueChange = {tax.value = decimalFormatter(it)},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            label = {Text("Sales Tax")}
        )
        Button(onClick = onMainPageClick) {
            Text("Home Page")
        }
    }
}

