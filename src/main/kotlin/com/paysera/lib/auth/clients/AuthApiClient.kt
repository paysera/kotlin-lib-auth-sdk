package com.paysera.lib.auth.clients

import com.paysera.lib.auth.entities.SystemToken
import com.paysera.lib.auth.entities.requests.CreateSystemTokenOptionalRequest
import com.paysera.lib.auth.retrofit.APIClient
import io.reactivex.Single

class AuthApiClient(private val apiClient: APIClient) {

    fun invalidateAuthToken(authToken: String): Single<Unit> {
        return apiClient.invalidateAuthToken("Bearer ${authToken}")
    }

    fun createSystemTokenOptional(authToken: String, audience: String, scope: String): Single<SystemToken> {
        return apiClient.createSystemTokenOptional(
            "Bearer ${authToken}",
            CreateSystemTokenOptionalRequest(audience, scope)
        )
    }
}