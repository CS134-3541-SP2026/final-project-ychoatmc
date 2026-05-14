package com.example.pricecheckshoppinglist.models

data class ItemPrice(
    val store: Store,
    var price: Double,
    var unit: String,
    var quantity: Double,
//    val subtotal: Double,
//    val taxed: Boolean,
//    val total: Double
)