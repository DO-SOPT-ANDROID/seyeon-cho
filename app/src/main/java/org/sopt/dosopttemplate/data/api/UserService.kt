package org.sopt.dosopttemplate.data.api

import org.sopt.dosopttemplate.data.user.ResponseUserDto
import retrofit2.Call
import retrofit2.http.GET

interface UserService {
    @GET("api/users?page=2")
    fun getUserList(): Call<ResponseUserDto>
}



