package com.paysera.lib.auth.retrofit

import com.paysera.lib.auth.clients.AuthApiClient
import com.paysera.lib.common.interfaces.TokenRefresherInterface
import com.paysera.lib.common.retrofit.BaseApiFactory
import okhttp3.logging.HttpLoggingInterceptor

class NetworkApiFactory(
    userAgent: String?,
    timeout: Long? = null,
    httpLoggingInterceptorLevel: HttpLoggingInterceptor.Level = HttpLoggingInterceptor.Level.BASIC
) : BaseApiFactory<AuthApiClient>(
    userAgent,
    null,
    timeout,
    httpLoggingInterceptorLevel
) {
    override fun createClient(baseUrl: String, tokenRefresher: TokenRefresherInterface?): AuthApiClient {
        createRetrofit(baseUrl, tokenRefresher).apply {
            return AuthApiClient(
                retrofit.create(NetworkApiClient::class.java),
                apiRequestManager
            )
        }
    }
}