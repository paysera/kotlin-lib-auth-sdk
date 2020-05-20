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
    override val baseUrl = "https://auth-api.paysera.com/"
    override val certifiedHosts = listOf("auth-api.paysera.com")

    override fun createClient(tokenRefresher: TokenRefresherInterface?): AuthApiClient {
        createRetrofit(tokenRefresher).apply {
            return AuthApiClient(
                retrofit.create(NetworkApiClient::class.java),
                apiRequestManager
            )
        }
    }
}