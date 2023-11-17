package org.sopt.dosopttemplate

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

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
    val data: List<ResponseUserData> = emptyList(),
    @SerialName("support")
    val support: ResponceUserSupport = ResponceUserSupport())