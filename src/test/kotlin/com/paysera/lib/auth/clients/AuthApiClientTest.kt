package com.paysera.lib.auth.clients

import com.paysera.lib.auth.entities.requests.CreateSystemTokenOptionalRequest
import com.paysera.lib.auth.entities.requests.CreateSystemTokenScopeChallengeRequest
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

    @Test
    fun createSystemTokenCollectionOptional() {
        val testTokenScopes = "logged_in"
        val testAuthTokenKey = "token_key"

        val requests = listOf(
            CreateSystemTokenOptionalRequest("audience_1", testTokenScopes),
            CreateSystemTokenOptionalRequest("audience_2", testTokenScopes)
        )

        val result = apiClient.createSystemTokenCollectionOptional(
            "Bearer $testAuthTokenKey",
            requests
        ).runCatchingBlocking()

        assert(result.isSuccess)
        assert(result.getOrNull() != null)
    }

    @Test
    fun createSystemTokenScopeChallenge() {
        val testAuthTokenKey = "auth_token"
        val identifier = "identifier"

        val result = apiClient.createSystemTokenScopeChallenge(
                testAuthTokenKey,
                CreateSystemTokenScopeChallengeRequest(identifier)
        ).runCatchingBlocking()

        assert(result.isSuccess)
        assert(result.getOrNull() != null)
    }
}