package com.irfangujjar.tmdb.features.main.search.data.data_sources.remote

import com.irfangujjar.tmdb.features.main.celebs.domain.models.CelebsListModel
import com.irfangujjar.tmdb.features.main.movies.domain.models.MoviesListModel
import com.irfangujjar.tmdb.features.main.search.data.data_sources.remote.api.SearchApi
import com.irfangujjar.tmdb.features.main.search.data.data_sources.remote.dto.SearchResponse
import com.irfangujjar.tmdb.features.main.search.domain.models.SearchDetailsModel
import com.irfangujjar.tmdb.features.main.tv_shows.domain.models.TvShowsListModel

interface SearchRemoteDataSource {
    suspend fun loadTrendingSearch(): SearchResponse
    suspend fun loadSearchSuggestions(query: String): SearchResponse
    suspend fun loadSearchDetails(query: String): SearchDetailsModel
    suspend fun searchMovies(query: String, pageNo: Int): MoviesListModel
    suspend fun searchTvShows(query: String, pageNo: Int): TvShowsListModel
    suspend fun searchCelebs(query: String, pageNo: Int): CelebsListModel
}

class SearchRemoteDataSourceImpl(
    private val api: SearchApi
) : SearchRemoteDataSource {
    override suspend fun loadTrendingSearch(): SearchResponse = api.trendingSearch()

    override suspend fun loadSearchSuggestions(query: String): SearchResponse =
        api.multiSearch(query = query, pageNo = 1)

    override suspend fun loadSearchDetails(query: String): SearchDetailsModel {
        val moviesList = api.searchMovies(query = query, pageNo = 1)
        val tvShowsList = api.searchTvShows(query = query, pageNo = 1)
        val celebsList = api.searchCelebs(query = query, pageNo = 1)
        return SearchDetailsModel(
            moviesList = moviesList,
            tvShowsList = tvShowsList,
            celebsList = celebsList
        )
    }

    override suspend fun searchMovies(query: String, pageNo: Int): MoviesListModel =
        api.searchMovies(query = query, pageNo = pageNo)

    override suspend fun searchTvShows(query: String, pageNo: Int): TvShowsListModel =
        api.searchTvShows(query = query, pageNo = pageNo)

    override suspend fun searchCelebs(query: String, pageNo: Int): CelebsListModel =
        api.searchCelebs(query = query, pageNo = pageNo)

}