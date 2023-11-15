package org.sopt.dosopttemplate

import org.sopt.dosopttemplate.data.RequestLoginDto
import org.sopt.dosopttemplate.data.ResponseLoginDto
import org.sopt.dosopttemplate.data.signup.RequestSignupDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("api/v1/members")
    fun signup(
        @Body request: RequestSignupDto,
    ): Call<Unit>

    @POST("api/v1/members/sign-in")
    fun login(
        @Body request: RequestLoginDto,
    ): Call<ResponseLoginDto>
}