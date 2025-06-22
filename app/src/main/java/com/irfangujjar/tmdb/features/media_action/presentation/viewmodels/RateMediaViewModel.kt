package com.irfangujjar.tmdb.features.media_action.presentation.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.irfangujjar.tmdb.UserSession
import com.irfangujjar.tmdb.core.api.ResultWrapper
import com.irfangujjar.tmdb.core.api.safeApiCall
import com.irfangujjar.tmdb.core.navigation.nav_keys.HomeNavKey
import com.irfangujjar.tmdb.core.viewmodels.ViewModelWithKeyAndErrorAlerts
import com.irfangujjar.tmdb.features.media_action.domain.models.MediaActionModel
import com.irfangujjar.tmdb.features.media_action.domain.usecases.MediaActionUseCaseRate
import com.irfangujjar.tmdb.features.media_action.domain.usecases.MediaActionUseCaseUnRate
import com.irfangujjar.tmdb.features.media_action.domain.usecases.params.MediaActionUseCaseRateParams
import com.irfangujjar.tmdb.features.media_action.domain.usecases.params.MediaActionUseCaseUnRateParams
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RateMediaViewModel @Inject constructor(
    private val userSession: UserSession,
    private val useCaseRate: MediaActionUseCaseRate,
    private val useCaseUnRate: MediaActionUseCaseUnRate
) : ViewModelWithKeyAndErrorAlerts<HomeNavKey.RateMediaNavKey>() {

    var currentRating by mutableIntStateOf(0)
        private set

    var isLoading by mutableStateOf(false)
        private set

    var showUnRateConfirmationDialog by mutableStateOf(false)
        private set

    fun showUnRateConfirmationDialog() {
        this.showUnRateConfirmationDialog = true
    }

    fun hideUnRateConfirmationDialog() {
        this.showUnRateConfirmationDialog = false
    }


    override fun doInitial() {
        currentRating = key!!.rating.toInt()
    }

    fun changeRating(rating: Int) {
        currentRating = rating
    }

    fun rate(onSuccess: () -> Unit) {
        load(onSuccess = onSuccess) {
            useCaseRate.invoke(
                params = MediaActionUseCaseRateParams(
                    mediaType = key!!.mediaStateType,
                    mediaId = key!!.mediaId,
                    rating = currentRating,
                    sessionId = userSession.sessionId!!
                )
            )
        }
    }

    fun unRate(onSuccess: () -> Unit) {
        load(onSuccess = onSuccess) {
            useCaseUnRate.invoke(
                params = MediaActionUseCaseUnRateParams(
                    mediaType = key!!.mediaStateType,
                    mediaId = key!!.mediaId,
                    sessionId = userSession.sessionId!!
                )
            )
        }
    }

    private fun load(onSuccess: () -> Unit, invoker: suspend () -> MediaActionModel) {
        if (!isLoading)
            isLoading = true
        viewModelScope.launch(Dispatchers.IO) {
            val result = safeApiCall {
                invoker()
            }
            when (result) {
                is ResultWrapper.Error -> {
                    Log.e("RateMediaViewModel", "Error : ${result.errorEntity.message}")
                    showAlert(result.errorEntity.message)
                }

                is ResultWrapper.Success<MediaActionModel> -> {
                    when (result.data.statusCode) {
                        1, 12, 13 -> {
                            onSuccess()
                        }

                        else -> {
                            showAlert(
                                result.data.statusMessage
                                    ?: "An unknown error occurred! Please try again."
                            )
                            isLoading = false
                        }
                    }
                }
            }

        }
    }

}