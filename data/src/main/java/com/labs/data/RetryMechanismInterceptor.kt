package com.labs.data

import okhttp3.Interceptor
import okhttp3.Response

internal class RetryMechanismInterceptor : Interceptor {

    private val retryCount: Byte = 3
    private var attempt: Byte = 1

    override fun intercept(chain: Interceptor.Chain): Response {
        return proceed(chain)
    }

    private fun proceed(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("accept", "application/json")
            .addHeader(
                "Authorization",
                "Bearer ${BuildConfig.AUTH_TOKEN}"
            )
            .build()

        var response: Response? = null
        try {
            response = chain.proceed(request)
            if (attempt < retryCount && !response.isSuccessful) {
                attempt++
                response.body?.close()
                return proceed(chain)
            }
            return response
        } catch (error: Exception) {
            if (attempt < retryCount) {
                attempt++
                response?.body?.close()
                return proceed(chain)
            }
            throw error
        }
    }

}