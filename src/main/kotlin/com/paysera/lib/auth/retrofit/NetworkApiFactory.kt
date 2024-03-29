package com.paysera.lib.auth.retrofit

import com.paysera.lib.auth.clients.AuthApiClient
import com.paysera.lib.common.interfaces.ErrorLoggerInterface
import com.paysera.lib.common.interfaces.TokenRefresherInterface
import com.paysera.lib.common.retrofit.BaseApiFactory
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor

class NetworkApiFactory(
    baseUrl: String,
    userAgent: String?,
    timeout: Long? = null,
    httpLoggingInterceptorLevel: HttpLoggingInterceptor.Level = HttpLoggingInterceptor.Level.BASIC,
    errorLogger: ErrorLoggerInterface,
    certificateInterceptor: Interceptor?
) : BaseApiFactory<AuthApiClient>(
    baseUrl,
    userAgent,
    null,
    timeout,
    httpLoggingInterceptorLevel,
    errorLogger,
    certificateInterceptor
) {
    override fun createClient(tokenRefresher: TokenRefresherInterface?): AuthApiClient {
        createRetrofit(tokenRefresher).apply {
            return AuthApiClient(
                retrofit.create(NetworkApiClient::class.java),
                apiRequestManager
            )
        }
    }
}