package com.paysera.lib.auth.retrofit

import com.google.gson.FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES
import com.google.gson.GsonBuilder
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitConfigurator() {

    fun createRetrofit(baseUrl: String = "https://auth-api.paysera.com/") = with(Retrofit.Builder()) {
        baseUrl(baseUrl)
        addConverterFactory(createGsonConverterFactory())
        addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
        client(createOkHttpClient())
        build()
    }

    private fun createOkHttpClient() = OkHttpClient().newBuilder().build()

//    {
//        addNetworkInterceptor(with(HttpLoggingInterceptor()) {
//            setLevel(HttpLoggingInterceptor.Level.BODY)
//        })
//        build()
//    }

    private fun createGsonConverterFactory(): GsonConverterFactory {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.setFieldNamingPolicy(LOWER_CASE_WITH_UNDERSCORES)
        return GsonConverterFactory.create(gsonBuilder.create())
    }
}