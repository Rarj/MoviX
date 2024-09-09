package com.arj.network

import okhttp3.Interceptor
import okhttp3.Response

class RetryMechanismInterceptor(private val token: String) : Interceptor {

    private val retryCount: Byte = 3
    private var attempt: Byte = 1

    override fun intercept(chain: Interceptor.Chain): Response {
        return proceed(chain)
    }

    private fun proceed(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val request = original.newBuilder()
            .addHeader("accept", "application/json")
            .addHeader(
                "Authorization",
                "Bearer $token"
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