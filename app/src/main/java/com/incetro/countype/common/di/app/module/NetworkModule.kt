package com.incetro.countype.common.di.app.module

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.incetro.countype.data.api.ServerApi
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory

import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    internal fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val logLevel = HttpLoggingInterceptor.Level.BODY
        return HttpLoggingInterceptor().apply { level = logLevel }
    }

    @Singleton
    @Provides
    internal fun provideGson(): Gson {
        return GsonBuilder()
            .create()
    }

    @Provides
    internal fun provideOkHttpBuilder(
        context: Context,
        loggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient.Builder {
        return OkHttpClient.Builder()
            .cache(Cache(context.cacheDir, CACHE_SIZE_BYTES))
            .addNetworkInterceptor(loggingInterceptor)
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
    }

    @Provides
    internal fun provideRetrofitBuilder(gson: Gson): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
    }

    @Singleton
    @Provides
    internal fun provideTheRunApi(
        retrofitBuilder: Retrofit.Builder,
        okHttpClientBuilder: OkHttpClient.Builder,
    ): ServerApi {

        return retrofitBuilder
            .client(okHttpClientBuilder.build())
            .build()
            .create(ServerApi::class.java)
    }

    companion object {
        // For emulator use http://10.0.2.2:8080/
        // For device use http://192.168.0.153:8080/
        // U need to use ur router ip. U can get in from cmd command "ipconfig"
        // Find Адаптер беспроводной локальной сети Беспроводная сеть:
        // And then IPv4-адрес.
        private const val BASE_URL = "http://192.168.0.153:8080/"
        private const val CACHE_SIZE_BYTES = (5 * 1024 * 1024).toLong()
        private const val CONNECT_TIMEOUT = 10L
        private const val READ_TIMEOUT = 10L
        private const val WRITE_TIMEOUT = 10L
    }
}