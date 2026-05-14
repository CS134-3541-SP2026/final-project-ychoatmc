package com.example.pricecheckshoppinglist.models

data class Item(
    var name: String,
    val prices: MutableList<ItemPrice>
)