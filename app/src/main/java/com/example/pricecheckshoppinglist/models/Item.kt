package com.example.pricecheckshoppinglist.models

import kotlinx.serialization.Serializable

@Serializable
data class Item(
    var id: Int,
    var name: String,
    val prices: MutableList<ItemPrice> = mutableListOf<ItemPrice>()
)