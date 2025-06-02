package com.irfangujjar.tmdb.features.main.movies.data.data_sources.remote

import com.irfangujjar.tmdb.features.main.movies.data.data_sources.remote.api.MovieApi
import com.irfangujjar.tmdb.features.main.movies.domain.models.MoviesModel

interface MoviesRemoteDataSource {
    suspend fun loadMovies(): MoviesModel
}

class MoviesRemoteDataSourceImpl(
    private val api: MovieApi
) : MoviesRemoteDataSource {
    override suspend fun loadMovies(): MoviesModel {
        val popular = api.popularMovies(pageNo = 1)
        val inTheatres = api.inTheatreMovies(pageNo = 1)
        val trending = api.trendingMovies(pageNo = 1)
        val topRated = api.topRatedMovies(pageNo = 1)
        val upcoming = api.upcomingMovies(pageNo = 1)
        return MoviesModel(
            popular = popular,
            inTheatres = inTheatres,
            trending = trending,
            topRated = topRated,
            upcoming = upcoming
        )
    }

}