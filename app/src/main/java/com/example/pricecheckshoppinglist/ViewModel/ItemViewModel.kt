package com.example.pricecheckshoppinglist.ViewModel

import androidx.lifecycle.ViewModel
import com.example.pricecheckshoppinglist.Model.Item
import com.example.pricecheckshoppinglist.Model.Store
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ItemViewModel : ViewModel() {
    private val _allItems = MutableStateFlow<List<Item>>(emptyList())
    val allItems: StateFlow<List<Item>> = _allItems

    fun addItem(item: Item){
        _allItems.value = _allItems.value + item
    }

    fun deleteStore(item: Item, store: Store){
        item.prices.forEach {
            if (it.store.name == store.name)
                item.prices.removeAt(item.prices.indexOf(it))
        }
    }

    fun deleteItem(item: Item){
        val tempList = _allItems.value.toMutableList()
        tempList.forEach {
            if(it.name == item.name)
                tempList.removeAt(tempList.indexOf(it))
        }
        _allItems.value = tempList
    }

    fun editName(item: Item, name: String){
        _allItems.value.forEach {
            if(it.name == item.name)
                it.name = name
        }
    }

    fun editPrice(item: Item, store: Store, price: Double){
        _allItems.value.forEach {
            if(it.name == item.name){
                it.prices.forEach {
                    if(it.store.name == store.name)
                        it.price = price
                }
            }
        }
    }

    fun editPrice(item: Item, store: Store, unit: String){
        _allItems.value.forEach {
            if(it.name == item.name){
                it.prices.forEach {
                    if(it.store.name == store.name)
                        it.unit = unit
                }
            }
        }
    }

    fun editQuantity(item: Item, store: Store, quantity: Double){
        _allItems.value.forEach {
            if(it.name == item.name){
                it.prices.forEach {
                    if(it.store.name == store.name)
                        it.quantity = quantity
                }
            }
        }
    }
}