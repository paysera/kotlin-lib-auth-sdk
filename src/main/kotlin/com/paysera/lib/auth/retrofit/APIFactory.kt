package com.paysera.lib.auth.retrofit

import com.google.gson.FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES
import com.google.gson.GsonBuilder
import com.paysera.lib.auth.clients.AuthApiClient
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class APIFactory(private val timeout: Long? = null) {
    fun createAuthApiClient(baseUrl: String = "https://auth-api.paysera.com/"): AuthApiClient {
        return AuthApiClient(createRetrofitClient(baseUrl))
    }

    fun createRetrofitClient(baseUrl: String): APIClient {
        return createRetrofit(baseUrl).create(APIClient::class.java)
    }

    fun createRetrofit(baseUrl: String, okHttpClient: OkHttpClient = createOkHttpClient()) = with(Retrofit.Builder()) {
        baseUrl(baseUrl)
        addConverterFactory(createGsonConverterFactory())
        addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
        client(okHttpClient)
        build()
    }

    fun createOkHttpClient(): OkHttpClient {
        return with(OkHttpClient().newBuilder()) {
            timeout?.let {
                readTimeout(it, TimeUnit.MILLISECONDS)
                writeTimeout(it, TimeUnit.MILLISECONDS)
                connectTimeout(it, TimeUnit.MILLISECONDS)
            }
            build()
        }
    }

    private fun createGsonConverterFactory(): GsonConverterFactory {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.setFieldNamingPolicy(LOWER_CASE_WITH_UNDERSCORES)
        return GsonConverterFactory.create(gsonBuilder.create())
    }
}