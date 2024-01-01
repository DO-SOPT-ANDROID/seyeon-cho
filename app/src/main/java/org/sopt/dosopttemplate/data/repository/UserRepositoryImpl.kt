package org.sopt.dosopttemplate.data.repository

import dagger.hilt.android.lifecycle.HiltViewModel
import org.sopt.dosopttemplate.domain.repository.UserRepository
import org.sopt.dosopttemplate.data.datasource.UserDataSource
import org.sopt.dosopttemplate.domain.entity.UserEntity
import javax.inject.Inject

@HiltViewModel
class UserRepositoryImpl @Inject constructor(
    private val userDataSource: UserDataSource
    ): UserRepository {

    override suspend fun getUserList(page: Int): Result<List<UserEntity>> =
        runCatching {
            userDataSource.getUser(page).toUserEntity()
        }
}