package com.example.pricecheckshoppinglist.ui.theme

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class StoreViewModel : ViewModel() {
    private val _stores = mutableListOf<Store>()
    val stores: MutableList<Store> = _stores

    fun addStore(store: Store){
        _stores.add(store)
    }
}