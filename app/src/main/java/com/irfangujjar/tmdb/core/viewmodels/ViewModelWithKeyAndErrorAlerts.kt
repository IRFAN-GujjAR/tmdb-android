package com.irfangujjar.tmdb.core.viewmodels

abstract class ViewModelWithKeyAndErrorAlerts<K> : ViewModelWithErrorAlerts() {
    protected var key: K? = null
    private var isInitialized = false

    fun initialize(key: K) {
        if (!isInitialized) {
            this.key = key
            isInitialized = true
            doInitial()
        }
    }

    protected abstract fun doInitial()
}