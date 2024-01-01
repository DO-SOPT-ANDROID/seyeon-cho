package org.sopt.dosopttemplate.domain.repository

import org.sopt.dosopttemplate.domain.entity.UserEntity

interface UserRepository {
    suspend fun getUserList(page: Int): Result<List<UserEntity>>
}