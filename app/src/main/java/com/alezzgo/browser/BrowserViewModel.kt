package com.alezzgo.browser

import androidx.lifecycle.ViewModel

class BrowserViewModel : ViewModel() {

    val itemsList = listOf(
        Item("https://static.tildacdn.com/tild6539-3334-4761-a666-366365646330/logo_cvl.png", "Item 1", "Item 1 subtitle"),
        Item("https://static.tildacdn.com/tild3134-3636-4137-b831-353732633330/USDx160.png", "Item 2", "Item 2 subtitle"),
        Item("https://static.tildacdn.com/tild6362-3766-4761-b466-373330323333/EURx160.png", "Item 3", "Item 3 subtitle")
    )

}

data class Item(val imageUrl: String, val title: String, val subtitle: String)