package com.example.pricecheckshoppinglist.viewModels

import androidx.lifecycle.ViewModel
import com.example.pricecheckshoppinglist.models.Item
import com.example.pricecheckshoppinglist.models.Store
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ItemViewModel : ViewModel() {
    private val _allItems = MutableStateFlow<List<Item>>(emptyList())
    val allItems: StateFlow<List<Item>> = _allItems

    fun addItem(itemName: String){
        _allItems.value += Item(itemName)
    }
/**Edit Later
    fun deleteStore(item: Item, store: Store){
        item.prices.value.forEach {
            if (it.store.name == store.name)
               // item.prices.removeAt(item.prices.indexOf(it))
        }
    }*/

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
                it.prices.value.forEach {
                    if(it.store.name == store.name)
                        it.price = price
                }
            }
        }
    }

    /** Update Later */
    fun editUnit(item: Item, store: Store, unit: String){
        _allItems.value.forEach {
            if(it.name == item.name){
                it.prices.value.forEach {
                    if(it.store.name == store.name)
                        it.unit = unit
                }
            }
        }
    }

    /** Update Later */
    fun editQuantity(item: Item, store: Store, quantity: Double){
        _allItems.value.forEach {
            if(it.name == item.name){
                it.prices.value.forEach {
                    if(it.store.name == store.name)
                        it.quantity = quantity
                }
            }
        }
    }

    fun itemCount() : Int {
        return _allItems.value.count()
    }
}