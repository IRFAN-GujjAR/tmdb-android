package com.irfangujjar.tmdb.features.main.tmdb.sub_features.media_list.data.data_sources.api

enum class TMDBMediaListCategory(val categoryValue: String, val categoryName: String) {
    Favorite("favorite", "Favorites"),
    Rated("rated", "Rated"),
    Watchlist("watchlist", "Watchlist")
}