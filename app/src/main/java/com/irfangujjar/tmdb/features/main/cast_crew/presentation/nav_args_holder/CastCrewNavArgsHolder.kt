package com.irfangujjar.tmdb.features.main.cast_crew.presentation.nav_args_holder

import com.irfangujjar.tmdb.core.models.CreditsModel
import com.irfangujjar.tmdb.core.navigation.args_holder.NavArgsHolder
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CastCrewNavArgsHolder @Inject constructor() : NavArgsHolder<CreditsModel>()