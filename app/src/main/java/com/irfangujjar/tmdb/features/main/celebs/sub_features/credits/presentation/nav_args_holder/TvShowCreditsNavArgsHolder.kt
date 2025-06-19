package com.irfangujjar.tmdb.features.main.celebs.sub_features.credits.presentation.nav_args_holder

import com.irfangujjar.tmdb.core.navigation.args_holder.NavArgsHolder
import com.irfangujjar.tmdb.features.main.celebs.sub_features.details.domain.models.TvShowCreditsModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TvShowCreditsNavArgsHolder @Inject constructor() : NavArgsHolder<TvShowCreditsModel>()