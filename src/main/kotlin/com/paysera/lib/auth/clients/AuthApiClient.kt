package com.paysera.lib.auth.clients

import com.paysera.lib.auth.entities.SystemToken
import com.paysera.lib.auth.entities.requests.CreateSystemTokenOptionalRequest
import com.paysera.lib.auth.entities.requests.CreateSystemTokenScopeChallengeRequest
import com.paysera.lib.auth.retrofit.NetworkApiClient
import com.paysera.lib.common.retrofit.ApiRequestManager
import com.paysera.lib.common.retrofit.BaseApiClient
import kotlinx.coroutines.Deferred
import retrofit2.Response

class AuthApiClient(
    private val apiClient: NetworkApiClient,
    apiRequestManager: ApiRequestManager
) : BaseApiClient(apiRequestManager) {

    fun invalidateAuthToken(authToken: String): Deferred<Response<Unit>> {
        return apiClient.invalidateAuthToken("Bearer $authToken")
    }

    fun createSystemTokenOptional(authToken: String, audience: String, scope: String): Deferred<SystemToken> {
        return apiClient.createSystemTokenOptional(
            "Bearer $authToken",
            CreateSystemTokenOptionalRequest(audience, scope)
        )
    }

    fun createSystemTokenCollectionOptional(
        authToken: String,
        requests: List<CreateSystemTokenOptionalRequest>
    ): Deferred<List<SystemToken>> {
        return apiClient.createSystemTokenCollectionOptional(
            "Bearer $authToken",
            requests
        )
    }

    fun createSystemTokenScopeChallenge(
        authToken: String,
        identifier: CreateSystemTokenScopeChallengeRequest
    ): Deferred<SystemToken> {
        return apiClient.createSystemTokenScopeChallenge(
            "Bearer $authToken",
            identifier
        )
    }
}