package com.paysera.lib.auth.retrofit

import com.paysera.lib.auth.entities.SystemToken
import com.paysera.lib.auth.entities.requests.CreateSystemTokenOptionalRequest
import com.paysera.lib.auth.entities.requests.CreateSystemTokenScopeChallengeRequest
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Header
import retrofit2.http.POST

interface NetworkApiClient {
    @DELETE("authentication/rest/v1/auth-tokens/current")
    fun invalidateAuthToken(
        @Header("Authorization") authorizationHeaderValue: String
    ): Deferred<Response<Unit>>

    @POST("authentication/rest/v1/system-tokens/optional")
    fun createSystemTokenOptional(
        @Header("Authorization") authorizationHeaderValue: String,
        @Body request: CreateSystemTokenOptionalRequest
    ): Deferred<SystemToken>

    @POST("authentication/rest/v1/system-tokens/optional-collection")
    fun createSystemTokenCollectionOptional(
        @Header("Authorization") authorizationHeaderValue: String,
        @Body request: List<CreateSystemTokenOptionalRequest>
    ): Deferred<List<SystemToken>>

    @POST("authentication/rest/v1/system-tokens/scope-challenge")
    fun createSystemTokenScopeChallenge(
        @Header("Authorization") authorizationHeaderValue: String,
        @Body identifier: CreateSystemTokenScopeChallengeRequest
    ): Deferred<SystemToken>
}