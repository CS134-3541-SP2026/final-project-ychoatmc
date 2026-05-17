package com.example.pricecheckshoppinglist.models

import kotlinx.serialization.Serializable

@Serializable
data class Store(
    var id: Int,
    var name: String,
    var city: String = "",
    var tax: Double = 0.0
)