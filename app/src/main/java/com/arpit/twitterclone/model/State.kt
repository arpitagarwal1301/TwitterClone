package com.arpit.twitterclone.model


/**
 * State Management for UI & Data.
 *
 * @param T
 * @constructor Create empty State
 */
sealed class State<T> {
    class Loading<T> : State<T>()

    data class Success<T>(val data: T) : State<T>()

    data class Error<T>(val message: String) : State<T>()

    companion object {

        /**
         * Loading
         * @param T
         */
        fun <T> loading() = Loading<T>()

        /**
         * Success
         * @param T
         * @param data
         */
        fun <T> success(data: T) =
            Success(data)

        /**
         * Error
         * @param T
         * @param message
         */
        fun <T> error(message: String) =
            Error<T>(message)
    }
}
