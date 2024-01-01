package org.sopt.dosopttemplate.data.datasourceimpl

import dagger.hilt.android.lifecycle.HiltViewModel
import org.sopt.dosopttemplate.data.service.UserService
import org.sopt.dosopttemplate.data.model.response.ResponseUserDto
import org.sopt.dosopttemplate.data.datasource.UserDataSource
import org.sopt.dosopttemplate.di.ServicePool.userService
import javax.inject.Inject

@HiltViewModel
class UserDataSourceImpl @Inject constructor(
    private val UserService: UserService
    ): UserDataSource {

    override suspend fun getUser(page: Int): ResponseUserDto =
        UserService.getUserList(page)
}
