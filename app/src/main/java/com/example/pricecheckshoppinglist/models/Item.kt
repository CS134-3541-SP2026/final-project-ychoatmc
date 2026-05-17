package com.example.pricecheckshoppinglist.models

import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.serialization.Serializable

@Serializable
data class Item(
    var id: Int,
    var name: String,
    val prices: MutableList<ItemPrice> = mutableListOf<ItemPrice>()
)