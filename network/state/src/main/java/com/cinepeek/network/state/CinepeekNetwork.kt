package com.cinepeek.network.state

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.net.UnknownHostException

suspend fun <T> safeCall(
    dispatcher: CoroutineDispatcher,
    apiService: suspend () -> T
): Flow<CinepeekNetworkResult<T>> {
    return flow {
        emit(CinepeekNetworkResult.Loading())
        emit(CinepeekNetworkResult.Success(apiService.invoke()))
    }.catch { cause: Throwable ->
        when (cause) {
            is UnknownHostException -> {
                emit(
                    CinepeekNetworkResult.Failed(
                        message = "No internet connection",
                        code = 503,
                    )
                )
            }

            is HttpException -> {
                when (cause.code()) {
                    401 -> emit(
                        CinepeekNetworkResult.Failed(
                            code = cause.code(),
                            message = "Unauthorized"
                        )
                    )

                    else -> emit(CinepeekNetworkResult.Failed(message = "Unknown Error"))
                }
            }

            else -> emit(CinepeekNetworkResult.Failed(message = cause.message))
        }
    }.flowOn(dispatcher)
}
