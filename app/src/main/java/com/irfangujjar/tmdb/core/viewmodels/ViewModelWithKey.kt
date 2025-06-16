package com.irfangujjar.tmdb.core.viewmodels

import androidx.lifecycle.ViewModel

abstract class ViewModelWithKey<K> : ViewModel() {
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