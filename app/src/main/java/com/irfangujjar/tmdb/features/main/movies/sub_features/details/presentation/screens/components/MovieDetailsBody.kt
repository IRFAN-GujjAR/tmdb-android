package com.irfangujjar.tmdb.features.main.movies.sub_features.details.presentation.screens.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.irfangujjar.tmdb.core.models.CreditsModel
import com.irfangujjar.tmdb.core.navigation.nav_keys.HomeNavKey
import com.irfangujjar.tmdb.core.ui.ScreenPadding
import com.irfangujjar.tmdb.core.ui.components.CustomDivider
import com.irfangujjar.tmdb.core.ui.components.DividerBottomPadding
import com.irfangujjar.tmdb.core.ui.components.DividerTopPadding
import com.irfangujjar.tmdb.core.ui.components.details.DetailsBackdropComp
import com.irfangujjar.tmdb.core.ui.components.details.DetailsCastCrewItemsComp
import com.irfangujjar.tmdb.core.ui.components.details.YoutubeVideosComp
import com.irfangujjar.tmdb.core.ui.components.list.MediaItemsHorizontalList
import com.irfangujjar.tmdb.core.ui.components.list.values.MediaItemsHorizontalListConfigValues
import com.irfangujjar.tmdb.core.ui.components.list.values.MediaItemsHorizontalListValues
import com.irfangujjar.tmdb.core.ui.theme.TMDbTheme
import com.irfangujjar.tmdb.core.ui.util.MediaType
import com.irfangujjar.tmdb.core.ui.util.MoviesCategory
import com.irfangujjar.tmdb.features.main.movies.sub_features.details.domain.models.MovieDetailsModel


@Composable
fun MovieDetailsBody(
    preview: Boolean,
    outerPadding: PaddingValues,
    innerPadding: PaddingValues,
    movieDetails: MovieDetailsModel,
    onNavigateToSeeAllMovies: (MoviesCategory) -> Unit,
    onNavigateToMovieDetails: (HomeNavKey.MovieDetailsNavKey) -> Unit,
    onCastCrewSeeAllClick: (CreditsModel) -> Unit
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .padding(
                ScreenPadding.getPadding(
                    outerPaddingValues = outerPadding,
                    innerPaddingValues = innerPadding,
                    includeStartPadding = false,
                    includeEndPadding = false,
                    includeTopPadding = false
                )
            )
    ) {
        DetailsBackdropComp(
            preview = preview,
            type = MediaType.Movie,
            backdropDetailsPath = movieDetails.backdropPath,
            backdropPath = movieDetails.backdropPath,
            posterDetailsPath = movieDetails.posterPath,
            posterPath = movieDetails.posterPath,
            title = movieDetails.title,
            voteAverage = movieDetails.voteAverage,
            voteCount = movieDetails.voteCount,
            genres = movieDetails.genres,
            overview = movieDetails.overview,
        )
        Spacer(modifier = Modifier.height(12.dp))
        if (movieDetails.collection != null)
            CollectionComp(
                preview = preview,
                collection = movieDetails.collection,
                genres = movieDetails.genres,
                posterPath = movieDetails.posterPath,
                backdropPath = movieDetails.backdropPath
            )
        if (movieDetails.isCastCrewPresent())
            DetailsCastCrewItemsComp(
                preview = preview,
                credits = movieDetails.credits!!,
                onSeeAllClicked = {
                    onCastCrewSeeAllClick(movieDetails.credits)
                }
            )
        if (movieDetails.isVideosPresent())
            YoutubeVideosComp(
                preview = preview,
                videos = movieDetails.videos!!
            )
        if (movieDetails.isInformationPresent())
            MovieDetailsInformationComp(
                releaseDate = movieDetails.releaseDate,
                language = movieDetails.language,
                budget = movieDetails.budget,
                revenue = movieDetails.revenue,
                productionCompanies = movieDetails.productionCompanies
            )
        if (movieDetails.isRecommendationsPresent()) {
            CustomDivider(
                topPadding = DividerTopPadding.OneAndHalf,
                bottomPadding = DividerBottomPadding.Half
            )
            MediaItemsHorizontalList(
                preview = preview,
                values = MediaItemsHorizontalListValues.fromMovies(
                    movies = movieDetails.recommendedMovies!!.movies,
                    isLandscape = false,
                    configValues = MediaItemsHorizontalListConfigValues.movieConfig(
                        category = MoviesCategory.DetailsRecommended
                    )
                ),
                title = MoviesCategory.DetailsRecommended.title,
                onSeeAllClick = { onNavigateToSeeAllMovies(MoviesCategory.DetailsRecommended) },
                onItemTapped = { id, title, posterPath, backdropPath ->
                    onNavigateToMovieDetails(
                        HomeNavKey.MovieDetailsNavKey(
                            movieId = id,
                            title = title,
                            posterPath = posterPath,
                            backdropPath = backdropPath
                        )
                    )
                }
            )
        }
        if (movieDetails.isSimilarPresent()) {
            CustomDivider(
                topPadding = DividerTopPadding.OneAndHalf,
                bottomPadding = DividerBottomPadding.Half
            )
            MediaItemsHorizontalList(
                preview = preview,
                values = MediaItemsHorizontalListValues.fromMovies(
                    movies = movieDetails.similarMovies!!.movies,
                    isLandscape = false,
                    configValues = MediaItemsHorizontalListConfigValues.movieConfig(
                        category = MoviesCategory.DetailsSimilar
                    )
                ),
                title = MoviesCategory.DetailsSimilar.title,
                onSeeAllClick = { onNavigateToSeeAllMovies(MoviesCategory.DetailsSimilar) },
                onItemTapped = { id, title, posterPath, backdropPath ->
                    onNavigateToMovieDetails(
                        HomeNavKey.MovieDetailsNavKey(
                            movieId = id,
                            title = title,
                            posterPath = posterPath,
                            backdropPath = backdropPath
                        )
                    )
                }
            )
        }
    }
}

@Preview
@Composable
private fun MovieDetailsBodyPreview() {
    TMDbTheme {
        Surface {
            MovieDetailsBody(
                preview = true,
                outerPadding = PaddingValues(0.dp),
                innerPadding = PaddingValues(0.dp),
                movieDetails = MovieDetailsModel.dummyData(),
                onNavigateToSeeAllMovies = {

                },
                onNavigateToMovieDetails = {

                },
                onCastCrewSeeAllClick = {}
            )
        }
    }

}