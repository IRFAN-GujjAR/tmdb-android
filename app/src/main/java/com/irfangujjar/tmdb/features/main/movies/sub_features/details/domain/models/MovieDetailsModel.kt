package com.irfangujjar.tmdb.features.main.movies.sub_features.details.domain.models

import JsonKeyNames
import com.google.gson.annotations.SerializedName
import com.irfangujjar.tmdb.core.models.BackdropImagesModel
import com.irfangujjar.tmdb.core.models.CollectionModel
import com.irfangujjar.tmdb.core.models.CreditsModel
import com.irfangujjar.tmdb.core.models.GenreModel
import com.irfangujjar.tmdb.core.models.ProductionCompanyModel
import com.irfangujjar.tmdb.core.models.VideosModel
import com.irfangujjar.tmdb.core.ui.util.MediaType
import com.irfangujjar.tmdb.features.main.movies.domain.models.MoviesListModel

data class MovieDetailsModel(
    @SerializedName(JsonKeyNames.BACKDROP_PATH)
    val backdropPath: String?,
    @SerializedName(JsonKeyNames.POSTER_PATH)
    val posterPath: String?,
    val title: String,
    @SerializedName(JsonKeyNames.VOTE_AVERAGE)
    val voteAverage: Double?,
    @SerializedName(JsonKeyNames.VOTE_COUNT)
    val voteCount: Int?,
    val genres: List<GenreModel>,
    val overview: String?,
    @SerializedName(JsonKeyNames.COLLECTION)
    val collection: CollectionModel?,
    val credits: CreditsModel?,
    val images: BackdropImagesModel,
    val videos: VideosModel?,
    @SerializedName(JsonKeyNames.RELEASE_DATE)
    val releaseDate: String?,
    @SerializedName(JsonKeyNames.LANGUAGE)
    val language: String?,
    val budget: Long,
    val revenue: Long,
    @SerializedName(JsonKeyNames.PRODUCTION_COMPANIES)
    val productionCompanies: List<ProductionCompanyModel>,
    @SerializedName(JsonKeyNames.RECOMMENDATIONS)
    val recommendedMovies: MoviesListModel?,
    @SerializedName(JsonKeyNames.SIMILAR)
    val similarMovies: MoviesListModel?
) {

    fun isInformationPresent(): Boolean = releaseDate != null || language != null || budget != 0L
            || revenue != 0L || productionCompanies.isNotEmpty()

    companion object {
        fun dummyData(): MovieDetailsModel = MovieDetailsModel(
            backdropPath = "/tmU7GeKVybMWFButWEGl2M4GeiP.jpg",
            posterPath = "/3bhkrj58Vtu7enYsRolD1fZdja1.jpg",
            title = "The Godfather",
            voteAverage = 8.686,
            voteCount = 21522,
            genres = listOf(
                GenreModel(
                    name = "Drama"
                ),
                GenreModel(
                    name = "Crime"
                )
            ),
            overview = "Spanning the years 1945 to 1955, a chronicle of the fictional Italian-American Corleone crime family. When organized crime family patriarch, Vito Corleone barely survives an attempt on his life, his youngest son, Michael steps in to take care of the would-be killers, launching a campaign of bloody revenge.",
            collection = CollectionModel(
                id = 230,
                name = "The Godfather Collection",
                posterPath = "/zVb22YOMgljCEYbXlvCvEiN4VwT.jpg",
                backdropPath = "/mDMCET9Ens5ANvZAWRpluBsMAtS.jpg"
            ),
            credits = CreditsModel.dummyData(type = MediaType.Movie),
            images = BackdropImagesModel.dummyData(type = MediaType.Movie),
            videos = VideosModel.dummyData(type = MediaType.Movie),
            releaseDate = "1972-03-14",
            language = "en",
            budget = 6000000,
            revenue = 245066411,
            productionCompanies = listOf(
                ProductionCompanyModel(
                    name = "Paramount Pictures",
                ),
                ProductionCompanyModel(
                    name = "Alfran Productions",
                )
            ),
            recommendedMovies = MoviesListModel.dummyData(),
            similarMovies = MoviesListModel.dummyData()
        )
    }
}

