package com.capstone.project.kerjamin.data.helper

import androidx.test.espresso.idling.CountingIdlingResource

object IdlingResource {
    private const val CLIENT_RESOURCE = "GLOBAL"

    @JvmField
    val countingIdlingResource = CountingIdlingResource(CLIENT_RESOURCE)

    fun increment() {
        countingIdlingResource.increment()
    }

    fun decrement() {
        if (!countingIdlingResource.isIdleNow) {
            countingIdlingResource.decrement()
        }
    }
}

inline fun <T> setClientIdlingResource(function: () -> T): T {
    IdlingResource.increment()
    return try {
        function()
    } finally {
        IdlingResource.decrement()
    }
}