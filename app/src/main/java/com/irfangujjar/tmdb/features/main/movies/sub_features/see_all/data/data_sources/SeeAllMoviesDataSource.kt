package com.irfangujjar.tmdb.features.main.movies.sub_features.see_all.data.data_sources

import com.irfangujjar.tmdb.core.ui.util.MoviesCategory
import com.irfangujjar.tmdb.features.main.movies.data.data_sources.remote.api.MovieApi
import com.irfangujjar.tmdb.features.main.movies.domain.models.MoviesListModel

interface SeeAllMoviesDataSource {
    suspend fun loadMovies(
        categories: MoviesCategory,
        pageNo: Int,
        movieId: Int?
    ): MoviesListModel
}

class SeeAllMoviesDataSourceImpl(
    private val api: MovieApi
) : SeeAllMoviesDataSource {
    override suspend fun loadMovies(
        categories: MoviesCategory,
        pageNo: Int,
        movieId: Int?
    ): MoviesListModel =
        when (categories) {
            MoviesCategory.Popular -> api.popularMovies(pageNo = pageNo)
            MoviesCategory.InTheatres -> api.inTheatreMovies(pageNo = pageNo)
            MoviesCategory.Trending -> api.trendingMovies(pageNo = pageNo)
            MoviesCategory.TopRated -> api.topRatedMovies(pageNo = pageNo)
            MoviesCategory.Upcoming -> api.upcomingMovies(pageNo = pageNo)
            MoviesCategory.DetailsRecommended -> api.recommendedMovies(
                movieId = movieId!!,
                pageNo = pageNo
            )

            MoviesCategory.DetailsSimilar -> api.similarMovies(
                movieId = movieId!!,
                pageNo = pageNo
            )
        }
}