package org.sopt.dosopttemplate.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.sopt.dosopttemplate.domain.entity.UserEntity

@Serializable
data class ResponseUserDto(
    @SerialName("page")
    val page: Int = 0,
    @SerialName("per_page")
    val per_page: Int = 0,
    @SerialName("total")
    val total: Int = 0,
    @SerialName("total_pages")
    val total_pages: Int = 0,
    @SerialName("data")
    val data: List<ResponseUserData>,
    @SerialName("support")
    val support: Support,
) {
    @Serializable
    data class ResponseUserData(
        @SerialName("id")
        val id: Int = 0,
        @SerialName("email")
        val email: String = "",
        @SerialName("first_name")
        val first_name: String = "",
        @SerialName("last_name")
        val last_name: String = "",
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
            first_name = it.first_name,
            email = it.email,
            avatar = it.avatar
        )
    }
}

