package org.sopt.dosopttemplate.data.api

import android.util.Log
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.sopt.dosopttemplate.BuildConfig
import retrofit2.Retrofit

object ApiFactory {
    const val AUTH_BASE_URL = BuildConfig.AUTH_BASE_URL
    const val USER_BASE_URL = BuildConfig.USER_BASE_URL

    private fun getLogOkHttpClient(): Interceptor {
        val loggingInterceptor = HttpLoggingInterceptor { message ->
            Log.d("Retrofit2", "CONNECTION INFO -> $message")
        }
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(getLogOkHttpClient())
        .build()

    inline fun <reified T> create(baseUrl: String): T {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()

        return retrofit.create(T::class.java)
    }
}

object ServicePool {
    val authService = ApiFactory.create<AuthService>(ApiFactory.AUTH_BASE_URL)
    val userService = ApiFactory.create<UserService>(ApiFactory.USER_BASE_URL)
}

