package com.paysera.lib.auth.clients

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import runCatchingBlocking

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class AuthApiClientTest : BaseTest() {

    @Test
    fun invalidateAuthToken() {
        val testValidAuthToken = "enter_valid_token_key"
        val result = apiClient.invalidateAuthToken(
            "Bearer $testValidAuthToken"
        ).runCatchingBlocking()

        assert(result.isSuccess)
    }

    @Test
    fun createSystemTokenOptional() {
        val testTokenScopes = "logged_in"
        val testAuthTokenKey = "token_key"
        val testAudience = "evpbank"

        val result = apiClient.createSystemTokenOptional(
            "Bearer $testAuthTokenKey",
            testAudience,
            testTokenScopes
        ).runCatchingBlocking()

        assert(result.isSuccess)
        assert(result.getOrNull() != null)
    }
}