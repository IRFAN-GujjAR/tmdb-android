package com.irfangujjar.tmdb.features.main.movies.sub_features.collection.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.irfangujjar.tmdb.core.api.ResultWrapper
import com.irfangujjar.tmdb.core.api.safeApiCall
import com.irfangujjar.tmdb.core.navigation.nav_keys.HomeNavKey
import com.irfangujjar.tmdb.core.viewmodels.ViewModelWithKey
import com.irfangujjar.tmdb.features.main.movies.sub_features.collection.domain.models.CollectionDetailsModel
import com.irfangujjar.tmdb.features.main.movies.sub_features.collection.domain.usecases.CollectionDetailsUseCaseLoad
import com.irfangujjar.tmdb.features.main.movies.sub_features.collection.domain.usecases.params.CollectionDetailsUseCaseLoadParams
import com.irfangujjar.tmdb.features.main.movies.sub_features.collection.presentation.viewmodels.states.CollectionDetailsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CollectionDetailsViewModel @Inject constructor(
    private val useCase: CollectionDetailsUseCaseLoad
) : ViewModelWithKey<HomeNavKey.CollectionDetailsNavKey>() {

    private val _state = MutableStateFlow<CollectionDetailsState>(CollectionDetailsState.Loading)
    val state: StateFlow<CollectionDetailsState> = _state

    override fun doInitial() = loadDetails()

    fun loadDetails() {
        if (_state.value !is CollectionDetailsState.Loading)
            _state.value = CollectionDetailsState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            val result = safeApiCall {
                useCase.invoke(
                    params = CollectionDetailsUseCaseLoadParams(
                        collectionId = key!!.collectionId
                    )
                )
            }
            when (result) {
                is ResultWrapper.Error -> {
                    Log.d(
                        "CollectionDetailsViewModel",
                        "Error Loading CollectionDetails : ${result.errorEntity.message}"
                    )
                }

                is ResultWrapper.Success<CollectionDetailsModel> -> _state.value =
                    CollectionDetailsState.Loaded(collectionDetails = result.data)
            }
        }
    }


}