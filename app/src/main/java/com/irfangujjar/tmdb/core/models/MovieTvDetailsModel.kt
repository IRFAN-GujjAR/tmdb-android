package com.irfangujjar.tmdb.core.models

interface MovieTvDetailsModel {
    fun isCastCrewPresent(): Boolean

    fun isVideosPresent(): Boolean

    fun isRecommendationsPresent(): Boolean

    fun isSimilarPresent(): Boolean
}