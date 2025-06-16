package com.irfangujjar.tmdb.features.main.celebs.sub_features.details.domain.models

import JsonKeyNames
import com.google.gson.annotations.SerializedName
import com.irfangujjar.tmdb.features.main.movies.domain.models.MovieModel
import com.irfangujjar.tmdb.features.main.tv_shows.domain.models.TvShowModel

data class CelebDetailsModel(
    val name: String,
    @SerializedName(JsonKeyNames.KNOWN_FOR_DEPT)
    val department: String?,
    @SerializedName(JsonKeyNames.BIRTHDAY)
    val dateOfBirth: String?,
    @SerializedName(JsonKeyNames.DEATH_DAY)
    val dateOfDeath: String?,
    val biography: String?,
    @SerializedName(JsonKeyNames.BIRTH_PLACE)
    val placeOfBirth: String?,
    @SerializedName(JsonKeyNames.PROFILE_PATH)
    val profilePath: String?,
    @SerializedName(JsonKeyNames.MOVIE_CREDITS)
    val movieCredits: MovieCreditsModel?,
    @SerializedName(JsonKeyNames.TV_CREDITS)
    val tvCredits: TvShowCreditsModel?
) {
    fun isMovieCreditsPresent(): Boolean =
        movieCredits != null && (movieCredits.cast.isNotEmpty() || movieCredits.crew.isNotEmpty())

    fun movieCreditsMovies(): List<MovieModel> =
        if (movieCredits!!.cast.isNotEmpty()) movieCredits.cast else movieCredits.crew

    fun isTvCreditsPresent(): Boolean =
        tvCredits != null && (tvCredits.cast.isNotEmpty() || tvCredits.crew.isNotEmpty())

    fun tvCreditsTvShows(): List<TvShowModel> =
        if (tvCredits!!.cast.isNotEmpty()) tvCredits.cast else tvCredits.crew

    companion object {
        fun dummyData(): CelebDetailsModel =
            CelebDetailsModel(
                name = "Christian Bale",
                department = "Acting",
                dateOfBirth = "1974-01-30",
                dateOfDeath = null,
                biography = "Christian Charles Philip Bale (born 30 January 1974) is an English actor. Known for his versatility and physical transformations for his roles, he has been a leading man in films of several genres. He has received various accolades, including an Academy Award and two Golden Globe Awards. Forbes magazine ranked him as one of the highest-paid actors in 2014.\n\nBorn in Wales to English parents, Bale had his breakthrough role at age 13 in Steven Spielberg's 1987 war film Empire of the Sun. After more than a decade of performing in leading and supporting roles in films, he gained wider recognition for his portrayals of serial killer Patrick Bateman in the black comedy American Psycho (2000) and the titular role in the psychological thriller The Machinist (2004). In 2005, he played superhero Batman in Batman Begins and again in The Dark Knight (2008) and The Dark Knight Rises (2012), garnering acclaim for his performance in the trilogy, which is one of the highest-grossing film franchises.\n\nBale continued in starring roles in a range of films outside his work as Batman, including the period drama The Prestige (2006), the action film Terminator Salvation (2009), the crime drama Public Enemies (2009), the epic film Exodus: Gods and Kings (2014) and the superhero film Thor: Love and Thunder (2022). For his portrayal of boxer Dicky Eklund in the 2010 biographical film The Fighter, he won an Academy Award and a Golden Globe Award. Further Academy Award and Golden Globe Award nominations came for his work in the black comedy American Hustle (2013) and the biographical dramedies The Big Short (2015) and Vice (2018). His performances as politician Dick Cheney in Vice and race car driver Ken Miles in the sports drama Ford v Ferrari (2019) earned him a second win and a fifth nomination respectively at the Golden Globe Awards.",
                placeOfBirth = "Haverfordwest, Pembrokeshire, Wales, UK",
                profilePath = "/7Pxez9J8fuPd2Mn9kex13YALrCQ.jpg",
                movieCredits = MovieCreditsModel.dummyDate(),
                tvCredits = TvShowCreditsModel.dummyDate()
            )
    }
}