package com.irfangujjar.tmdb.features.main.celebs.sub_features.see_all.presentation.nav_args_holder

import com.irfangujjar.tmdb.core.navigation.args_holder.NavArgsHolder
import com.irfangujjar.tmdb.features.main.celebs.domain.models.CelebsListModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SeeAllCelebsNavArgsHolder @Inject constructor() : NavArgsHolder<CelebsListModel>()