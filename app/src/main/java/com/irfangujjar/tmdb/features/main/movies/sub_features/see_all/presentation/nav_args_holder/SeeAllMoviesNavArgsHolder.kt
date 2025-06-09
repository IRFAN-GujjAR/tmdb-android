package com.irfangujjar.tmdb.features.main.movies.sub_features.see_all.presentation.nav_args_holder

import com.irfangujjar.tmdb.core.navigation.args_holder.NavArgsHolder
import com.irfangujjar.tmdb.features.main.movies.domain.models.MoviesListModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SeeAllMoviesNavArgsHolder @Inject constructor() : NavArgsHolder<MoviesListModel>()