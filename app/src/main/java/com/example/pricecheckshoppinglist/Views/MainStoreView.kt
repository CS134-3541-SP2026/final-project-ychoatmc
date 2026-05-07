package com.example.pricecheckshoppinglist.Views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pricecheckshoppinglist.ui.theme.Store
import com.example.pricecheckshoppinglist.ui.theme.StoreViewModel

@Composable
fun MainStoreView(
    viewModel: StoreViewModel,
    modifier: Modifier
){
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Smarter Shopping App")
    }
}
