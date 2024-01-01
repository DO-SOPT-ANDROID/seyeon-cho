package org.sopt.dosopttemplate.data.datasource

import org.sopt.dosopttemplate.data.model.response.ResponseUserDto

interface UserDataSource {
    suspend fun getUser(page : Int): ResponseUserDto
}