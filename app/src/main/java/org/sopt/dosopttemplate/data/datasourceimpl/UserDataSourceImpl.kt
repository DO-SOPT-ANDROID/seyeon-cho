package org.sopt.dosopttemplate.data.datasourceimpl

import org.sopt.dosopttemplate.data.service.UserService
import org.sopt.dosopttemplate.data.model.response.ResponseUserDto
import org.sopt.dosopttemplate.data.datasource.UserDataSource
import javax.inject.Inject

class UserDataSourceImpl @Inject constructor(
    private val UserService: UserService
    ): UserDataSource {

    override suspend fun getUser(page: Int): ResponseUserDto =
        UserService.getUserList(page)
}
