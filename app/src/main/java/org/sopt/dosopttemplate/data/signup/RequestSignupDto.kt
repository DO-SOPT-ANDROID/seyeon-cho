package org.sopt.dosopttemplate.data.signup

import android.provider.ContactsContract.CommonDataKinds.Nickname
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestSignupDto(
    @SerialName("username")
    val username: String,
    @SerialName("password")
    val password: String,
    @SerialName("major")
    val major: String,
    @SerialName("nickname")
    val nickname: String,
)
