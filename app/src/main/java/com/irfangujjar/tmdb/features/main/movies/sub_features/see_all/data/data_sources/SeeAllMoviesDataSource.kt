package com.irfangujjar.tmdb.features.main.movies.sub_features.see_all.data.data_sources

import com.irfangujjar.tmdb.core.ui.util.MoviesCategories
import com.irfangujjar.tmdb.features.main.movies.data.data_sources.remote.api.MovieApi
import com.irfangujjar.tmdb.features.main.movies.domain.models.MoviesListModel

interface SeeAllMoviesDataSource {
    suspend fun loadMovies(
        categories: MoviesCategories,
        pageNo: Int,
        movieId: Int?
    ): MoviesListModel
}

class SeeAllMoviesDataSourceImpl(
    private val api: MovieApi
) : SeeAllMoviesDataSource {
    override suspend fun loadMovies(
        categories: MoviesCategories,
        pageNo: Int,
        movieId: Int?
    ): MoviesListModel =
        when (categories) {
            MoviesCategories.Popular -> api.popularMovies(pageNo = pageNo)
            MoviesCategories.InTheatres -> api.inTheatreMovies(pageNo = pageNo)
            MoviesCategories.Trending -> api.trendingMovies(pageNo = pageNo)
            MoviesCategories.TopRated -> api.topRatedMovies(pageNo = pageNo)
            MoviesCategories.Upcoming -> api.upcomingMovies(pageNo = pageNo)
            MoviesCategories.DetailsRecommended -> api.recommendedMovies(
                movieId = movieId!!,
                pageNo = pageNo
            )

            MoviesCategories.DetailsSimilar -> api.similarMovies(
                movieId = movieId!!,
                pageNo = pageNo
            )
        }
}