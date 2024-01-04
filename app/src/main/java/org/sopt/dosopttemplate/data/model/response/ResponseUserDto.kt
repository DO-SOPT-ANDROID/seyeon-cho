package org.sopt.dosopttemplate.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.sopt.dosopttemplate.domain.entity.UserEntity

@Serializable
data class ResponseUserDto(
    @SerialName("page")
    val page: Int,
    @SerialName("per_page")
    val perPage: Int,
    @SerialName("total")
    val total: Int,
    @SerialName("total_pages")
    val totalPages: Int,
    @SerialName("data")
    val data: List<ResponseUserData>,
    @SerialName("support")
    val support: Support,
) {
    @Serializable
    data class ResponseUserData(
        @SerialName("id")
        val id: Int,
        @SerialName("email")
        val email: String = "",
        @SerialName("first_name")
        val firstName: String = "",
        @SerialName("last_name")
        val lastName: String = "",
        @SerialName("avatar")
        val avatar: String = ""
    )
    @Serializable
    data class Support(
        @SerialName("url")
        val url: String = "",
        @SerialName("text")
        val text: String = ""
    )
    fun toUserEntity(): List<UserEntity> = data.map {
        UserEntity(
            firstName = it.firstName,
            email = it.email,
            avatar = it.avatar
        )
    }
}

