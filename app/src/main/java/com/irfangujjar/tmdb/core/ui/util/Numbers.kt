package com.irfangujjar.tmdb.core.ui.util

import java.text.DecimalFormat


fun formatDollarAmount(amount: Long): String {

    val trillion = 1_000_000_000_000L
    val billion = 1_000_000_000L
    val million = 1_000_000L
    val format = DecimalFormat("#,##0.##") // Show up to 2 decimal places, but drop .00
    return when {
        amount >= trillion -> "$${format.format(amount / trillion.toDouble())} trillion USD"
        amount >= billion -> "$${format.format(amount / billion.toDouble())} billion USD"
        amount >= million -> "$${format.format(amount / million.toDouble())} million USD"
        else -> "$$amount USD"
    }
    
}