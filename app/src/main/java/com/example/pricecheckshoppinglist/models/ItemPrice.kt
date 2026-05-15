package com.example.pricecheckshoppinglist.models

data class ItemPrice(
    val store: String,
    var price: Double = 0.0,
    var unit: String = "each",
    var quantity: Double = 0.0
//    val subtotal: Double,
//    val taxed: Boolean,
//    val total: Double
)