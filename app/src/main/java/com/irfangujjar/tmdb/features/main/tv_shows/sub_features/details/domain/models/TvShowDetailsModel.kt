package com.irfangujjar.tmdb.features.main.tv_shows.sub_features.details.domain.models

import JsonKeyNames
import com.google.gson.annotations.SerializedName
import com.irfangujjar.tmdb.core.models.BackdropImagesModel
import com.irfangujjar.tmdb.core.models.CreatorModel
import com.irfangujjar.tmdb.core.models.CreditsModel
import com.irfangujjar.tmdb.core.models.GenreModel
import com.irfangujjar.tmdb.core.models.NetworkModel
import com.irfangujjar.tmdb.core.models.ProductionCompanyModel
import com.irfangujjar.tmdb.core.models.SeasonModel
import com.irfangujjar.tmdb.core.models.VideosModel
import com.irfangujjar.tmdb.core.ui.util.MediaType
import com.irfangujjar.tmdb.features.main.tv_shows.domain.models.TvShowsListModel

data class TvShowDetailsModel(
    @SerializedName(JsonKeyNames.BACKDROP_PATH)
    val backdropPath: String?,
    @SerializedName(JsonKeyNames.POSTER_PATH)
    val posterPath: String?,
    val name: String,
    @SerializedName(JsonKeyNames.VOTE_AVERAGE)
    val voteAverage: Double?,
    @SerializedName(JsonKeyNames.VOTE_COUNT)
    val voteCount: Int?,
    val genres: List<GenreModel>,
    val overview: String?,
    val seasons: List<SeasonModel>,
    val credits: CreditsModel?,
    val images: BackdropImagesModel,
    val videos: VideosModel,
    @SerializedName(JsonKeyNames.CREATED_BY)
    val createdBy: List<CreatorModel>,
    @SerializedName(JsonKeyNames.FIRST_AIR_DATE)
    val firstAirDate: String?,
    @SerializedName(JsonKeyNames.LANGUAGE)
    val language: String?,
    @SerializedName(JsonKeyNames.COUNTRY_ORIGIN)
    val countryOrigin: List<String>,
    val networks: List<NetworkModel>?,
    @SerializedName(JsonKeyNames.PRODUCTION_COMPANIES)
    val productionCompanies: List<ProductionCompanyModel>,
    @SerializedName(JsonKeyNames.RECOMMENDATIONS)
    val recommendedTvShows: TvShowsListModel?,
    @SerializedName(JsonKeyNames.SIMILAR)
    val similarTvShows: TvShowsListModel?
) {
    companion object {
        fun dummyData(): TvShowDetailsModel =
            TvShowDetailsModel(
                backdropPath = "/or7wKwv1IT6LEOktt395O5qi7e.jpg",
                posterPath = "/vUUqzWa2LnHIVqkaKVlVGkVcZIW.jpg",
                name = "Peaky Blinders",
                voteAverage = 8.5,
                voteCount = 10393,
                genres = listOf(
                    GenreModel(
                        name = "Drama"
                    ),
                    GenreModel(
                        name = "Crime"
                    )
                ),
                overview = "A gangster family epic set in 1919 Birmingham, England and centered on a gang who sew razor blades in the peaks of their caps, and their fierce boss Tommy Shelby, who means to move up in the world.",
                seasons = SeasonModel.dummyListData(),
                credits = CreditsModel.dummyData(type = MediaType.TvShow),
                images = BackdropImagesModel.dummyData(type = MediaType.TvShow),
                videos = VideosModel.dummyData(type = MediaType.TvShow),
                createdBy = listOf(
                    CreatorModel(
                        id = 23227,
                        name = "Steven Knight"

                    )
                ),
                firstAirDate = "2013-09-12",
                language = "en",
                countryOrigin = listOf(
                    "GB"
                ),
                networks = listOf(
                    NetworkModel(
                        name = "BBC One"
                    ),
                    NetworkModel(
                        name = "BBC Two"
                    )
                ),
                productionCompanies = listOf(
                    ProductionCompanyModel(
                        name = "Tiger Aspect"
                    ),
                    ProductionCompanyModel(
                        name = "Caryn Mandabach Productions"
                    ),
                    ProductionCompanyModel(
                        name = "Screen Yorkshire"
                    )
                ),
                recommendedTvShows = TvShowsListModel.dummyData(),
                similarTvShows = TvShowsListModel.dummyData()
            )
    }
}
