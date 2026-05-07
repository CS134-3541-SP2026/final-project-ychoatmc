package com.example.pricecheckshoppinglist.Model

import com.example.pricecheckshoppinglist.Model.ItemPrice

data class Item(
    var name: String,
    val prices: MutableList<ItemPrice>
)