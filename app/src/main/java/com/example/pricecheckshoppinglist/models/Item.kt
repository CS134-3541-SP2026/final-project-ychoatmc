package com.example.pricecheckshoppinglist.models

import kotlinx.coroutines.flow.MutableStateFlow

data class Item(
    var name: String,
    val prices: MutableStateFlow<List<ItemPrice>> = MutableStateFlow<List<ItemPrice>>(emptyList())
)