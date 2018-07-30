package com.paysera.lib.auth.retrofit

import com.paysera.lib.auth.entities.SystemToken
import com.paysera.lib.auth.entities.requests.CreateSystemTokenOptionalRequest
import io.reactivex.Observable
import retrofit2.http.*

interface APIClient {
    @DELETE("authentication/rest/v1/auth-tokens/current")
    fun invalidateAuthToken(
        @Header("Authorization") authorizationHeaderValue: String
    ): Observable<Unit>

    @POST("authentication/rest/v1/system-tokens/optional")
    fun createSystemTokenOptional(
        @Header("Authorization") authorizationHeaderValue: String,
        @Body request: CreateSystemTokenOptionalRequest
    ): Observable<SystemToken>
}