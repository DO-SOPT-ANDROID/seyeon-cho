package org.sopt.dosopttemplate.data.service

import org.sopt.dosopttemplate.data.model.response.ResponseUserDto
import retrofit2.http.GET
import retrofit2.http.Query

interface UserService {
    @GET("api/users")
    suspend fun getUserList(
        @Query("page") page: Int
    ): ResponseUserDto
}


