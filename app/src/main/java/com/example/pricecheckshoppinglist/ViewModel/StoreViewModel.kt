package com.example.pricecheckshoppinglist.ViewModel

import androidx.lifecycle.ViewModel
import com.example.pricecheckshoppinglist.Model.Store

class StoreViewModel : ViewModel() {
    private val _stores = mutableListOf<Store>()
    val stores: MutableList<Store> = _stores

    fun addStore(store: Store){
        _stores.add(store)
    }
}