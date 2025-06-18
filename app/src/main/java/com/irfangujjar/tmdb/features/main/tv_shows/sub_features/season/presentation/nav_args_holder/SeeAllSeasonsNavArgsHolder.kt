package com.irfangujjar.tmdb.features.main.tv_shows.sub_features.season.presentation.nav_args_holder

import com.irfangujjar.tmdb.core.models.SeasonModel
import com.irfangujjar.tmdb.core.navigation.args_holder.NavArgsHolder
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SeeAllSeasonsNavArgsHolder @Inject constructor() : NavArgsHolder<List<SeasonModel>>()