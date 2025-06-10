package com.irfangujjar.tmdb.features.main.search.domain.repos

import com.irfangujjar.tmdb.features.main.celebs.domain.models.CelebsListModel
import com.irfangujjar.tmdb.features.main.movies.domain.models.MoviesListModel
import com.irfangujjar.tmdb.features.main.search.domain.models.SearchDetailsModel
import com.irfangujjar.tmdb.features.main.search.domain.models.SearchModel
import com.irfangujjar.tmdb.features.main.tv_shows.domain.models.TvShowsListModel
import kotlinx.coroutines.flow.Flow

interface SearchRepo {
    suspend fun loadTrendingSearch()
    fun getTrendingSearchFlow(): Flow<SearchModel?>
    suspend fun loadSearchSuggestions(query: String): SearchModel
    suspend fun loadSearchDetails(query: String): SearchDetailsModel
    suspend fun searchMovies(query: String, pageNo: Int): MoviesListModel
    suspend fun searchTvShows(query: String, pageNo: Int): TvShowsListModel
    suspend fun searchCelebs(query: String, pageNo: Int): CelebsListModel
}