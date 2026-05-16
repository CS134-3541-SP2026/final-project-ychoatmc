package com.example.pricecheckshoppinglist.views

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.pricecheckshoppinglist.viewModels.ItemViewModel

@Composable
fun ListScreen(
    viewModel: ItemViewModel,
    modifier: Modifier,
    onBackClick: () -> Unit,
    onEditItemPageClick: (String, String) -> Unit,
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
        Column(modifier = Modifier.weight(.9f)) {
            if (viewModel.itemCount() != 0)
                viewModel.allItems.collectAsState().value.forEach {
                    Row(modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        var textColor by remember { mutableStateOf(Color.Black) }
                        var isChecked by remember { mutableStateOf(false) }
                        Checkbox(checked = isChecked, onCheckedChange = {
                            isChecked = it
                            if(isChecked) {
                                textColor = Color.LightGray
                            }
                            else{
                                textColor = Color.Black
                            }
                            })
                        TextButton({ onEditItemPageClick(it.name, storeName) }) {
                            Text(it.name, color = textColor)
                        }
                        Text(viewModel.getQuantity(it.name, storeName).toString() + " " +
                        viewModel.getUnit(it.name, storeName),
                            textAlign = TextAlign.Right,
                            color = textColor,
                            modifier = Modifier.width(200.dp))
                        Text(viewModel.getPrice(it.name, storeName).toString(),
                            textAlign = TextAlign.Right,
                            color = textColor,
                            modifier = Modifier.width(300.dp))
                    }
                }
        }
        Button(onClick = {onEditItemPageClick("", storeName)}) {
            Text("Add New Item")
        }
    }
}