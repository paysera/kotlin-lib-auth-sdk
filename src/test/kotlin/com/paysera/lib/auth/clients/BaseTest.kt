package com.paysera.lib.auth.clients

import com.paysera.lib.auth.retrofit.NetworkApiFactory
import com.paysera.lib.common.interfaces.TokenRefresherInterface
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle

@TestInstance(Lifecycle.PER_CLASS)
internal open class BaseTest {

    companion object {
        private val userAgent = "okhttp/3.12.1"
        private val timeout: Long? = null
        private val loggingLevel = HttpLoggingInterceptor.Level.BODY
        private val tokenRefresher = object : TokenRefresherInterface {
            override fun refreshToken(): Deferred<Any> {
                return CompletableDeferred(1)
            }
        }
    }

    protected lateinit var apiClient: AuthApiClient

    @BeforeAll
    fun setUp() {
        apiClient = NetworkApiFactory(
            userAgent,
            timeout,
            loggingLevel
        ).createClient(tokenRefresher)
    }

    @AfterAll
    fun tearDown() {
        apiClient.cancelCalls()
    }
}