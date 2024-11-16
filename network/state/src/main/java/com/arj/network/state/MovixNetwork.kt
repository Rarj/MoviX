package com.arj.network.state

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.net.UnknownHostException

suspend fun <T> safeCall(
    dispatcher: CoroutineDispatcher,
    apiService: suspend () -> T
): Flow<MovixNetworkResult<T>> {
    return flow {
        emit(MovixNetworkResult.Loading())
        emit(MovixNetworkResult.Success(apiService.invoke()))
    }.catch { cause: Throwable ->
        when (cause) {
            is UnknownHostException -> {
                emit(
                    MovixNetworkResult.Failed(
                        message = "No internet connection",
                        code = 503,
                    )
                )
            }

            is retrofit2.HttpException -> {
                when (cause.code()) {
                    401 -> emit(
                        MovixNetworkResult.Failed(
                            code = cause.code(),
                            message = "Unauthorized"
                        )
                    )

                    else -> emit(MovixNetworkResult.Failed(message = "Unknown Error"))
                }
            }

            else -> emit(MovixNetworkResult.Failed(message = cause.message))
        }
    }.flowOn(dispatcher)
}
