package com.alezzgo.browser

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class BrowserViewModel : ViewModel() {

    val itemsLists = listOf(
        RecommendationLink(
            "https://static.tildacdn.com/tild6539-3334-4761-a666-366365646330/logo_cvl.png",
            "RecommendationLink 1",
            "RecommendationLink 1 subtitle"
        ),
        RecommendationLink(
            "https://static.tildacdn.com/tild3134-3636-4137-b831-353732633330/USDx160.png",
            "RecommendationLink 2",
            "RecommendationLink 2 subtitle"
        ),
        RecommendationLink(
            "https://static.tildacdn.com/tild6362-3766-4761-b466-373330323333/EURx160.png",
            "RecommendationLink 3",
            "RecommendationLink 3 subtitle"
        ),
        RecommendationLink(
            "https://static.tildacdn.com/tild6662-6662-4536-b730-313336623939/197408.png",
            "RecommendationLink 3",
            "RecommendationLink 3 subtitle"
        ),
        RecommendationLink(
            "https://static.tildacdn.com/tild3762-3135-4465-b166-623666623166/GBPx160.png",
            "RecommendationLink 3",
            "RecommendationLink 3 subtitle"
        ),
        RecommendationLink(
            "https://static.tildacdn.com/tild6236-3131-4931-a538-663133666136/kazx160.png",
            "RecommendationLink 3",
            "RecommendationLink 3 subtitle"
        ),
    )
    private val _state =
        MutableStateFlow(BrowserScreenState(itemsLists))
    val state = _state.asStateFlow()

    fun onEvent(event: BrowserEvent) {
        when (event) {
            is BrowserEvent.OnSearch -> { /* todo */ }
            is BrowserEvent.OnLinkClick -> { /* todo */ }
        }
    }


}

sealed interface BrowserEvent {
    data class OnSearch(val searchText: String) : BrowserEvent
    data class OnLinkClick(val link: RecommendationLink) : BrowserEvent

}
data class BrowserScreenState(val links: List<RecommendationLink>)
data class RecommendationLink(val imageUrl: String, val title: String, val subtitle: String)