package com.example.pricecheckshoppinglist.views

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.pricecheckshoppinglist.viewModels.ItemViewModel
import com.example.pricecheckshoppinglist.viewModels.StoreViewModel

@Composable
fun ListScreen(
    viewModel: ItemViewModel,
    storeViewModel: StoreViewModel,
    modifier: Modifier,
    onBackClick: () -> Unit,
    onEditItemPageClick: (String, String) -> Unit,
    storeName: String
){
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(14.dp),
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
        Column(modifier = Modifier.weight(.5f)) {
            if (viewModel.itemCount() != 0) {
                val otherStores = mutableListOf<String>()
                if (LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    Row(
                        modifier = Modifier.fillMaxWidth()
                            .height(32.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("", modifier = Modifier.width(415.dp))
                        storeViewModel.stores.collectAsState().value.forEach {
                            if (it.name != storeName) {
                                Text(it.name, modifier = Modifier.width(70.dp),
                                    textAlign = TextAlign.Right)
                                otherStores += it.name
                            }
                        }
                    }
                }
                viewModel.allItems.collectAsState().value.forEach {
                    val item = it.name
                    Row(
                        modifier = Modifier.fillMaxWidth()
                            .height(32.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        var textColor by remember { mutableStateOf(Color.Black) }
                        var isChecked by remember { mutableStateOf(false) }
                        val quantity = viewModel.getQuantity(it.name, storeName)
                        if (quantity == 0.0) {
                            textColor = Color.LightGray
                            isChecked = true
                        }
                        Checkbox(checked = isChecked, onCheckedChange = {
                            isChecked = it
                            textColor = if (isChecked) {
                                Color.LightGray
                            } else {
                                Color.Black
                            }
                        })
                        TextButton({ onEditItemPageClick(it.name, storeName) }) {
                            Text(
                                it.name, color = textColor,
                                modifier = Modifier.width(210.dp)
                            )
                        }
                        Text(
                            quantity.toString() + " " +
                                    viewModel.getUnit(it.name, storeName),
                            textAlign = TextAlign.Right,
                            color = textColor,
                            modifier = Modifier.width(50.dp)
                        )
                        Text(
                            viewModel.getPrice(it.name, storeName).toString(),
                            textAlign = TextAlign.Right,
                            color = textColor,
                            modifier = Modifier.width(55.dp)
                        )
                        if (LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                            otherStores.forEach {
                                Spacer(modifier = Modifier.width(25.dp))
                                Text(
                                    viewModel.getPrice(item, it).toString(),
                                    textAlign = TextAlign.Right,
                                    color = textColor,
                                    modifier = Modifier.width(55.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
        Button(onClick = {onEditItemPageClick("", storeName)}) {
            Text("Add New Item")
        }
    }
}