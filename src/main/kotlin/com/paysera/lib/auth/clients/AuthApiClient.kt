package com.paysera.lib.auth.clients

import com.paysera.lib.auth.entities.SystemToken
import com.paysera.lib.auth.entities.requests.CreateSystemTokenOptionalRequest
import com.paysera.lib.auth.retrofit.APIClient
import io.reactivex.Observable

class AuthApiClient(private val apiClient: APIClient) {

    fun invalidateAuthToken(authToken: String): Observable<Unit> {
        return apiClient.invalidateAuthToken("Bearer ${authToken}")
    }

    fun createSystemTokenOptional(authToken: String, audience: String, scope: String): Observable<SystemToken> {
        return apiClient.createSystemTokenOptional(
            "Bearer ${authToken}",
            CreateSystemTokenOptionalRequest(audience, scope)
        )
    }
}