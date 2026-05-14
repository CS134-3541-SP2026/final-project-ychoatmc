package com.example.pricecheckshoppinglist.models

data class Store(
    var name: String,
    var city: String = "",
    var tax: Double = 0.0
)