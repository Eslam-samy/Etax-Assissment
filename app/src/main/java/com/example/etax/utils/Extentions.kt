package com.example.etax.utils

fun Long.isCacheStale(): Boolean {
    val now = System.currentTimeMillis()
    return now - this > 24 * 60 * 60 * 1000 // 24 hours
}