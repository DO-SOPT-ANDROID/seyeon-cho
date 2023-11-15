package org.sopt.dosopttemplate.data.api

import org.sopt.dosopttemplate.data.login.RequestLoginDto
import org.sopt.dosopttemplate.data.login.ResponseLoginDto
import org.sopt.dosopttemplate.data.signup.RequestSignupDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("api/v1/members")
    fun signUp(
        @Body request: RequestSignupDto,
    ): Call<Unit>

    @POST("api/v1/members/sign-in")
    fun login(
        @Body request: RequestLoginDto,
    ): Call<ResponseLoginDto>
}