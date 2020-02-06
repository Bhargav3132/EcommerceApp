package com.heady.ecommerceapp.data.remote.simaple

sealed class ApiResponse<out T> {

    data class Success<out T>(val data: T?, val successMessage: String? = null) : ApiResponse<T>()

    data class Loading(val isRefresh: Boolean = false) : ApiResponse<Nothing>()

    data class ApiError(val apiErrorMessage: String) : ApiResponse<Nothing>()

    data class ServerError(val errorMessage: String) : ApiResponse<Nothing>()

    data class UnauthorizedAccess(val errorMessage: String) : ApiResponse<Nothing>()

    object NoInternetConnection : ApiResponse<Nothing>()
}
