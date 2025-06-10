package com.irfangujjar.tmdb.features.main.tv_shows.sub_features.see_all.presentation.nav_args_holder

import com.irfangujjar.tmdb.core.navigation.args_holder.NavArgsHolder
import com.irfangujjar.tmdb.features.main.tv_shows.domain.models.TvShowsListModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SeeAllTvShowsNavArgsHolder @Inject constructor() : NavArgsHolder<TvShowsListModel>()