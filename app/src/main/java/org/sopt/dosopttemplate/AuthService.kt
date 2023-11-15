package org.sopt.dosopttemplate

import org.sopt.dosopttemplate.data.RequestLoginDto
import org.sopt.dosopttemplate.data.ResponseLoginDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("api/v1/members")
    fun signup(
        @Body request: RequestLoginDto,
    ): Call<Unit>

    @POST("api/v1/members/sign-in")
    fun login(
        @Body request: RequestLoginDto,
    ): Call<ResponseLoginDto>
}